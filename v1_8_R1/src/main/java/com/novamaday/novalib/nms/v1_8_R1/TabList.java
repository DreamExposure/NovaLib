package com.novamaday.novalib.nms.v1_8_R1;

import com.novamaday.novalib.api.bukkit.packets.ITabList;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabList implements ITabList {
    /**
     * Sends a new tab list to the player with the specified params.
     *
     * @param p      The player to send the tab list to.
     * @param header The header for the tablist.
     * @param footer The footer for the tablist.
     */
    @Override
    public void sendTablist(Player p, String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();

        header = ChatColor.translateAlternateColorCodes('&', header);
        footer = ChatColor.translateAlternateColorCodes('&', footer);

        IChatBaseComponent headerComponent = ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent footerComponent = ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        try {
            Field headerField = headerFooterPacket.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(headerFooterPacket, headerComponent);
            headerField.setAccessible(false);

            Field footerField = headerFooterPacket.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(headerFooterPacket, footerComponent);
            footerField.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        connection.sendPacket(headerFooterPacket);
    }
}