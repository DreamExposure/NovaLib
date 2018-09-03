package org.dreamexposure.novalib.api.network.crosstalk.client;

import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.events.network.crosstalk.CrossTalkReceiveEvent;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"unused", "Duplicates"})
public class ClientSocketHandler {
    private static ServerSocket serverSocket;
    private static Thread listenerTread;

    /**
     * Send the specified message to the CrossTalk Server
     *
     * @param pluginName The plugin sending the message (<code>Plugin#getName()</code>)
     * @param data   The data to send.
     * @return <code>true</code> if successful, else <code>false</code>.
     */
    public static boolean sendToServer(String pluginName, JSONObject data) {
        try {
            //Add the additional data we need so that the Bungee CrossTalk server knows where this is to go.
            JSONObject input = new JSONObject();
            Socket sock;

            if (NovaLibAPI.getApi().isBukkit()) {
                input.put("Client-IP", NovaLibAPI.getApi().getBukkitConfig().get().getString("CrossTalk.Client.Hostname"));
                input.put("Client-Port", NovaLibAPI.getApi().getBukkitConfig().get().getInt("CrossTalk.Client.Port"));
                input.put("Client-Plugin", pluginName);
                input.put("Data", data);

                //Init socket
                String hostname = NovaLibAPI.getApi().getBukkitConfig().get().getString("CrossTalk.Server.Hostname");
                int port = NovaLibAPI.getApi().getBukkitConfig().get().getInt("CrossTalk.Server.Port");
                sock = new Socket(hostname, port);
            } else {
                input.put("Client-IP", NovaLibAPI.getApi().getBungeeConfig().get().getString("CrossTalk.Client.Hostname"));
                input.put("Client-Port", NovaLibAPI.getApi().getBungeeConfig().get().getInt("CrossTalk.Client.Port"));
                input.put("Client-Plugin", pluginName);
                input.put("Data", data);

                //Init socket
                String hostname = NovaLibAPI.getApi().getBungeeConfig().get().getString("CrossTalk.Server.Hostname");
                int port = NovaLibAPI.getApi().getBungeeConfig().get().getInt("CrossTalk.Server.Port");
                sock = new Socket(hostname, port);
            }

            //Send data to CrossTalk Server
            DataOutputStream ds = new DataOutputStream(sock.getOutputStream());
            ds.writeUTF(input.toString());
            ds.close();
            sock.close();

            return true;
        } catch (Exception e) {
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().severe("Failed to send Server CrossTalk Message");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().severe("Failed to send Server CrossTalk Message");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Initiates  the Server CrossTalk Client Listener. This is automatically called by NovaLibAPI on server start and should not be called by any plugins ever!
     */
    public static void initListener() {
        try {
            if (NovaLibAPI.getApi().isBukkit())
                serverSocket = new ServerSocket(NovaLibAPI.getApi().getBukkitConfig().get().getInt("CrossTalk.Client.Port"));
            else
                serverSocket = new ServerSocket(NovaLibAPI.getApi().getBungeeConfig().get().getInt("CrossTalk.Client.Port"));

        } catch (Exception e) {
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().severe("Failed to start Server CrossTalk Client! Are you sure it was configured correctly?");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().severe("Failed to start Server CrossTalk Client! Are you sure it was configured correctly?");
            e.printStackTrace();
            return;
        }

        listenerTread = new Thread(() -> {
            while (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    Socket client = serverSocket.accept();

                    if (NovaLibAPI.getApi().verbose()) {
                        if (NovaLibAPI.getApi().isBukkit())
                            NovaLibAPI.getApi().getBukkitPlugin().getLogger().info("Received CrossTalk Message from Server!");
                        else
                            NovaLibAPI.getApi().getBungeePlugin().getLogger().info("Received CrossTalk Message from Server!");
                    }

                    DataInputStream dis = new DataInputStream(client.getInputStream());
                    String dataRaw = dis.readUTF();

                    JSONObject dataOr = new JSONObject(dataRaw);

                    //Parse
                    JSONObject data = new JSONObject(dataOr.getJSONObject("Data"));
                    String clientIp = dataOr.getString("Client-IP");
                    String clientPlugin = dataOr.getString("Client-Plugin");

                    //Send event so plugins can use.
                    if (NovaLibAPI.getApi().isBukkit()) {
                        CrossTalkReceiveEvent event = new CrossTalkReceiveEvent(data, clientIp, clientPlugin);
                        NovaLibAPI.getApi().getBukkitPlugin().getServer().getPluginManager().callEvent(event);
                    } else {
                        org.dreamexposure.novalib.api.bungee.events.network.crosstalk.CrossTalkReceiveEvent event = new org.dreamexposure.novalib.api.bungee.events.network.crosstalk.CrossTalkReceiveEvent(data, clientIp, clientPlugin);
                        NovaLibAPI.getApi().getBungeePlugin().getProxy().getPluginManager().callEvent(event);
                    }

                    dis.close();
                    client.close();
                } catch (Exception e) {
                    if (NovaLibAPI.getApi().isBukkit())
                        NovaLibAPI.getApi().getBukkitPlugin().getLogger().severe("Failed to handle Server CrossTalk receive!");
                    else
                        NovaLibAPI.getApi().getBungeePlugin().getLogger().severe("Failed to handle Server CrossTalk receive!");
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
        listenerTread.stop();
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (Exception e) {
                if (NovaLibAPI.getApi().isBukkit())
                    NovaLibAPI.getApi().getBukkitPlugin().getLogger().warning("Failed to close Server CrossTalk Receiver gracefully.");
                else
                    NovaLibAPI.getApi().getBungeePlugin().getLogger().warning("Failed to close Server CrossTalk Receiver gracefully.");
                e.printStackTrace();
            }
        }
    }
}