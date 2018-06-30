package com.novamaday.novalib.api.minigames.regeneration;

import com.novamaday.novalib.api.NovaLibAPI;
import com.novamaday.novalib.api.bukkit.region.Cuboid;
import com.novamaday.novalib.api.file.FileUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class Regenerator {
    private static Regenerator instance;

    private final HashMap<Location, BlockState> originalBlocks = new HashMap<>();

    private Regenerator() {
    } //Prevent initialization

    /**
     * Gets the currently loaded instance of the Regenerator.
     *
     * @return The currently loaded instance of the Regenerator.
     */
    public static Regenerator getInstance() {
        if (instance == null)
            instance = new Regenerator();
        return instance;
    }

    /**
     * Saves the world files to a backup on disk.
     * @param world The world to backup.
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    public boolean saveWorldBackup(World world) {
        String name = world.getName();
        if (Bukkit.getServer().getWorld(name) == null)
            Bukkit.getServer().createWorld(new WorldCreator(name));

        world.save();

        File srcFolder = Bukkit.getWorld(name).getWorldFolder();
        File destFolder = new File(NovaLibAPI.getApi().getBukkitPlugin().getDataFolder() + "/Backups/Worlds/" + name);
        destFolder.mkdirs();
        //make sure source exists
        if (!srcFolder.exists()) {
            return false;
        } else {
            try {
                FileUtils.copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        world.setAutoSave(false);
        return true;
    }

    /**
     * Saves all blocks in the region to memory
     * @param originalRegenArea The region to backup.
     * @param toMemory ignore this, shouldn't be there. Sorry.
     */
    public void saveAllBlocksToMemory(Cuboid originalRegenArea, boolean toMemory) {
        for (Block block : originalRegenArea.getBlocks()) {
            Location loc = block.getLocation();
            BlockState state = block.getState();
            originalBlocks.put(loc, state);
        }
    }

    /**
     * Reloads the world from the backup on disk..
     * @param world The World to restore.
     * @param quitLocation The location to teleport players that are in the world.
     * @param deleteAfterReload Whether or not to delete the world backup after the restart.
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    public boolean reloadWorldFromBackup(World world, Location quitLocation, boolean deleteAfterReload) {
        String name = world.getName();
        if (world.getPlayers().size() > 0) {
            for (Player p : world.getPlayers()) {
                p.teleport(quitLocation);
            }
        }
        File destFolder = Bukkit.getWorld(name).getWorldFolder();
        File srcFolder = new File(NovaLibAPI.getApi().getBukkitPlugin().getDataFolder() + "/Backups/Worlds/" + name);

        Bukkit.getServer().unloadWorld(world, false);

        if (!srcFolder.exists()) {
            return false;
        } else {
            try {
                FileUtils.copyFolder(srcFolder, destFolder);
                if (deleteAfterReload)
                    FileUtils.deleteFile(srcFolder);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        Bukkit.getServer().createWorld(new WorldCreator(name));
        return true;
    }

    /**
     * Regenerates all blocks from memory.
     * @param regenArea The region to restore.
     */
    public void regenAllBlocksFromMemory(Cuboid regenArea) {
        for (Block block : regenArea.getBlocks()) {
            Location loc = block.getLocation();
            if (originalBlocks.containsKey(loc)) {
                BlockState originalBlock = originalBlocks.get(loc);
                int bId = originalBlock.getTypeId();
                byte blockDat = originalBlock.getRawData();
                block.setTypeIdAndData(bId, blockDat, false);
            }
        }
    }

    /**
     * Clears all items on the ground.
     * @param regenArea The region to clear items from.
     */
    public void clearGroundItems(Cuboid regenArea) {
        List<Entity> entList = regenArea.getWorld().getEntities();
        for (Entity entity : entList) {
            if (entity instanceof Item) {
                if (regenArea.contains(entity.getLocation()))
                    entity.remove();
            }
        }
    }

    /**
     * Clears all non-player/item entities from the region.
     * @param regenArea The region to clear entities from.
     */
    public void removeAllEntities(Cuboid regenArea) {
        List<Entity> entList = regenArea.getWorld().getEntities();
        for (Entity entity : entList) {
            if (entity instanceof Creature && !(entity instanceof Player)) {
                if (regenArea.contains(entity.getLocation()))
                    entity.remove();
            }
        }
    }

    /**
     * Closes all doors or openable blocks in the region.
     * @param regenArea The region to close all openable blocks in.
     */
    public void resetDoors(Cuboid regenArea) {
        for (Block block : regenArea.getBlocks()) {
            if (block == null || block.getType().equals(Material.AIR))
                continue;

            Material mat = block.getType();
            if (isOpenable(mat)) {

                BlockState state = block.getState();
                Openable o = (Openable) state.getData();
                o.setOpen(false);
                state.setData((MaterialData) o);
                state.update();
            }
        }
    }

    private boolean isOpenable(Material mat) {
        return mat.equals(Material.ACACIA_DOOR) || mat.equals(Material.BIRCH_DOOR) || mat.equals(Material.DARK_OAK_DOOR)
                || mat.equals(Material.SPRUCE_DOOR) || mat.equals(Material.JUNGLE_DOOR) || mat.equals(Material.IRON_DOOR)
                || mat.equals(Material.WOODEN_DOOR) || mat.equals(Material.TRAP_DOOR) || mat.equals(Material.FENCE_GATE)
                || mat.equals(Material.ACACIA_FENCE_GATE) || mat.equals(Material.BIRCH_FENCE_GATE) || mat.equals(Material.DARK_OAK_FENCE_GATE)
                || mat.equals(Material.SPRUCE_FENCE_GATE) || mat.equals(Material.JUNGLE_FENCE_GATE) || mat.equals(Material.IRON_TRAPDOOR);
    }
}