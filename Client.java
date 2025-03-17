import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

class Client extends JFrame implements ActionListener
{
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    JPanel p;

    private DataOutputStream dataOut;
    private DataInputStream dataIn;


    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("SAN_SERIf",Font.PLAIN,20);
    public String contenttosend;
    private static SecretKey secretKey;
    private static byte[] ivBytes;

    public Client()
    {
        String[] serverDetails = showServerInputDialog();
        if (serverDetails == null) {
            System.out.println("User cancelled the operation. Exiting...");
            System.exit(0); // Exit if user cancels
        }
        String IP_A = serverDetails[0];
        int sPort = Integer.parseInt(serverDetails[1]);

        try
        {
            System.out.println("Sending request to Server ");
            socket=new Socket(IP_A,sPort);
            System.out.println("Connection Established to Server side");

            ObjectInputStream keyIn = new ObjectInputStream(socket.getInputStream());
            secretKey = (SecretKey) keyIn.readObject();
            ivBytes = (byte[]) keyIn.readObject();

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));//obtaining stream from server

            out=new PrintWriter(socket.getOutputStream());//writing stream to server

            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());
    
            createGUI();
            handleEvents();
            startReading();

            startWriting();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private String[] showServerInputDialog() {
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel S__IP = new JLabel("Server IP:");
        JTextField lh = new JTextField("localhost", 15);
        JLabel spl = new JLabel("Server Port:");
        JTextField sp = new JTextField("7777", 15);

        panel.add(S__IP);
        panel.add(lh);
        panel.add(spl);
        panel.add(sp);


        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Server Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String ip = lh.getText().trim();
            String port = sp.getText().trim();


            if (ip.isEmpty() || port.isEmpty()) {
                JOptionPane.showMessageDialog(null, "IP and Port cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            try {
                Integer.parseInt(port); 
                return new String[]{ip, port};
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Port must be a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;
    }

    private void createGUI()
    {
        setLayout(null);
        p=new JPanel();
        p.setBounds(0,0,450,70);
        p.setBackground(new Color(7,94,84));
        p.setLayout(null);
        add(p);

        ImageIcon iiIcon=new ImageIcon(ClassLoader.getSystemResource("imgs/1.png"));
        Image i2=iiIcon.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
        ImageIcon i2Icon=new ImageIcon(i2);
        JLabel pictu=new JLabel(i2Icon);
        pictu.setBounds(5,15,45,45);
        p.add(pictu);

        pictu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                try {
                    Desktop.getDesktop().open(new File("imgs/1.png"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        ImageIcon v=new ImageIcon(ClassLoader.getSystemResource("imgs/V.png"));
        Image vi=v.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        ImageIcon vcall=new ImageIcon(vi);
        JLabel videocall=new JLabel(vcall);
        videocall.setBackground(Color.white);
        videocall.setForeground(Color.white);
        videocall.setBounds(290,15,45,45);
        p.add(videocall);

        ImageIcon c=new ImageIcon(ClassLoader.getSystemResource("imgs/call.png"));
        Image cl=c.getImage().getScaledInstance(45,45,Image.SCALE_DEFAULT);
        ImageIcon cll=new ImageIcon(cl);
        JLabel cll1=new JLabel(cll);
        cll1.setBackground(Color.white);
        cll1.setForeground(Color.white);
        cll1.setBounds(340,15,45,45);
        p.add(cll1);

        ImageIcon ifo=new ImageIcon(ClassLoader.getSystemResource("imgs/info.png"));
        Image ifo1=ifo.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon ifo2=new ImageIcon(ifo1);
        JLabel info=new JLabel(ifo2);
        info.setBackground(Color.white);
        info.setForeground(Color.white);
        info.setBounds(390,15,45,45);
        p.add(info);
        info.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae ){
                try {
                    dp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });

        JLabel name= new JLabel("Vikas patil");
        name.setLayout(null);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        name.setForeground(Color.white);
        name.setBounds(70, 15, 170, 18);
        p.add(name);



        JLabel name2= new JLabel("Active now.");
        name2.setLayout(null);
        name2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        name2.setForeground(Color.white);
        name2.setBounds(70, 38, 190, 18);
        p.add(name2);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("imgs/telegram.png"));
        Image i211=i1.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
        ImageIcon icc1=new ImageIcon(i211);
        JLabel pictu1=new JLabel(icc1);
        pictu1.setBounds(270, 610, 70, 30);
        add(pictu1);
        pictu1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                if(!socket.isClosed())
                {
                    try 
                    {
                        contenttosend=messageInput.getText();
                        messageArea.append("Me :"+contenttosend+"\n");
                        messageArea.setCaretPosition(messageArea.getDocument().getLength());
                        String encryptedMsg = encrypt(contenttosend, secretKey, ivBytes);
                        out.println(encryptedMsg);
                        out.flush();
                        messageInput.setText("");
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }
        });

        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("imgs/gallery.png"));
        Image immg=img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon ime=new ImageIcon(immg);
        JLabel im1=new JLabel(ime);
        im1.setBounds(320, 600, 60, 40);
        add(im1);
        im1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                    sendFile();
                }
            });
        ImageIcon chat=new ImageIcon(ClassLoader.getSystemResource("imgs/chatbot.png"));
        Image chatt=chat.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon ch1=new ImageIcon(chatt);
        JLabel chatbot=new JLabel(ch1);
        chatbot.setBounds(370, 600, 60, 40);
        add(chatbot);

        chatbot.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){

                }
            });
        messageArea.setFont(font);
        messageInput.setFont(font);
        
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBounds(0, 75, 460, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageArea.setEditable(false);
        add(scrollPane); 

        messageInput.setBounds(5, 600, 281, 50);
        add(messageInput);
        contenttosend=messageInput.getText();

        setResizable(false);
        this.setTitle("Vchat");
        ImageIcon icon = new ImageIcon("imgs/vch.jpg");
        this.setIconImage(icon.getImage());
        setSize(460,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(930,10);
        this.setVisible(true);
        
    }
    
    private void sendFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images & Videos", "jpg", "png", "mp4","mp3"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Sending file indicator as File name and metadata
                out.println("FILE");
                out.flush();
                out.println(file.getName());
                out.flush();
                out.println(file.length());
                out.flush();

                // Sending raw file bytes
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    dataOut.write(buffer, 0, bytesRead);
                }
                dataOut.flush();
                fis.close();
                messageArea.append("Me: Sent file " + file.getName() + "\n");
                messageArea.setCaretPosition(messageArea.getDocument().getLength());
            } catch (IOException e) {
                e.printStackTrace();
                messageArea.append("Error sending file: " + e.getMessage() + "\n");
                messageArea.setCaretPosition(messageArea.getDocument().getLength());
            }
        }
    }

    public void dp()
    {
        JFrame frame = new JFrame("New Window");
        JPanel p=new JPanel();
        p.setBounds(0,0,460,700);

        ImageIcon in=new ImageIcon(ClassLoader.getSystemResource("imgs/i.jpg"));
        Image i1=in.getImage().getScaledInstance(460, 700, Image.SCALE_SMOOTH);
        ImageIcon img=new ImageIcon(i1);
        JLabel image=new JLabel(img);
        image.setLayout(null);
        image.setBounds(0,20,460,700);
        
        ImageIcon c=new ImageIcon(ClassLoader.getSystemResource("imgs/bac.png"));
        Image ij=c.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon cc=new ImageIcon(ij);
        JLabel previous=new JLabel(cc);
        previous.setBounds(5,10,45,45);
        frame.add(previous);
        p.add(image);
        frame.add(p);
        previous.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                frame.dispose();
            }
        });
        
        frame.setLayout(null);
        frame.setSize(460,700);
        frame.setLocation(930,10);
        frame.setTitle("Vchat");
        ImageIcon icon = new ImageIcon("imgs/vch.jpg");
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
    }

    private void handleEvents()
    {
        messageInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // System.out.println("key released"+e.getKeyCode());
                if(e.getKeyCode()==10)
                {
                    // System.out.println("you have pressed enter button");

                    try{
                    
                    if(!socket.isClosed())
                    {
                        // String contenttosend=messageInput.getText();
                        contenttosend=messageInput.getText();
                        messageArea.append("Me :"+contenttosend+"\n");
                        messageArea.setCaretPosition(messageArea.getDocument().getLength());
                        String encryptedMsg = encrypt(contenttosend, secretKey, ivBytes);
                        out.println(encryptedMsg);
                        out.flush();
                        messageInput.setText("");
                        
                    }
                    else
                    {
                        socket.close();
                        messageInput.setEditable(false);
                        
                    }
                    } catch(Exception ee)
                    {
                        messageArea.append(ee.getMessage());
                        messageArea.setCaretPosition(messageArea.getDocument().getLength());
                    }
                }
            }
            
        });
    }


    public void startReading()
        {
            Runnable r1=()->
            {

                System.out.println("Reader started");

                try 
                {
                    while (true ) 
                    {
                    
                        String msg=br.readLine();
                        if (msg != null) 
                        {
                            if (msg.equals("FILE")) {
                                receiveFile();
                            }
                            
                            else 
                            {    
                                String decryptedMessage = decrypt(msg, secretKey,ivBytes);
                                if(decryptedMessage.equals("exit"))
                                {
                                    System.out.println("Mohit terminated the chat");
                                    socket.close();
                                    break;
                                }
                                else 
                                {
                                    messageArea.append("Vikas:"+decryptedMessage+"\n");
                                    messageArea.setCaretPosition(messageArea.getDocument().getLength());
                                } 
                            }
                        } 
                    } 
                  
                }
                catch (Exception e) 
                    {
                        e.printStackTrace();   
                    }

            };
            new Thread(r1).start();
        }
    private void receiveFile() throws IOException {
        String fileName = br.readLine();
        long fileSize = Long.parseLong(br.readLine());
        File file = new File("received_" + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[4096];
        long bytesReceived = 0;
        int bytesRead;
        while (bytesReceived < fileSize && (bytesRead = dataIn.read(buffer, 0, (int) Math.min(buffer.length, fileSize - bytesReceived))) != -1) {
            fos.write(buffer, 0, bytesRead);
            bytesReceived += bytesRead;
        }
        fos.close();
        messageArea.append("Vikas: Received file " + fileName +")\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
        if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
            displayImage(file);
        }
        else if (fileName.endsWith(".mp3")) {
            messageArea.append("Received an audio file. You can play it from: " + file.getAbsolutePath() + "\n");
            messageArea.setCaretPosition(messageArea.getDocument().getLength());
        }
    }

    private void displayImage(File file) {
        JFrame imageFrame = new JFrame("Received Image: " + file.getName());
        ImageIcon icon = new ImageIcon(file.getPath());
        JLabel imageLabel = new JLabel(icon);
        imageFrame.add(imageLabel);
        imageFrame.pack();
        imageFrame.setLocationRelativeTo(this);
        imageFrame.setVisible(true);
    }

    public void startWriting()
        {
            Runnable r2=()->
            {
                
                    try 
                    {
                        while (true &&!socket.isClosed()) 
                        {   
                            BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                            String content=br1.readLine();
                            if (content.equals("FILE")) {
                                out.println(content);
                            }
                            else
                            {
                                String ENCRYPT_msg=encrypt(content, secretKey,ivBytes);
                                out.println(ENCRYPT_msg);
                                out.flush();
                                if(content.equals("exit"))
                                {   
                                    messageArea.append("Vikas terminated chat");
                                    messageArea.setCaretPosition(messageArea.getDocument().getLength());
                                    messageInput.setEditable(false);
                                    socket.close();
                                    break;
                                }
                            }
                            
                        }
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }    
                
            };
            new Thread(r2).start();
        }
    
        private static String encrypt(String data, SecretKey key, byte[] iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
    
        private static String decrypt(String data, SecretKey key, byte[] iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(decryptedBytes);
        }
  
    public static void main(String[] args) 
    {
        System.out.println("This is Mohit");

        new Client();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
