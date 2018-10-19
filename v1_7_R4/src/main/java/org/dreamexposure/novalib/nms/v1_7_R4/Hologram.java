package org.dreamexposure.novalib.nms.v1_7_R4;

import org.bukkit.Location;
import org.dreamexposure.novalib.api.bukkit.holo.HologramEntity;
import org.dreamexposure.novalib.api.bukkit.packets.IHologram;

import java.util.List;

/**
 * @author NovaFox161
 * Date Created: 10/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 * <p>
 * Developers note: this class only exists for this NMS package to prevent eroneous errors.
 */
@SuppressWarnings({"unused", "Duplicates"})
public class Hologram implements IHologram {
    public Hologram() {
    }

    @Override
    public HologramEntity addHologram(HologramEntity hologram) {
        //Armor stands did not exist in 1.7
        return hologram;
    }

    @Override
    public boolean removeHologram(HologramEntity hologram) {
        //Armor stands did not exist in 1.7
        return false;
    }

    @Override
    public void removeHologram(Location location, int lines) {
        //Armor stands did not exist in 1.7
    }

    @Override
    public HologramEntity getHologram(Location location) {
        //Armor stands did not exist in 1.7
        return null;
    }

    @Override
    public HologramEntity getHologram(String name) {
        //Armor stands did not exist in 1.7
        return null;
    }

    @Override
    public List<HologramEntity> getHolograms() {
        //Armor stands did not exist in 1.7
        return null;
    }
}
