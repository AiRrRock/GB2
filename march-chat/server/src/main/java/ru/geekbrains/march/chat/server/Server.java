package ru.geekbrains.march.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private Map<String, User> users;

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.users = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            users.put("u" + i, new User("u" + i, i + "pass", "Nickname" + i));
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту " + port);
            while (true) {
                System.out.println("Ждем нового клиента..");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastMessage("Client " + clientHandler.getUsername() + " logged in");
        broadcastClientsList();
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastMessage("Client " + clientHandler.getUsername() + " left");
        broadcastClientsList();
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(message);
        }
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String receiverUsername, String message) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(receiverUsername)) {
                c.sendMessage("From " + sender.getUsername() + ": " + message);
                sender.sendMessage("To " + receiverUsername + ": " + message);
                return;
            }
        }
        sender.sendMessage("Unable to send message to:" + receiverUsername + "User is offline");
    }

    public synchronized boolean isUserOnline(String username) {
        String name = username;
        if(users.containsKey(username)){
            name = users.get(username).getNickName();
        }
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastClientsList() {
        StringBuilder stringBuilder = new StringBuilder("/clients_list ");
        for (ClientHandler c : clients) {
            stringBuilder.append(c.getUsername()).append(" ");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        String clientsList = stringBuilder.toString();
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(clientsList);
        }
    }

    public boolean authenticate(String login, String password) {
        if(users.containsKey(login)){
            return users.get(login).getPassword().equals(password);
        }
        return false;
    }

    public User getUser(String name) {
        return users.get(name);
    }
}
