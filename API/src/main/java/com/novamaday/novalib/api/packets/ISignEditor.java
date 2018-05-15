package com.novamaday.novalib.api.packets;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public interface ISignEditor {
    void open(Player player, Sign sign);
}