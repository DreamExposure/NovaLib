package org.dreamexposure.novalib.nms.v1_17_R1;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.dreamexposure.novalib.api.bukkit.packets.IArmorStand;

public class ArmorStand implements IArmorStand {
    @Override
    public void setNoClip(org.bukkit.entity.ArmorStand armorStand, boolean clip) {
        Entity armorEntity = ((CraftEntity) armorStand).getHandle();

        EntityArmorStand entityArmorStand = (EntityArmorStand) armorEntity;

        //P is #noclip variable
        entityArmorStand.P = clip;
    }
}
