package ru.geekbrains.march.chat.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    // Домашнее задание:
    // 1. Разберитесь с кодом, все вопросы можно писать в комментариях к дз
    // 2. Пусть сервер подсчитывает количество сообщений от клиента
    // 3. Если клиент отправит команду '/stat', то сервер должен выслать клиенту
    // не эхо, а сообщение вида 'Количество сообщений - n'

    private static final String STAT_REQUEST = "stat";
    private static final String EXIT_REQUEST = "exit";


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен на порту 8189. Ожидаем подключение клиента...");
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Клиент подключился");
            long msgCounter = 0;

            while (true) {
                String msg = in.readUTF();
                if (msg.startsWith("/") && msg.length() >= 2) {
                    switch (msg.substring(1).toLowerCase()) {
                        case STAT_REQUEST:
                            out.writeUTF("Number of messages sent - " + msgCounter);
                            break;
                        case EXIT_REQUEST:
                            serverSocket.close();
                            System.exit(0);
                    }
                } else {
                    msgCounter++;
                    out.writeUTF("ECHO: " + msg);
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
