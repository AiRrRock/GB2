package ru.geekbrains.march.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler {
    private static final String STAT_REQUEST = "stat";
    private static final String EXIT_REQUEST = "exit";
    private static final String IDENTITY_REQUEST = "who_am_i";
    private static final String PERSONAL_MSG_REQUEST = "w";
    //Already done, but the command differs, thus just renamed command
    private static final String CHANGE_NAME_REQUEST = "change_nick";


    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private int msgCounter = 0;

    public String getUsername() {
        return username;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) { // Цикл авторизации
                    String msg = in.readUTF();
                    if (msg.startsWith("/login ")) {
                        String[] cred = msg.split("\\s", 3);
                        if(cred.length < 3){
                            sendMessage("/login_failed Enter login and password");
                            continue;
                        }
                        String usernameFromLogin = cred[1];
                        String password = cred[2];

                        if (server.isUserOnline(usernameFromLogin)) {
                            sendMessage("/login_failed Current nickname is already used");
                            continue;
                        }
                        if (server.authenticate(usernameFromLogin,password)){
                            User u = server.getUser(usernameFromLogin);
                            username = u.getNickName();
                            sendMessage("/login_ok " + username);
                            server.subscribe(this);
                            break;
                        }

                    }
                }

                while (true) { // Цикл общения с клиентом
                    String msg = in.readUTF();
                    if (msg.startsWith("/")) {
                        processCommands(msg.substring(1));
                    } else {
                        msgCounter++;
                        server.broadcastMessage(username + ": " + msg);
                    }

                }
            } catch (SocketException sc) {
                System.out.println(username + " disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            disconnect();
        }
    }

    public void disconnect() {
        server.unsubscribe(this);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processCommands(String command) throws IOException {
        String[] strings = command.split("\\s");
        switch (command.toLowerCase().split("\\s")[0]) {
            case STAT_REQUEST:
                out.writeUTF("Number of messages sent - " + msgCounter);
                break;
            case EXIT_REQUEST:
                disconnect();
                break;
            case PERSONAL_MSG_REQUEST:
                String to = strings[1];
                StringBuilder msg = new StringBuilder();
                if (strings.length > 2) {
                    for (int i = 2; i < strings.length; i++) {
                        msg.append(strings[i]);
                        msg.append(" ");
                    }
                }
                msgCounter++;
                server.sendPrivateMessage(this, strings[1], msg.toString());
                break;
            case IDENTITY_REQUEST:
                this.sendMessage(username);
                break;
            // Homework part 3 was already done
            /*case CHANGE_NAME_REQUEST:
                if (strings.length > 1) {
                    String newName = strings[1];
                    if (server.isUserOnline(newName)) {
                        this.sendMessage(String.format("Unable to change name to %s, nickname is already in use", newName));
                    } else {
                        username = newName;
                        this.sendMessage(String.format("Changed name to %s", newName));

                    }

                }*/
            case CHANGE_NAME_REQUEST:
                if (strings.length > 1) {
                    String newName = strings[1];
                    if (server.isUserOnline(newName)) {
                        this.sendMessage(String.format("Unable to change name to %s, nickname is already in use", newName));
                    } else {
                        username = newName;
                        this.sendMessage(String.format("Changed name to %s", newName));

                    }

                }
        }
    }
}

