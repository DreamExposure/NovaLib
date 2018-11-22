package org.dreamexposure.novalib.plugin.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.update.UpdateChecker;
import org.dreamexposure.novalib.api.network.crosstalk.client.ClientSocketHandler;
import org.dreamexposure.novalib.api.network.pubsub.PubSubManager;
import org.dreamexposure.novalib.plugin.bukkit.listener.JoinListener;
import org.dreamexposure.novalib.plugin.bukkit.listener.PubSubListener;
import org.json.JSONObject;

public class NovaLib extends JavaPlugin {
    private static NovaLib instance;

    @Override
    public void onEnable() {
        instance = this;

        NovaLibAPI.getApi().initAPIForBukkit(this);
    
        //Register commands
    
        //Register listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new PubSubListener(), this);
    
    
        //Finish up
        checkForUpdates();

        startKeepAlive();
    
        if (NovaLibAPI.getApi().getBukkitConfig().get().getBoolean("Redis.PubSub.Enabled"))
            PubSubManager.get().register(getName(), "NovaLib.Internal.ToBukkit");
    }

    @Override
    public void onDisable() {
        NovaLibAPI.getApi().shutdownAPI();
    
        PubSubManager.get().unregisterAll(getName());
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
    
    @SuppressWarnings("Duplicates")
    private void checkForUpdates() {
        if (NovaLibAPI.getApi().getBukkitConfig().get().getBoolean("Updates.Check")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> UpdateChecker.init(this, 61835).requestUpdateCheck().whenComplete((result, exception) -> {
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
            }), 20L);
        }
    }
}