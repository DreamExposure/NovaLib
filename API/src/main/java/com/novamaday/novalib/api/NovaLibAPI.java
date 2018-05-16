package com.novamaday.novalib.api;

import com.novamaday.novalib.api.packets.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NovaLibAPI {
    private static NovaLibAPI instance;
    public JavaPlugin plugin;

    private NovaLibAPI() {
    } //Prevent initialization

    /**
     * Gets the current loaded instance of the API.
     *
     * @return The  current loaded instance of the API.
     */
    public static NovaLibAPI getApi() {
        if (instance == null)
            instance = new NovaLibAPI();
        return instance;
    }

    /**
     * Initializes all parts of the API. This is automatically handled on server boot and SHOULD NOT be called by any plugins.
     */
    public void initAPI(JavaPlugin _plugin) {
        plugin = _plugin;
        PacketManager.getManager().init(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]);
    }
}