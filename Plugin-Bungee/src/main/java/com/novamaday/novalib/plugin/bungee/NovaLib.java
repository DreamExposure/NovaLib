package com.novamaday.novalib.plugin.bungee;

import com.novamaday.novalib.api.NovaLibAPI;
import net.md_5.bungee.api.plugin.Plugin;

public class NovaLib extends Plugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPIForBungee(this);
    }

    @Override
    public void onDisable() {
        NovaLibAPI.getApi().shutdownAPI();
    }

    public static NovaLib getInstance() {
        return instance;
    }
}