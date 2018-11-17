package org.dreamexposure.novalib.api.bukkit.minigames.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;

/**
 * @author NovaFox161
 * Date Created: 11/12/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class GameScoreboard {
    
    private final int id;
    private final String gameName;
    private final boolean useTeams;
    
    private final HashMap<Integer, ScoreboardLine> lines = new HashMap<>();
    private ScoreboardManager sbManager;
    
    private Scoreboard scoreboard;
    
    public GameScoreboard(int _id, String _gameName) {
        id = _id;
        gameName = _gameName;
        useTeams = false;
        
        sbManager = Bukkit.getScoreboardManager();
    }
    
    public GameScoreboard(int _id, String _gameName, boolean _useTeams) {
        id = _id;
        gameName = _gameName;
        useTeams = _useTeams;
        
        sbManager = Bukkit.getScoreboardManager();
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    public boolean usingTeams() {
        return useTeams;
    }
}