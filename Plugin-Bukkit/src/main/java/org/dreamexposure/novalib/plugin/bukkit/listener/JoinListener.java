package org.dreamexposure.novalib.plugin.bukkit.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.update.UpdateChecker;
import org.dreamexposure.novalib.plugin.bukkit.NovaLib;

/**
 * @author NovaFox161
 * Date Created: 10/29/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class JoinListener implements Listener {
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission("novalib.update.notify") && NovaLibAPI.getApi().getBukkitConfig().get().getBoolean("Updates.Check")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(NovaLib.getInstance(), () -> UpdateChecker.init(NovaLib.getInstance(), 61835).requestUpdateCheck().whenComplete((result, exception) -> {
                if (result.requiresUpdate()) {
                    event.getPlayer().sendMessage(String.format("An update is available! NovaLib %s may be downloaded on SpigotMC", result.getNewestVersion()));
                    return;
                }
                
                UpdateChecker.UpdateReason reason = result.getReason();
                if (reason == UpdateChecker.UpdateReason.UP_TO_DATE) {
                    event.getPlayer().sendMessage(String.format("Your version of NovaLib (%s) is up to date!", result.getNewestVersion()));
                } else if (reason == UpdateChecker.UpdateReason.UNRELEASED_VERSION) {
                    event.getPlayer().sendMessage(String.format("Your version of NovaLib (%s) is more recent than the one publicly available. Are you on a development build?", result.getNewestVersion()));
                } else {
                    event.getPlayer().sendMessage("Could not check for a new version of NovaLib. Reason: " + reason);
                }
            }), 20L);
        }
    }
}
