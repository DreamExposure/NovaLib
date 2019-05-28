package org.dreamexposure.novalib.nms.v1_14_R1;

import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.dreamexposure.novalib.api.bukkit.packets.IPing;

/**
 * @author NovaFox161
 * Date Created: 5/4/2019
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
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
