package com.novamaday.novalib.api.network.crosstalk;

import org.bukkit.Bukkit;

import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static boolean send(String data) {
        try {
            //TODO: GET HOST AND CLIENT FROM CONFIG!!!
            Socket client = new Socket("HOSTNAME", 1234);

            DataOutputStream ds = new DataOutputStream(client.getOutputStream());
            ds.writeUTF(data);
            ds.close();
            client.close();

            return true;
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to send crosstalk data to Bungee Host!");
            e.printStackTrace();
        }
        return false;
    }
}