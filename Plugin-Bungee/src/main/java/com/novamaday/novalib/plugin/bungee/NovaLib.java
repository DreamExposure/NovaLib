package com.novamaday.novalib.plugin.bungee;

import com.novamaday.novalib.api.NovaLibAPI;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class NovaLib extends Plugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        //TODO: Handle API init.
        NovaLibAPI.getApi().initAPI(getPlugin());
    }

    @Override
    public void onDisable() {
        //TODO: Handle API shutdown.
        NovaLibAPI.getApi().shutdownAPI();
    }

    public static NovaLib getInstance() {
        return instance;
    }

    public static Plugin getPlugin() {
        return ProxyServer.getInstance().getPluginManager().getPlugin("NovaLib");
    }
}