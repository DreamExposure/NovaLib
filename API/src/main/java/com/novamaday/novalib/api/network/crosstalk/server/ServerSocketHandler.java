package com.novamaday.novalib.api.network.crosstalk.server;

import com.novamaday.novalib.api.NovaLibAPI;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler {
    private static ServerSocket serverSocket;
    private static Thread listenerTread;

    private static boolean allowListen = false;

    public static boolean sendToAllClients(JSONObject data, String clientIp, String clientPort, String clientPlugin) {
        try {
            //Add the additional data we need so that the Bungee CrossTalk server knows where this is to go.
            JSONObject input = new JSONObject();

            input.put("Client-IP", clientIp);
            input.put("Client-Port", clientPort);
            input.put("Client-Plugin", clientPlugin);
            input.put("Data", data);

            //TODO: Get list of all clients!!

            //Init socket
            String hostname = NovaLibAPI.getApi().config.get().getString("HOSTNAME");
            int port = NovaLibAPI.getApi().config.get().getInt("PORT");
            Socket sock = new Socket(hostname, port);

            //Send data to Bungee CrossTalk Server
            DataOutputStream ds = new DataOutputStream(sock.getOutputStream());
            ds.writeUTF(input.toString());
            ds.close();
            sock.close();

            return true;
        } catch (Exception e) {
            System.out.println("[NovaLib] Failed to send Server CrossTalk Message");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Initiates  the Server CrossTalk Client Listener. This is automatically called by NovaLibAPI on server start and should not be called by any plugins ever!
     */
    public static void initListener() {
        try {
            serverSocket = new ServerSocket(NovaLibAPI.getApi().config.get().getInt("CrossTalk.Server.Port"));
        } catch (Exception e) {
            System.out.println("[NovaLib] Failed to start Server CrossTalk Server! Are you sure it was configured correctly?");
            e.printStackTrace();
            return;
        }

        allowListen = true;

        listenerTread = new Thread(() -> {
            while (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    Socket client = serverSocket.accept();

                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    String dataRaw = dis.readUTF();

                    JSONObject dataOr = new JSONObject(dataRaw);

                    //Parse
                    JSONObject data = new JSONObject(dataOr.getJSONObject("Data"));
                    String clientIp = dataOr.getString("Client-IP");
                    String clientPort = dataOr.getString("Client-Port");
                    String clientPlugin = dataOr.getString("Client-Plugin");

                    //Send to all clients!!!!
                    sendToAllClients(data, clientIp, clientPort, clientPlugin);

                    dis.close();
                    client.close();
                } catch (Exception e) {
                    System.out.println("[NovaLib] Failed to handle Server CrossTalk receive!");
                    e.printStackTrace();
                }
            }
        });
        listenerTread.setDaemon(true);
        listenerTread.start();
    }

    /**
     * Gracefully stops the CrossTalk Client Listener. This is automatically called by NovaLibAPI on server stop and should not be called by any plugins ever!
     */
    @SuppressWarnings("deprecation")
    public static void shutdownListener() {
        allowListen = false;
        listenerTread.stop();

        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (Exception e) {
                System.out.println("[NovaLib] Failed to close Server CrossTalk Receiver gracefully.");
                e.printStackTrace();
            }
        }
    }
}