package com.example.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class EchoServer {

    private String host = "localhost";
    private int port = 8090;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            InetSocketAddress address = new InetSocketAddress(host, port);
            ServerSocket serverSocket = new ServerSocket();

            serverSocket.bind(address);

            Socket socket = null;
            while ((socket = serverSocket.accept()) != null) {
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        private ClientHandler(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            try {
                DataInputStream readStream = new DataInputStream(socket.getInputStream());
                String msg = readStream.readUTF();

                DataOutputStream writeStream = new DataOutputStream(socket.getOutputStream());
                if("hello".equalsIgnoreCase(msg)) {
                    writeStream.writeUTF("I got it");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(socket != null && !socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
