import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;

class Client extends JFrame implements ActionListener
{
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    JPanel p;
    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("SAN_SERIf",Font.PLAIN,20);
    public String contenttosend;

    public Client()
    {
        try
        {
            System.out.println("Sending request to S ");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("Connection Established to Server side");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));//obtaining stream from server

            out=new PrintWriter(socket.getOutputStream());//writing stream to server
    
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

        ImageIcon v=new ImageIcon(ClassLoader.getSystemResource("imgs/v.png"));
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
                    contenttosend=messageInput.getText();
                    messageArea.append("Me :"+contenttosend+"\n");
                    out.println(contenttosend);
                    out.flush();
                    messageInput.setText("");
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
        
        messageArea.setLayout(null);
        messageArea.setBounds(0, 75, 460, 500);
        messageArea.setEditable(false);
        add(messageArea);
        messageInput.setLayout(null); 
        messageInput.setBounds(5,600,281,50);
        messageInput.setEditable(true);
        add(messageInput);
        contenttosend=messageInput.getText();

        this.setTitle("Vchat");
        ImageIcon icon = new ImageIcon("imgs/vch.jpg");
        this.setIconImage(icon.getImage());
        setSize(460,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(930,10);
        this.setVisible(true);
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
                        out.println(contenttosend);
                        out.flush();
                        messageInput.setText("");
                        
                    }
                    else{
                        socket.close();
                        messageInput.setEditable(false);
                        
                    }
                    } catch(Exception ee)
                    {
                        messageArea.append(ee.getMessage());
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
                        if(msg.equals("exit"))
                        {
                            // System.out.println("Server terminated the chat");
                            messageArea.append("Vikas terminated chat");
                            socket.close();
                            break;
                        }  
                        // System.out.println("Server :"+msg);
                        messageArea.append("Vikas :"+msg+"\n");
                    } 
                  
                }
                catch (Exception e) 
                    {
                        e.printStackTrace();   
                    }

            };
            new Thread(r1).start();
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
                            out.println(content);
                            out.flush();
                            if(content.equals("exit"))
                            {
                                socket.close();
                                break;
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