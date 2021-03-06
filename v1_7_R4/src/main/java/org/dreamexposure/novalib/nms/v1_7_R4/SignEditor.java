package org.dreamexposure.novalib.nms.v1_7_R4;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import net.minecraft.server.v1_7_R4.TileEntitySign;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.packets.ISignEditor;

public class SignEditor implements ISignEditor {
    @Override
    public void open(Player player, Sign sign) {
        Location loc = sign.getLocation();

        //Check if chunk loaded
        if (!loc.getChunk().isLoaded())
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(NovaLibAPI.getApi().getBukkitPlugin(), () -> loc.getChunk().load());

        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        TileEntitySign tileEntitySign = (TileEntitySign) nmsPlayer.world.getTileEntity(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        PlayerConnection conn = nmsPlayer.playerConnection;

        tileEntitySign.isEditable = true;
        tileEntitySign.a(nmsPlayer);
        conn.sendPacket(new PacketPlayOutOpenSignEditor(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
    }
}