# 📲📲 VChat - Peer-to-Peer Chat Application  

VChat is a simple yet powerful Java-based peer-to-peer chat application that allows users to communicate over a network. It features a graphical user interface (GUI) built with Java Swing and supports basic messaging and multimedia functionality.

## Cool Features ✨

- **Encrypted Chat**: Messages are locked with AES-128.  
- **File Sharing**: Send `.jpg`, `.png`, `.mp4`, or `.mp3` files.  
- **GUI**: Built with Swing—looks clean and works smooth.  
- **Public IP**: Shows your server’s IP to share easily.  
- **Real-Time**: Instant messaging and file transfers via sockets.

## How to Use .EXE 🛠️

1. **Download**: Grab `Server.exe` .  
2. **Run**: Double-click—it’ll show your public IP(In case this doesnt work type "ip-config" in CMD and use ipv4 as your IP).  
3. **Connect**: Share the IP with a Vchat Client.  
4. **Chat**: Type and press Enter or click the icon.  
5. **Files**: Click the gallery icon to send media.

**Disclaimer**: If server and client aren’t on the same network (like same Wi-Fi), use the server’s public IP and allow port `7777` in your firewall!

## Encryption 🔐

- **AES-128**: 128-bit key for secure messages.  
- **Files**: Sent raw (not encrypted yet—working on it!).

## Java Concepts Used ☕

- **OOP**: Classes, inheritance (`extends JFrame`), encapsulation.  
- **Networking**: `ServerSocket`, `Socket` for connections.  
- **Multithreading**: `Runnable` and `Thread` for reading/writing.  
- **GUI**: Swing (`JFrame`, `JTextArea`, etc.) with event handling.  
- **Encryption**: `Cipher`, `KeyGenerator`, `SecureRandom`.  
- **I/O**: Streams (`BufferedReader`, `DataOutputStream`, etc.).  

## 📸 Screenshots  

### 1. Server Option Pane
![Server option pane](https://github.com/user-attachments/assets/430b579a-09cf-4405-a167-bde4b77568ed)

### 2. Client Window to enter server details
![Client](https://github.com/user-attachments/assets/90b36ca2-cdc2-44a7-a21c-79dfcb653fd8)


### 3. Chatting Window 
![Mohithii](https://github.com/user-attachments/assets/59cea729-d567-4505-bbec-f8483e14649c)
![VikasHii](https://github.com/user-attachments/assets/2aced9f2-a422-4453-bb9b-4a1752a7647b)

### 4. Status Bar
![Server 2](https://github.com/patilvikas580/V-chat-peer-to-peer-chatting-application-using-Java/assets/84447249/37a622e7-a50c-4872-aec1-edb2a1fbc7fe)
![Server 8](https://github.com/patilvikas580/V-chat-peer-to-peer-chatting-application-using-Java/assets/84447249/f04d226a-e7f1-4221-9cc0-bcce0d771902)

### 5. Sending Files
![Mohit Doc](https://github.com/user-attachments/assets/dd5c4df6-c070-4bf6-b39b-619613b265c6)

### 6.Opening Profile in new window when click on it
![Server 3](https://github.com/patilvikas580/V-chat-peer-to-peer-chatting-application-using-Java/assets/84447249/9a433ab0-ce58-4182-be82-e5b8f3ba8306)
![Server 6](https://github.com/patilvikas580/V-chat-peer-to-peer-chatting-application-using-Java/assets/84447249/a2024bc2-375a-47e7-b6f7-45a697081fe8)
### Opening Info window when we click on it(as of now it is just a picture will be updating soon with elements)
![Server 4](https://github.com/patilvikas580/V-chat-peer-to-peer-chatting-application-using-Java/assets/84447249/5fec6bac-bb03-46c9-aa9c-c9103d1b8a6d)
![Server7](https://github.com/patilvikas580/V-chat-peer-to-peer-chatting-application-using-Java/assets/84447249/2270c201-f91d-4e55-9b98-c3ad1952a45b)

## Bugs 🐛

- File sends might crash—still debugging!  
- One client at a time for now.


## 🚀 How to Run  

### Prerequisites  
Ensure you have **Java JDK 8+** installed on your system.  

### Steps to Run the Application  

1. **Clone the repository**  
   ```bash
   git clone https://github.com/patilvikas580/VChat.git
   cd VChat
   ```
2. **Compile and Run the Server**  
   Open a terminal in the project folder and run:  
   ```bash
   javac Server.java
   java Server
   ```
3. **Compile and Run the Client**  
   In another terminal window, run:  
   ```bash
   javac Client.java
   java Client
   ```
4. Start chatting! 🎉  

## 🛠️ Future Enhancements  
🔹 Emojis and multimedia support  
🔹 Improved UI with dark mode  

 
## Help Out? 🤝

Fork it, tweak it, send a PR—I’d love some collab! 😊

## 🏆 Credits 
## Who Made This? 👋

Vikas Patil—just a dude into secure chats. Made with ❤️.

Try Vchat out! Let me know if it glitches—I’ll sort it! 🎉








