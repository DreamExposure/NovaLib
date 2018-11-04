package org.dreamexposure.novalib.api.bukkit.minigames.arena;

import org.dreamexposure.novalib.api.bukkit.minigames.enums.*;
import org.dreamexposure.novalib.api.bukkit.minigames.team.Team;
import org.dreamexposure.novalib.api.bukkit.minigames.team.Teams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author NovaFox161
 * Date Created: 11/4/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings("unused")
public class BaseArena implements IArena {
    private final int id;
    private final String gameName;
    
    private List<UUID> players = new ArrayList<>();
    private List<UUID> spectators = new ArrayList<>();
    
    private ArenaStatus status;
    private GameState state;
    private GameGoal goal;
    private GameType type;
    
    private boolean joinable;
    
    private int waitId;
    private int startId;
    private int gameId;
    
    private boolean useTeams;
    private Teams teams;
    
    private WinType winType;
    private List<UUID> winningPlayers = new ArrayList<>();
    private List<Team> winningTeams = new ArrayList<>();
    
    
    public BaseArena(int _id, String _gameName) {
        id = _id;
        gameName = _gameName;
        
        status = ArenaStatus.EMPTY;
        state = GameState.NOT_STARTED;
        goal = GameGoal.LAST_MAN_STANDING;
        type = GameType.FREE_FOR_ALL;
        
        teams = new Teams(id);
        
        winType = WinType.NONE;
    }
    
    //Getters
    
    /**
     * Gets the ID of the arena.
     *
     * @return The ID of the arena.
     */
    @Override
    public int getId() {
        return id;
    }
    
    /**
     * The name of the game for the arena.
     *
     * @return The name of the game for the arena.
     */
    @Override
    public String getGameName() {
        return gameName;
    }
    
    /**
     * Gets a list of players in the arena.
     *
     * @return A list of players in the arena.
     */
    @Override
    public List<UUID> getPlayers() {
        return players;
    }
    
    /**
     * Gets a list of players spectating the arena.
     *
     * @return A list of players spectating the arena.
     */
    @Override
    public List<UUID> getSpectators() {
        return spectators;
    }
    
    /**
     * Gets the current status of the arena.
     *
     * @return The current status of the arena.
     */
    @Override
    public ArenaStatus getArenaStatus() {
        return status;
    }
    
    /**
     * Gets the current state of the game.
     *
     * @return The current state of the game.
     */
    @Override
    public GameState getGameState() {
        return state;
    }
    
    /**
     * Gets the goal of the game.
     *
     * @return The goal of the game.
     */
    @Override
    public GameGoal getGameGoal() {
        return goal;
    }
    
    /**
     * Gets the type of game being played.
     *
     * @return The type of gaming being played.
     */
    @Override
    public GameType getGameType() {
        return type;
    }
    
    /**
     * Gets whether or not players can join the game.
     *
     * @return Whether or not players can join the game.
     */
    @Override
    public boolean isJoinable() {
        return joinable;
    }
    
    /**
     * Gets the ID used by the TimeManager to monitor if the game is in waiting status.
     *
     * @return The ID used by the TimeManager to monitor waiting status.
     */
    @Override
    public int getWaitId() {
        return waitId;
    }
    
    /**
     * Gets the ID used by the TimeManager to monitor if the game is in starting status.
     *
     * @return The ID used by the TimeManager to monitor starting status.
     */
    @Override
    public int getStartId() {
        return startId;
    }
    
    /**
     * Gets the ID used by the TimeManager to monitor if the game is in in game status.
     *
     * @return The ID used by the TimeManager to monitor game status.
     */
    @Override
    public int getGameId() {
        return gameId;
    }
    
    /**
     * Gets whether or not teams are enabled for the arena.
     *
     * @return Whether or not teams are enabled for the arena.
     */
    @Override
    public boolean useTeams() {
        return useTeams;
    }
    
    /**
     * Gets the Teams object containing all team related data for the arena.
     *
     * @return The Teams object containing all team related data for the arena.
     */
    @Override
    public Teams getTeams() {
        return teams;
    }
    
    /**
     * Get the WinType for the arena to determine handling of rewards and stats.
     *
     * @return The WinType for the arena at the end of the game.
     */
    @Override
    public WinType getWinType() {
        return winType;
    }
    
    /**
     * Gets a list of player(s) that have won the game.
     *
     * @return A list of player(s) that have won the game.
     */
    @Override
    public List<UUID> getWinningPlayers() {
        return winningPlayers;
    }
    
    /**
     * Gets a list of Team(s) that have won the game.
     *
     * @return A list of Team(s) that have won the game.
     */
    @Override
    public List<Team> getWinningTeams() {
        return winningTeams;
    }
    
    //Setters
    
    /**
     * Sets the status of the arena.
     *
     * @param _status The status of the arena.
     */
    @Override
    public void setArenaStatus(ArenaStatus _status) {
        status = _status;
    }
    
    /**
     * Sets the state of the game.
     *
     * @param _state The state of the game.
     */
    @Override
    public void setGameState(GameState _state) {
        state = _state;
    }
    
    /**
     * Sets the goal of the game.
     *
     * @param _goal The goal of the game.
     */
    @Override
    public void setGameGoal(GameGoal _goal) {
        goal = _goal;
    }
    
    /**
     * Sets the type of game
     *
     * @param _type The type of game
     */
    @Override
    public void setGameType(GameType _type) {
        type = _type;
    }
    
    /**
     * Sets whether or not players can join the game.
     *
     * @param _joinable Whether or not the players can join the game.
     */
    @Override
    public void setJoinable(boolean _joinable) {
        joinable = _joinable;
    }
    
    /**
     * Sets the ID the TimeManager uses to monitor waiting status.
     *
     * @param _waitId The ID the TimeManager uses to monitor waiting status.
     */
    @Override
    public void setWaitId(int _waitId) {
        waitId = _waitId;
    }
    
    /**
     * Sets the ID the TimeManager uses to monitor waiting status.
     *
     * @param _startId The ID the TimeManager uses to monitor waiting status.
     */
    @Override
    public void setStartId(int _startId) {
        startId = _startId;
    }
    
    /**
     * Sets the ID the TimeManager uses to monitor game status.
     *
     * @param _gameId The ID of the TimeManager uses to monitor game status.
     */
    @Override
    public void setGameId(int _gameId) {
        gameId = _gameId;
    }
    
    /**
     * Sets whether or not to use teams for this arena.
     *
     * @param _useTeams Whether or not to use teams for the arena.
     */
    @Override
    public void setUseTeam(boolean _useTeams) {
        useTeams = _useTeams;
    }
    
    
    /**
     * Sets the Win type of the game for determining rewards.
     *
     * @param _type the Win type of the game.
     */
    @Override
    public void setWinType(WinType _type) {
        winType = _type;
    }
}
