package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Server {
    ServerSocket server = null;
    Socket socket = null;
    DataInputStream in;
    DataOutputStream out;
    Scanner sc;

    public Server() {
        try {
            server = new ServerSocket(8189);
            System.out.println("Server is running!");
            socket = server.accept();
            System.out.println("Client connected!");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            out.writeUTF("Connect to server successfully!");
            out.flush();
        } catch (IOException e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("bye")) {
                            System.out.println("Closed connection!");
                            out.writeUTF("bye");
                            break;
                        }
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.flush();
                        socket.close();
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String msg = sc.nextLine();
                    try {
                        out.writeUTF(msg);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.setDaemon(true);
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ServerStart {
    public static void main(String[] args) {
        new Server();
    }
}