package org.dreamexposure.nms.v1_16_R3;

import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.EntityArmorStand;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.dreamexposure.novalib.api.bukkit.packets.IArmorStand;

public class ArmorStand implements IArmorStand {
    @Override
    public void setNoClip(org.bukkit.entity.ArmorStand armorStand, boolean clip) {
        Entity armorEntity = ((CraftEntity) armorStand).getHandle();

        EntityArmorStand entityArmorStand = (EntityArmorStand) armorEntity;

        entityArmorStand.noclip = clip;
    }
}
