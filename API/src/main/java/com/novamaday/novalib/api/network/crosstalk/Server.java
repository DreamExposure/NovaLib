package com.novamaday.novalib.api.network.crosstalk;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket sock;
    private static Socket client;

    public boolean start(int port) {
        try {
            sock = new ServerSocket(port);
            return true;
        } catch (Exception e) {
            System.out.println("[NovaLib] Failed to Init ServerSocket! Server crosstalk will not work!");
        }
        return false;
    }

    public void stop() {
        if (sock != null) {
            try {
                sock.close();
            } catch (Exception e) {
                System.out.println("[NovaLib] Failed to Shutdown socket properly! Connection must have forcibly closed!");
            }
        }
    }

    public void listen() {
        new Thread(() -> {
            while (true) {
                try {
                    client = sock.accept();
                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    String data = dis.readUTF();

                    //TODO: Convert to JSON and send to handler!

                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}