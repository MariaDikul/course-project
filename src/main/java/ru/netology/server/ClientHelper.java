package ru.netology.server;

import java.io.*;
import java.net.Socket;

import ru.netology.core.Logger;

public class ClientHelper implements Runnable {
    private Server server;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHelper(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            out.println("Введите свое имя");
            String name = in.readLine();
            String connectionMessage = name + " присоединился к чату";
            server.sendToAllClients(connectionMessage);
            Logger.log(connectionMessage, server.getLog());

            while (true) {
                String clientMessage = in.readLine();
                if (clientMessage.equals("/exit")) {
                    String shutdownMessage = name + " покинул чат";
                    server.sendToAllClients(shutdownMessage);
                    Logger.log(shutdownMessage, server.getLog());
                    server.removeClient(this);
                    break;
                }
                clientMessage = name + ": " + clientMessage;
                server.sendToAllClients(clientMessage);
                Logger.log(clientMessage, server.getLog());
            }
            Thread.sleep(100);
        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        } finally {
            this.close();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        server.removeClient(this);
    }
}


