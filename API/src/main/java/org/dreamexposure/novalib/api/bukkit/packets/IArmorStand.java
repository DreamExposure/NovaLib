package org.dreamexposure.novalib.api.bukkit.packets;

import org.bukkit.entity.ArmorStand;

/**
 * @author NovaFox161
 * Date Created: 10/29/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
public interface IArmorStand {
    
    public void setNoClip(ArmorStand armorStand, boolean clip);
}