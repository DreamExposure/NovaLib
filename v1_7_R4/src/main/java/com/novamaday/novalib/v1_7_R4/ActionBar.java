package com.novamaday.novalib.v1_7_R4;

import com.novamaday.novalib.api.packets.IActionBar;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar implements IActionBar {
    @Override
    public void send(Player player, String message) {
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, true);
        //TODO: send custom event maybe


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }
}