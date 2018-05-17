package com.novamaday.novalib.api.packets;

import org.bukkit.entity.Player;

public interface IActionBar {

    /**
     * Sends the player a message via the action bar
     *
     * @param player  The player to send the message to
     * @param message The message to send to the player.
     */
    void send(Player player, String message);
}