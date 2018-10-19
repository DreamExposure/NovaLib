package org.dreamexposure.novalib.nms.v1_12_R1;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.dreamexposure.novalib.api.bukkit.holo.HologramEntity;
import org.dreamexposure.novalib.api.bukkit.packets.IHologram;

import java.util.*;

/**
 * @author NovaFox161
 * Date Created: 10/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings({"unused", "Duplicates"})
public class Hologram implements IHologram {

    private Map<Location, HologramEntity> registeredHolograms = new HashMap<>();

    public Hologram() {
        reloadHologram();
    }

    private void reloadHologram() {
        for (HologramEntity hologram : registeredHolograms.values()) {
            addHologram(hologram);
        }
    }

    @Override
    public HologramEntity addHologram(HologramEntity hologram) {
        if (hologram.getLocation().getWorld() == null || hologram.getLocation() == null) return null;
        registeredHolograms.put(hologram.getLocation(), hologram);


        double increment = 0;

        for (String line : hologram.getLines()) {
            Location location = hologram.getLocation().subtract(0, increment, 0);

            Collection<org.bukkit.entity.Entity> nearbyEntities = location.getWorld().getNearbyEntities(location, 1, 1, 1);
            nearbyEntities.removeIf(e -> e.getType() != EntityType.ARMOR_STAND || e.getLocation().getX() != location.getX() || e.getLocation().getY() != location.getY() || e.getLocation().getZ() != location.getZ());

            EntityArmorStand nmsEntity = new EntityArmorStand(((CraftWorld) hologram.getLocation().getWorld()).getHandle(), hologram.getLocation().getX(), hologram.getLocation().getY() - increment, hologram.getLocation().getZ());

            if (nearbyEntities.size() == 0) {
                removeEntityFromWorld(location);
            } else {
                nearbyEntities.iterator().next().setCustomName(line);
                increment += .25;
                continue;
            }

            nmsEntity.getBukkitEntity().setCustomName(line);
            nmsEntity.setCustomNameVisible(true);
            nmsEntity.setInvisible(true);
            nmsEntity.setNoGravity(true);
            nmsEntity.setSmall(true);
            nmsEntity.setMarker(true);
            nmsEntity.setBasePlate(true);

            WorldServer nmsWorld = ((CraftWorld) hologram.getLocation().getWorld()).getHandle();

            setPosition(nmsEntity, location);

            if (nearbyEntities.size() == 0) {
                nmsWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
            }

            increment += .25;
        }

        return hologram;
    }

    @Override
    public boolean removeHologram(HologramEntity hologram) {
        if (hologram == null)
            return false;
        registeredHolograms.remove(hologram.getLocation());
        removeHologram(hologram.getLocation(), hologram.getLines().size());
        return true;
    }

    @Override
    public void removeHologram(Location location, int lines) {
        if (location == null) return;

        double increment = 0;

        for (int i = 0; i < lines; i++) {
            Location location2 = location.clone().subtract(0, increment, 0);

            removeEntityFromWorld(location2);

            increment += .25;
        }
    }

    private void setPosition(Entity entity, Location location) {
        entity.setPosition(location.getX(), location.getY(), location.getZ());
        PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(entity);
        for (EntityHuman human : entity.world.players) {
            if (!(human instanceof EntityPlayer))
                continue;
            EntityPlayer player = (EntityPlayer) human;
            double distanceSquared = Math.pow(player.locX - entity.locX, 2) + Math.pow(player.locZ - entity.locZ, 2);
            if (distanceSquared < 8192 && player.playerConnection != null) {
                player.playerConnection.sendPacket(teleportPacket);
            }

        }
    }

    private void removeEntityFromWorld(Location location) {
        Collection<org.bukkit.entity.Entity> nearbyEntities = location.getWorld().getNearbyEntities(location, 2, 2, 2);
        for (org.bukkit.entity.Entity entity : nearbyEntities) {
            if (entity.getType() == EntityType.ARMOR_STAND) {
                if (location.getX() == entity.getLocation().getX() && location.getY() == entity.getLocation().getY() && location.getZ() == entity.getLocation().getZ()) {
                    entity.remove();
                }
            }
        }
    }

    @Override
    public HologramEntity getHologram(Location location) {
        return registeredHolograms.get(location);
    }

    @Override
    public HologramEntity getHologram(String name) {
        for (HologramEntity hologram : registeredHolograms.values()) {
            if (hologram.getName() != null && hologram.getName().equalsIgnoreCase(name))
                return hologram;
        }
        return null;
    }

    @Override
    public List<HologramEntity> getHolograms() {
        return new ArrayList<>(registeredHolograms.values());
    }
}