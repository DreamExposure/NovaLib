package com.novamaday.novalib.plugin;

import com.novamaday.novalib.api.NovaLibAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class NovaLib extends JavaPlugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPI(this);
    }

    @Override
    public void onDisable() {

    }

    public static NovaLib getInstance() {
        return instance;
    }
}