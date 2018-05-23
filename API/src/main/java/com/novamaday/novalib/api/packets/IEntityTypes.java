package com.novamaday.novalib.api.packets;

import org.bukkit.Location;

public interface IEntityTypes {

    /**
     * Adds the custom Entity
     *
     * @param entityId    The ID of the entity.
     * @param entityName  The name of the entity.
     * @param entityClass The Entity class <strong>MUST EXTEND THE NMS ENTITY FOR THE VERSION IN USE!</strong>
     */
    void addCustomEntity(int entityId, String entityName, Class entityClass);

    /**
     * Spawns the specified entity
     *
     * @param entityClass The Entity to spawn <strong>MUST EXTEND THE NMS ENTITY FOR THE VERSION IN USE!</strong>
     * @param loc         The location to spawn the entity at.
     */
    void spawnEntity(Object entityClass, Location loc);

    /**
     * Gets the specified field from class
     *
     * @param fieldName The name of the field.
     * @param clazz     The class to get the field from.
     * @param object    The object to return.
     * @return The object/field
     */
    Object getPrivateField(String fieldName, Class clazz, Object object);
}