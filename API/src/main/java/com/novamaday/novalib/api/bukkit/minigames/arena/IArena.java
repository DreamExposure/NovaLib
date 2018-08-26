package com.novamaday.novalib.api.bukkit.minigames.arena;

import com.novamaday.novalib.api.bukkit.minigames.enums.*;
import com.novamaday.novalib.api.bukkit.minigames.team.Team;
import com.novamaday.novalib.api.bukkit.minigames.team.Teams;

import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings("unused")
public interface IArena {

    //Getters

    /**
     * Gets the ID of the arena.
     *
     * @return The ID of the arena.
     */
    int getId();

    /**
     * The name of the game for the arena.
     * @return The name of the game for the arena.
     */
    String getGameName();

    /**
     * Gets a list of players in the arena.
     * @return A list of players in the arena.
     */
    ArrayList<UUID> getPlayers();

    /**
     * Gets a list of players spectating the arena.
     * @return A list of players spectating the arena.
     */
    ArrayList<UUID> getSpectators();

    /**
     * Gets the current player count.
     * @return THe current player count.
     */
    int getPlayerCount();

    /**
     * Gets the current status of the arena.
     *
     * @return The current status of the arena.
     */
    ArenaStatus getArenaStatus();

    /**
     * Gets the current state of the game.
     *
     * @return The current state of the game.
     */
    GameState getGameState();

    /**
     * Gets the goal of the game.
     *
     * @return The goal of the game.
     */
    GameGoal getGameGoal();

    /**
     * Gets the type of game being played.
     *
     * @return The type of gaming being played.
     */
    GameType getGameType();

    /**
     * Gets whether or not players can join the game.
     * @return Whether or not players can join the game.
     */
    boolean isJoinable();

    /**
     * Gets the ID used by the TimeManager to monitor if the game is in waiting status.
     * @return The ID used by the TimeManager to monitor waiting status.
     */
    int getWaitId();

    /**
     * Gets the ID used by the TimeManager to monitor if the game is in starting status.
     * @return The ID used by the TimeManager to monitor starting status.
     */
    int getStartId();

    /**
     * Gets the ID used by the TimeManager to monitor if the game is in in game status.
     * @return The ID used by the TimeManager to monitor game status.
     */
    int getGameId();

    /**
     * Gets whether or not teams are enabled for the arena.
     * @return Whether or not teams are enabled for the arena.
     */
    boolean useTeams();

    /**
     * Gets the Teams object containing all team related data for the arena.
     *
     * @return The Teams object containing all team related data for the arena.
     */
    Teams getTeams();

    //TODO: Add scoreboard manager

    //TODO: Add PlayerStats

    /**
     * Get the WinType for the arena to determine handling of rewards and stats.
     *
     * @return The WinType for the arena at the end of the game.
     */
    WinType getWinType();

    /**
     * Gets a list of player(s) that have won the game.
     * @return A list of player(s) that have won the game.
     */
    ArrayList<UUID> getWInningPlayers();

    /**
     * Gets a list of Team(s) that have won the game.
     *
     * @return A list of Team(s) that have won the game.
     */
    ArrayList<Team> getWinningTeams();
    //Setters

    /**
     * Sets the current player count
     * @param _count the current player count.
     */
    void setPlayerCount(int _count);

    /**
     * Sets the status of the arena.
     *
     * @param _status The status of the arena.
     */
    void setArenaStatus(ArenaStatus _status);

    /**
     * Sets the state of the game.
     *
     * @param _state The state of the game.
     */
    void setGameState(GameState _state);

    /**
     * Sets the goal of the game.
     *
     * @param _goal The goal of the game.
     */
    void setGameGoal(GameGoal _goal);

    /**
     * Sets the type of game
     *
     * @param _type The type of game
     */
    void setGameType(GameType _type);

    /**
     * Sets whether or not players can join the game.
     * @param _joinable Whether or not the players can join the game.
     */
    void setJoinable(boolean _joinable);

    /**
     * Sets the ID the TimeManager uses to monitor waiting status.
     * @param _waitId The ID the TimeManager uses to monitor waiting status.
     */
    void setWaitId(int _waitId);

    /**
     * Sets the ID the TimeManager uses to monitor waiting status.
     * @param _startId The ID the TimeManager uses to monitor waiting status.
     */
    void setStartId(int _startId);

    /**
     * Sets the ID the TimeManager uses to monitor game status.
     * @param _gameId The ID of the TimeManager uses to monitor game status.
     */
    void setGameId(int _gameId);

    //TODO: Add PlayerStats

    /**
     * Sets the Win type of the game for determining rewards.
     * @param _type the Win type of the game.
     */
    void setWinType(WinType _type);
}