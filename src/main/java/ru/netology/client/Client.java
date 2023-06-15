package ru.netology.client;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import ru.netology.core.PortLookup;
import ru.netology.core.Logger;

public class Client {
    private int port = PortLookup.portLookup();
    private String host = "localhost";
    private Scanner scanner = new Scanner(System.in);
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    Random random = new Random();
    private File log = new File(
            "C:\\IdeaProjects\\multithreading\\course-project\\log_for_client" +
                    random.nextInt(10000) + ".txt");

    public Client() throws IOException {
        this.clientSocket = new Socket(host, port);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String authorization = in.readLine();
        System.out.println(authorization);
        String name = scanner.nextLine();
        out.println(name);

        new Thread(() -> {
            while (true) {
                try {
                    String resp = in.readLine();
                    System.out.println(resp);
                    Logger.log(resp, log);
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                String answer = scanner.nextLine();
                out.println(answer);
            }
        }).start();

    }
}
