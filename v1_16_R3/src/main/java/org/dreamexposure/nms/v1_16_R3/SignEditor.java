package org.dreamexposure.nms.v1_16_R3;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.packets.ISignEditor;

public class SignEditor implements ISignEditor {
    @Override
    public void open(Player player, Sign sign) {
        //Check if chunk loaded
        if (!sign.getLocation().getChunk().isLoaded())
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(NovaLibAPI.getApi().getBukkitPlugin(), () -> sign.getLocation().getChunk().load());

        BlockPosition position = new BlockPosition(sign.getLocation().getX(), sign.getLocation().getY(), sign.getLocation().getZ());
        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        TileEntitySign tileEntitySign = (TileEntitySign) nmsPlayer.world.getTileEntity(position);
        PlayerConnection connection = nmsPlayer.playerConnection;

        if (tileEntitySign == null)
            return;

        tileEntitySign.isEditable = true;
        tileEntitySign.a(nmsPlayer);
        connection.sendPacket(new PacketPlayOutOpenSignEditor(position));
    }
}
