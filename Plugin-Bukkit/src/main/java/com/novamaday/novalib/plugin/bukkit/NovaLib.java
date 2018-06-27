package com.novamaday.novalib.plugin.bukkit;

import com.novamaday.novalib.api.NovaLibAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class NovaLib extends JavaPlugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPIForBukkit(this);
    }

    @Override
    public void onDisable() {
        NovaLibAPI.getApi().shutdownAPI();
    }

    public static NovaLib getInstance() {
        return instance;
    }
}