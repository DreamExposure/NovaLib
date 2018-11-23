package org.dreamexposure.novalib.api.bukkit.minigames.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author NovaFox161
 * Date Created: 11/23/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class PlayerChecker {
    public static boolean hasEmptyInventory(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null)
                return false;
        }
        if (p.getInventory().getBoots() != null)
            return false;
        if (p.getInventory().getLeggings() != null)
            return false;
        if (p.getInventory().getChestplate() != null)
            return false;
        return p.getInventory().getHelmet() == null;
    }
}
