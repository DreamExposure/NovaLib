package com.novamaday.novalib.plugin.bungee;

import com.novamaday.novalib.api.NovaLibAPI;
import com.novamaday.novalib.api.network.crosstalk.client.ClientSocketHandler;
import net.md_5.bungee.api.plugin.Plugin;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class NovaLib extends Plugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPIForBungee(this);

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
        if (NovaLibAPI.getApi().getBungeeConfig().get().getBoolean("CrossTalk.Enabled") && !NovaLibAPI.getApi().getBungeeConfig().get().getBoolean("CrossTalk.Self as Server")) {
            ClientSocketHandler.sendToServer(this.getDescription().getName(), new JSONObject());

            getProxy().getScheduler().schedule(this, this::startKeepAlive, 5, TimeUnit.MINUTES);
        }
    }
}