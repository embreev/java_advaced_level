package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket = null;
    DataInputStream in;
    DataOutputStream out;
    Scanner sc;
    final String SERVER_ADDRESS = "localhost";
    final int SERVER_PORT = 8189;
    boolean flag = true;

    public Client() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

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
                            flag = false;
                            socket.close();
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
                    while (flag) {
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

        } catch (IOException e) {
            System.out.println("Connection error!");
            e.printStackTrace();
        } finally {

        }
    }
}

class ClientStart {
    public static void main(String[] args) {
        new Client();
    }
}
