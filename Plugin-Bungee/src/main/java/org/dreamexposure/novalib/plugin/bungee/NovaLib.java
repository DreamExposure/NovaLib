package org.dreamexposure.novalib.plugin.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bungee.update.UpdateChecker;
import org.dreamexposure.novalib.api.network.crosstalk.client.ClientSocketHandler;
import org.dreamexposure.novalib.api.network.pubsub.PubSubManager;
import org.dreamexposure.novalib.plugin.bungee.listeners.PubSubListener;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class NovaLib extends Plugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPIForBungee(this);
    
        checkForUpdates();

        startKeepAlive();
    
        getProxy().getPluginManager().registerListener(this, new PubSubListener());
    
        if (NovaLibAPI.getApi().getBungeeConfig().get().getBoolean("Redis.PubSub.Enabled"))
            PubSubManager.get().register(getDescription().getName(), "NovaLib.Internal.ToBungee");
    }

    @Override
    public void onDisable() {
        NovaLibAPI.getApi().shutdownAPI();
    
        PubSubManager.get().unregisterAll(getDescription().getName());
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
    
    @SuppressWarnings("Duplicates")
    private void checkForUpdates() {
        if (NovaLibAPI.getApi().getBungeeConfig().get().getBoolean("Updates.Check")) {
            UpdateChecker.init(this, 61835).requestUpdateCheck().whenComplete((result, exception) -> {
                if (result.requiresUpdate()) {
                    this.getLogger().info(String.format("An update is available! NovaLib %s may be downloaded on SpigotMC", result.getNewestVersion()));
                    return;
                }
                
                UpdateChecker.UpdateReason reason = result.getReason();
                if (reason == UpdateChecker.UpdateReason.UP_TO_DATE) {
                    getLogger().info(String.format("Your version of NovaLib (%s) is up to date!", result.getNewestVersion()));
                } else if (reason == UpdateChecker.UpdateReason.UNRELEASED_VERSION) {
                    getLogger().info(String.format("Your version of NovaLib (%s) is more recent than the one publicly available. Are you on a development build?", result.getNewestVersion()));
                } else {
                    getLogger().warning("Could not check for a new version of NovaLib. Reason: " + reason);
                }
            });
        }
    }
}