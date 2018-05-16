package com.novamaday.novalib.nms.v1_8_R2;

import com.novamaday.novalib.api.packets.IPing;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping implements IPing {

    /**
     * Gets the current ping of the player.
     *
     * @param p The player whose ping is to be gotten.
     * @return The current ping of the player in MS.
     */
    @Override
    public int getPing(Player p) {
        return ((CraftPlayer) p).getHandle().ping;
    }
}