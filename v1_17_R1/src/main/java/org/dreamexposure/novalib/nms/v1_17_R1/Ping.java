package org.dreamexposure.novalib.nms.v1_17_R1;

import org.bukkit.entity.Player;
import org.dreamexposure.novalib.api.bukkit.packets.IPing;

public class Ping implements IPing {
    /**
     * Gets the current ping of the player.
     *
     * @param p The player whose ping is to be gotten.
     * @return The current ping of the player in MS.
     */
    @Override
    public int getPing(Player p) {
        return p.getPing();
    }
}
