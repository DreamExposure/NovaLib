package org.dreamexposure.novalib.nms.v1_17_R1;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutOpenSignEditor;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.level.block.entity.TileEntitySign;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
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
        TileEntitySign tileEntitySign = (TileEntitySign) nmsPlayer.getWorld().getTileEntity(position);
        PlayerConnection connection = nmsPlayer.b;

        if (tileEntitySign == null)
            return;

        //TileEntitySign#f is editable field
        tileEntitySign.f = true;
        tileEntitySign.a(nmsPlayer);
        connection.sendPacket(new PacketPlayOutOpenSignEditor(position));
    }
}
