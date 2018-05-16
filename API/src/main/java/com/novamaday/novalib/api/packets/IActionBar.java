package com.novamaday.novalib.api.packets;

import org.bukkit.entity.Player;

public interface IActionBar {
    void send(Player player, String message);
}