package com.novamaday.novalib.api.network.crosstalk.client;

import com.novamaday.novalib.api.NovaLibAPI;
import com.novamaday.novalib.api.events.network.crosstalk.CrossTalkReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSocketHandler {
    private static ServerSocket serverSocket;
    private static Thread listenerTread;

    /**
     * Send the specified message to the Bungee CrossTalk Server
     *
     * @param plugin The plugin sending the message
     * @param data   The data to send.
     * @return <code>true</code> if successful, else <code>false</code>.
     */
    public static boolean sendToServer(JavaPlugin plugin, JSONObject data) {
        try {
            //Add the additional data we need so that the Bungee CrossTalk server knows where this is to go.
            JSONObject input = new JSONObject();

            input.put("Client-IP", NovaLibAPI.getApi().config.get().getString("CrossTalk.Client.Hostname"));
            input.put("Client-Port", NovaLibAPI.getApi().config.get().getInt("CrossTalk.Client.Port"));
            input.put("Client-Plugin", plugin.getName());
            input.put("Data", data);

            //Init socket
            String hostname = NovaLibAPI.getApi().config.get().getString("CrossTalk.Server.Hostname");
            int port = NovaLibAPI.getApi().config.get().getInt("CrossTalk.Server.Port");
            Socket sock = new Socket(hostname, port);

            //Send data to Bungee CrossTalk Server
            DataOutputStream ds = new DataOutputStream(sock.getOutputStream());
            ds.writeUTF(input.toString());
            ds.close();
            sock.close();

            return true;
        } catch (Exception e) {
            Bukkit.getServer().getLogger().severe("[NovaLib] Failed to send Server CrossTalk Message");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Initiates  the Server CrossTalk Client Listener. This is automatically called by NovaLibAPI on server start and should not be called by any plugins ever!
     */
    public static void initListener() {
        try {
            serverSocket = new ServerSocket(NovaLibAPI.getApi().config.get().getInt("CrossTalk.Client.Port"));

        } catch (Exception e) {
            Bukkit.getServer().getLogger().severe("[NovaLib] Failed to start Server CrossTalk Client! Are you sure it was configured correctly?");
            e.printStackTrace();
            return;
        }

        listenerTread = new Thread(() -> {
            while (true) {
                try {
                    Socket client = serverSocket.accept();

                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    String dataRaw = dis.readUTF();

                    JSONObject dataOr = new JSONObject(dataRaw);

                    //Parse
                    JSONObject data = new JSONObject(dataOr.getJSONObject("Data"));
                    String clientIp = dataOr.getString("Client-IP");
                    String clientPlugin = dataOr.getString("Client-Plugin");

                    //Send event so plugins can use.
                    CrossTalkReceiveEvent event = new CrossTalkReceiveEvent(data, clientIp, clientPlugin);
                    Bukkit.getPluginManager().callEvent(event);

                    dis.close();
                    client.close();
                } catch (Exception e) {
                    Bukkit.getServer().getLogger().severe("[NovaLib] Failed to handle Server CrossTalk receive!");
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
    public static void shutdownListener() {
        if (serverSocket != null) {
            try {
                listenerTread.interrupt();
                serverSocket.close();
            } catch (Exception e) {
                Bukkit.getServer().getLogger().warning("[NovaLib] Failed to close Server CrossTalk Receiver gracefully.");
                e.printStackTrace();
            }
        }
    }
}