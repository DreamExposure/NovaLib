package com.novamaday.novalib.api.minigames.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

@SuppressWarnings("unused")
public enum Team {
    RED(1, ChatColor.RED, DyeColor.RED),
    BLUE(2, ChatColor.BLUE, DyeColor.LIGHT_BLUE),
    GREEN(3, ChatColor.GREEN, DyeColor.LIME),
    PURPLE(4, ChatColor.DARK_PURPLE, DyeColor.PURPLE),
    AQUA(5, ChatColor.AQUA, DyeColor.CYAN),
    WHITE(6, ChatColor.WHITE, DyeColor.WHITE),
    GRAY(7, ChatColor.GRAY, DyeColor.GRAY),
    YELLOW(8, ChatColor.YELLOW, DyeColor.YELLOW);

    private int numValue;
    private ChatColor color;
    private DyeColor woolColor;

    Team(int _numValue, ChatColor _color, DyeColor _woolColor) {
        numValue = _numValue;
        color = _color;
        woolColor = _woolColor;
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