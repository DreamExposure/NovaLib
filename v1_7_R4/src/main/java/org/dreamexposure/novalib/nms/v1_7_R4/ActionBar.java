package org.dreamexposure.novalib.nms.v1_7_R4;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.dreamexposure.novalib.api.bukkit.packets.IActionBar;

public class ActionBar implements IActionBar {
    @Override
    public void send(Player player, String message) {
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, true);
        //TODO: send custom event maybe


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }
}