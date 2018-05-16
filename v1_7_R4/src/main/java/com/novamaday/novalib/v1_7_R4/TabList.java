package com.novamaday.novalib.v1_7_R4;

import com.novamaday.novalib.api.packets.ITabList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TabList implements ITabList {
    /**
     * Sends a new tab list to the player with the specified params.
     *
     * @param p      The player to send the tab list to.
     * @param header The header for the tablist.
     * @param footer The footer for the tablist.
     */
    @Override
    public void sendTablist(Player p, String header, String footer) {
        Bukkit.getServer().getLogger().warning("[NovaLib] Tablist headers/footers are not supported in 1.7.x!");
    }
}