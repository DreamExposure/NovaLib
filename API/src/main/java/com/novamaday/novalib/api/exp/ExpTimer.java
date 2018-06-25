package com.novamaday.novalib.api.exp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ExpTimer {
    /**
     * Starts the EXP timer for the specified player with the data. <strong>You must handle reverting the player's EXP to their previous levels.</strong>
     *
     * @param data   The ExpTimerData to use.
     * @param plugin The plugin using the timer. (Registers the bukkit task with it).
     */
    public static void start(final ExpTimerData data, JavaPlugin plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            //Increment time by one...
            data.setCurrentTimeInSeconds(data.getCurrentTimeInSeconds() + 1);

            //Check if we should continue....
            if (data.getCurrentTimeInSeconds() < data.getLengthInSeconds()) {
                Player p = Bukkit.getPlayer(data.getId());

                //Check if player is online of course to prevent errors.
                if (p != null) {
                    if (data.getLengthInSeconds() - data.getCurrentTimeInSeconds() <= 60 && data.getUnit() == ExpTimerUnit.MINUTES)
                        data.setUnit(ExpTimerUnit.SECONDS);

                    if (data.getUnit() == ExpTimerUnit.SECONDS) {
                        p.setLevel(data.getLengthInSeconds() - data.getCurrentTimeInSeconds());
                        p.setExp((float) (data.getLengthInSeconds() / (data.getLengthInSeconds() - data.getCurrentTimeInSeconds())));

                    } else if (data.getUnit() == ExpTimerUnit.MINUTES) {
                        p.setLevel((data.getLengthInSeconds() - data.getCurrentTimeInSeconds()) / 60);
                        p.setExp((float) (data.getLengthInSeconds() / (data.getLengthInSeconds() - data.getCurrentTimeInSeconds())));
                    }
                }
            }
        }, 20L, 20L);
    }
}