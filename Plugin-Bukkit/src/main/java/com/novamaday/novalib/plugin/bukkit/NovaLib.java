package com.novamaday.novalib.plugin.bukkit;

import com.novamaday.novalib.api.NovaLibAPI;
import com.novamaday.novalib.api.network.crosstalk.client.ClientSocketHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

public class NovaLib extends JavaPlugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPIForBukkit(this);

        startKeepAlive();
    }

    @Override
    public void onDisable() {
        NovaLibAPI.getApi().shutdownAPI();
    }

    @SuppressWarnings("unused")
    public static NovaLib getInstance() {
        return instance;
    }

    private void startKeepAlive() {
        if (NovaLibAPI.getApi().getBukkitConfig().get().getBoolean("CrossTalk.Enabled")) {
            ClientSocketHandler.sendToServer(this, new JSONObject());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, this::startKeepAlive, 5 * 60 * 20L);
        }
    }
}