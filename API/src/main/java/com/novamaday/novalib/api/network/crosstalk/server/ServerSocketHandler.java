package com.novamaday.novalib.api.network.crosstalk.server;

import com.novamaday.novalib.api.NovaLibAPI;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class ServerSocketHandler {
    private static ServerSocket serverSocket;
    private static Thread listenerTread;

    private static final List<ClientSocketData> clients = new ArrayList<>();

    public static boolean sendToAllClients(JSONObject data, String clientIp, int clientPort, String clientPlugin) {
        try {
            //Add the additional data we need so that the Bungee CrossTalk server knows where this is to go.
            JSONObject input = new JSONObject();

            input.put("Client-IP", clientIp);
            input.put("Client-Port", clientPort);
            input.put("Client-Plugin", clientPlugin);
            input.put("Data", data);

            //Send to all clients...
            for (ClientSocketData csd : clients) {
                //Don't send back to the client that just sent the message....
                if (csd.getPort() != clientPort && !csd.getIp().equals(clientIp)) {
                    try {
                        //Init socket
                        Socket sock = new Socket(csd.getIp(), csd.getPort());

                        //Send data to CrossTalk Client
                        DataOutputStream ds = new DataOutputStream(sock.getOutputStream());
                        ds.writeUTF(input.toString());
                        ds.close();
                        sock.close();
                    } catch (Exception e) {
                        //Failed to send to specific client....
                        if (NovaLibAPI.getApi().debug()) {
                            NovaLibAPI.getApi().getBungeePlugin().getLogger().warning("Failed to send CrossTalk Data to client...");
                            e.printStackTrace();
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
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
            serverSocket = new ServerSocket(NovaLibAPI.getApi().getBungeeConfig().get().getInt("CrossTalk.Server.Port"));
        } catch (Exception e) {
            NovaLibAPI.getApi().getBungeePlugin().getLogger().severe("Failed to start Server CrossTalk Server! Are you sure it was configured correctly?");
            e.printStackTrace();
            return;
        }

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
                    int clientPort = dataOr.getInt("Client-Port");
                    String clientPlugin = dataOr.getString("Client-Plugin");

                    if (clientPlugin.equalsIgnoreCase("NovaLib")) {
                        if (NovaLibAPI.getApi().verbose())
                            NovaLibAPI.getApi().getBungeePlugin().getLogger().info("CrossTalk KeepAlive message received!");

                        //Keep alive...
                        if (!isInList(clientIp, clientPort)) {
                            ClientSocketData csd = new ClientSocketData(clientIp, clientPort);
                            clients.add(csd);
                        }
                    } else {
                        //Send to all clients!!!!
                        sendToAllClients(data, clientIp, clientPort, clientPlugin);
                    }

                    dis.close();
                    client.close();
                } catch (Exception e) {
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
                NovaLibAPI.getApi().getBungeePlugin().getLogger().warning("Failed to close Server CrossTalk Receiver gracefully.");
                e.printStackTrace();
            }
        }
    }

    private static boolean isInList(String hostname, int port) {
        for (ClientSocketData cds : clients) {
            if (cds.getPort() == port && cds.getIp().equals(hostname))
                return true;
        }
        return false;
    }
}