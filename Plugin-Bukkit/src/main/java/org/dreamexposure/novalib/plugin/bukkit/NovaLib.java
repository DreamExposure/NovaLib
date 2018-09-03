package org.dreamexposure.novalib.plugin.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.network.crosstalk.client.ClientSocketHandler;
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
        if (NovaLibAPI.getApi().getBukkitConfig().get().getBoolean("CrossTalk.Enabled") && !NovaLibAPI.getApi().getBukkitConfig().get().getBoolean("CrossTalk.Self as Server")) {
            ClientSocketHandler.sendToServer(this.getDescription().getName(), new JSONObject());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, this::startKeepAlive, 5 * 60 * 20L);
        }
    }
}