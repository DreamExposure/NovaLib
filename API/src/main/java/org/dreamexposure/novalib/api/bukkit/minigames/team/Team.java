package org.dreamexposure.novalib.api.bukkit.minigames.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.dreamexposure.novalib.api.bukkit.compatibility.NMaterial;

@SuppressWarnings("unused")
public enum Team {
    RED(1, ChatColor.RED, DyeColor.RED, NMaterial.RED_WOOL.getItemStack()),
    BLUE(2, ChatColor.BLUE, DyeColor.LIGHT_BLUE, NMaterial.LIGHT_BLUE_WOOL.getItemStack()),
    GREEN(3, ChatColor.GREEN, DyeColor.LIME, NMaterial.LIME_WOOL.getItemStack()),
    PURPLE(4, ChatColor.DARK_PURPLE, DyeColor.PURPLE, NMaterial.PURPLE_WOOL.getItemStack()),
    AQUA(5, ChatColor.AQUA, DyeColor.CYAN, NMaterial.CYAN_WOOL.getItemStack()),
    WHITE(6, ChatColor.WHITE, DyeColor.WHITE, NMaterial.WHITE_WOOL.getItemStack()),
    GRAY(7, ChatColor.GRAY, DyeColor.GRAY, NMaterial.GRAY_WOOL.getItemStack()),
    YELLOW(8, ChatColor.YELLOW, DyeColor.YELLOW, NMaterial.YELLOW_WOOL.getItemStack());

    private int numValue;
    private ChatColor color;
    private DyeColor woolColor;
    private ItemStack woolBlock;

    Team(int _numValue, ChatColor _color, DyeColor _woolColor, ItemStack _woolBlock) {
        numValue = _numValue;
        color = _color;
        woolColor = _woolColor;
        woolBlock = _woolBlock;
    }

    public int getValue() {
        return numValue;
    }

    public ChatColor getColor() {
        return color;
    }

    public DyeColor getWoolColor() {
        return woolColor;
    }

    public ItemStack getWoolBlock() {
        return woolBlock;
    }

    public static Boolean exists(String teamName) {
        String teamNameUse = teamName.toUpperCase();
        switch (teamNameUse) {
            case "RED":
            case "WHITE":
            case "GREEN":
            case "BLUE":
            case "PURPLE":
            case "AQUA":
            case "GRAY":
            case "YELLOW":
                return true;
        }
        return false;
    }

    public static Team valueOf(int value) {
        switch (value) {
            case 2:
                return BLUE;
            case 3:
                return GREEN;
            case 4:
                return PURPLE;
            case 5:
                return AQUA;
            case 6:
                return WHITE;
            case 7:
                return GRAY;
            case 8:
                return YELLOW;
            default:
                return RED;
        }
    }
}