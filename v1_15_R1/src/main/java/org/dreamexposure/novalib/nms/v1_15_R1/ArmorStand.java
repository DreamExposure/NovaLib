package org.dreamexposure.novalib.nms.v1_15_R1;

import net.minecraft.server.v1_15_R1.Entity;
import net.minecraft.server.v1_15_R1.EntityArmorStand;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.dreamexposure.novalib.api.bukkit.packets.IArmorStand;

public class ArmorStand implements IArmorStand {
    @Override
    public void setNoClip(org.bukkit.entity.ArmorStand armorStand, boolean clip) {
        Entity armorEntity = ((CraftEntity) armorStand).getHandle();

        EntityArmorStand entityArmorStand = (EntityArmorStand) armorEntity;

        entityArmorStand.noclip = clip;
    }
}
