package org.dreamexposure.novalib.nms.v1_8_R1;

import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.packets.IArmorStand;

/**
 * @author NovaFox161
 * Date Created: 10/29/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class ArmorStand implements IArmorStand {
    @Override
    public void setNoClip(org.bukkit.entity.ArmorStand armorStand, boolean clip) {
        if (NovaLibAPI.getApi().debug())
            NovaLibAPI.getApi().getBukkitPlugin().getLogger().info("NO CLIP IS NOT SUPPORTED IN THIS MC VERSION!");
    }
}
