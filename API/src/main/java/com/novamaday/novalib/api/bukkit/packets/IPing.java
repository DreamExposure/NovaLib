package com.novamaday.novalib.api.bukkit.packets;

import org.bukkit.entity.Player;

/**
 * Handles NMS code for easily getting the Player's current ping in MS.
 */
@SuppressWarnings("unused")
public interface IPing {

    /**
     * Gets the current ping of the player.
     *
     * @param p The player whose ping is to be gotten.
     * @return The current ping of the player in MS.
     */
    int getPing(Player p);
}