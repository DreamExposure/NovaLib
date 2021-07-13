package org.dreamexposure.novalib.nms.v1_17_R1;

import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.dreamexposure.novalib.api.bukkit.packets.IActionBar;

import java.util.UUID;

public class ActionBar implements IActionBar {
    @Override
    public void send(Player player, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        //I believe just assigning a random UUID should work. it appears to get reassigned anyway.
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.a((byte) 2), UUID.randomUUID());
        //TODO: send custom event maybe


        //b is PlayerConnection
        ((CraftPlayer) player).getHandle().b.sendPacket(bar);
    }
}
