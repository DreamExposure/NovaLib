package org.dreamexposure.novalib.api.bukkit.packets;

import org.bukkit.Location;
import org.dreamexposure.novalib.api.bukkit.holo.HologramEntity;

import java.util.List;

/**
 * @author NovaFox161
 * Date Created: 10/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings("UnusedReturnValue")
public interface IHologram {
    /**
     * Gets a complete list of all holograms currently loaded.
     *
     * @return a complete list of all holograms currently loaded.
     */
    List<HologramEntity> getHolograms();

    /**
     * Gets a hologram with the specified name.
     *
     * @param name The name of the hologram to get.
     * @return The hologram with the specified name, otherwise null if the hologram does not exist.
     */
    HologramEntity getHologram(String name);

    /**
     * Gets a hologram at the specified location.
     *
     * @param loc The location of the hologram to get.
     * @return The hologram at the specified location, other null if the hologram does not exist.
     */
    HologramEntity getHologram(Location loc);

    /**
     * Adds a new hologram using the Object provided. <strong>Note: you are responsible for saving/deleting this data to disk to handle server restarts.</strong>
     *
     * @param hologram The hologram to create.
     * @return Returns the hologram that was newly created.
     */
    HologramEntity addHologram(HologramEntity hologram);

    /**
     * Removes the specified hologram. <strong>Note: you are responsible for saving/deleting this data to disk to handle server restarts.</strong>
     *
     * @param hologram The hologram to remove.
     * @return <code>true</code> if successful, otherwise <code>false</code>.
     */
    boolean removeHologram(HologramEntity hologram);

    void removeHologram(Location loc, int lines);
}