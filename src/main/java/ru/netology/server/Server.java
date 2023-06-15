package ru.netology.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ru.netology.core.PortLookup;

public class Server {
    private ArrayList<ClientHelper> clients = new ArrayList<>();
    int port = PortLookup.portLookup();
    private File log = new File("C:\\IdeaProjects\\multithreading\\course-project\\log.txt");

    public Server() throws IOException {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            log.createNewFile();
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен!");
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHelper client = new ClientHelper(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendToAllClients(String msg) {
        for (ClientHelper client : clients) {
            client.sendMsg(msg);
        }
    }

    public void removeClient(ClientHelper client) {
        clients.remove(client);
    }

    public File getLog() {
        return log;
    }
}

