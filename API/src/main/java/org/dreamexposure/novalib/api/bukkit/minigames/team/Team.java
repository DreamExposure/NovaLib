package org.dreamexposure.novalib.api.bukkit.minigames.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public enum Team {
    RED(1, ChatColor.RED, DyeColor.RED, new ItemStack(Material.RED_WOOL, 1)),
    BLUE(2, ChatColor.BLUE, DyeColor.LIGHT_BLUE, new ItemStack(Material.LIGHT_BLUE_WOOL, 1)),
    GREEN(3, ChatColor.GREEN, DyeColor.LIME, new ItemStack(Material.LIME_WOOL, 1)),
    PURPLE(4, ChatColor.DARK_PURPLE, DyeColor.PURPLE, new ItemStack(Material.PURPLE_WOOL, 1)),
    AQUA(5, ChatColor.AQUA, DyeColor.CYAN, new ItemStack(Material.CYAN_WOOL, 1)),
    WHITE(6, ChatColor.WHITE, DyeColor.WHITE, new ItemStack(Material.WHITE_WOOL, 1)),
    GRAY(7, ChatColor.GRAY, DyeColor.GRAY, new ItemStack(Material.GRAY_WOOL, 1)),
    YELLOW(8, ChatColor.YELLOW, DyeColor.YELLOW, new ItemStack(Material.YELLOW_WOOL, 1));

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
                return true;
            case "BLUE":
                return true;
            case "GREEN":
                return true;
            case "PURPLE":
                return true;
            case "AQUA":
                return true;
            case "WHITE":
                return true;
            case "GRAY":
                return true;
            case "YELLOW":
                return true;
        }
        return false;
    }

    public static Team valueOf(int value) {
        switch (value) {
            case 1:
                return RED;
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