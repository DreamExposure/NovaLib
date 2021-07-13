package org.dreamexposure.nms.v1_16_R3;

import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.MinecraftKey;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.dreamexposure.novalib.api.bukkit.packets.IEntityTypes;

import java.lang.reflect.Field;

public class EntityTypes implements IEntityTypes {
    /**
     * Adds the custom Entity
     *
     * @param entityId    The ID of the entity.
     * @param entityName  The name of the entity.
     * @param entityClass The Entity class <bold>MUST EXTEND THE NMS ENTITY FOR THE VERSION IN USE!</bold>
     */
    @Override
    public void addCustomEntity(int entityId, String entityName, Class entityClass) {
        MinecraftKey minecraftKey = new MinecraftKey(entityName);

        //TODO: Figure this shit out... its way too early in 1.14's existence for people to know.
        net.minecraft.server.v1_16_R3.EntityTypes.Builder.a(net.minecraft.server.v1_16_R3.EnumCreatureType.valueOf(entityName));
        //net.minecraft.server.v1_13_R1.EntityTypes.b.a(entityId, minecraftKey, entityClass);
    }

    /**
     * Spawns the specified entity
     *
     * @param entityClass The Entity to spawn <bold>MUST EXTEND THE NMS ENTITY FOR THE VERSION IN USE!</bold>
     * @param loc         The location to spawn the entity at.
     */
    @Override
    public void spawnEntity(Object entityClass, Location loc) {
        Entity entity = (Entity) entityClass;
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(entity);
    }

    /**
     * Gets the specified field from class
     *
     * @param fieldName The name of the field.
     * @param clazz     The class to get the field from.
     * @param object    The object to return.
     * @return The object/field
     */
    @Override
    public Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;

        try {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}

