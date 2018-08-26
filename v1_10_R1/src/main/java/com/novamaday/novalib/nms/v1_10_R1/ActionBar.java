package com.novamaday.novalib.nms.v1_10_R1;

import com.novamaday.novalib.api.bukkit.packets.IActionBar;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar implements IActionBar {
    @Override
    public void send(Player player, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        //TODO: send custom event maybe


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }
}