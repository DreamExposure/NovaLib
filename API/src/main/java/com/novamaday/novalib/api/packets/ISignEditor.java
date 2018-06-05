package com.novamaday.novalib.api.packets;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * Handles NMS code for the sign editor
 */
@SuppressWarnings("unused")
public interface ISignEditor {
    /**
     * Opens the sign editor for the specified player and sign.
     *
     * @param player The player who will use the sign editor
     * @param sign   The sign to open.
     */
    void open(Player player, Sign sign);
}