package com.example.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {

    private int port;
    private Socket socket;

    public EchoClient(int port) {
        this.port = port;
    }

    public void start() {
        try {
            this.socket = new Socket("localhost", port);

            InetSocketAddress remote = new InetSocketAddress("localhost", port);


            socket.connect(remote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
