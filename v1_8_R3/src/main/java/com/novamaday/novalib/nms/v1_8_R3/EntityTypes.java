package com.novamaday.novalib.nms.v1_8_R3;

import com.novamaday.novalib.api.packets.IEntityTypes;
import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        try {

            List<Map<?, ?>> dataMap = new ArrayList<>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }

            if (dataMap.get(2).containsKey(entityClass)) {
                dataMap.get(0).remove(entityClass);
                dataMap.get(2).remove(entityClass);
            }

            Method method = net.minecraft.server.v1_8_R3.EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, entityClass, entityName, entityId);

        } catch (Exception e) {
            e.printStackTrace();
        }
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