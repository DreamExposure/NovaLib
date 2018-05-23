package com.novamaday.novalib.api.minigames.arena;

import java.util.ArrayList;
import java.util.UUID;

public interface IArena {

    //Getters
    int getId();

    String getGameName();

    ArrayList<UUID> getPlayers();

    ArrayList<UUID> getSpectators();

    int getPlayerCount();

    //TODO: Add arenaStatus, GameStatus, GameGoal enums

    boolean isJoinable();

    int getWaitId();

    int getStartId();

    int getGameId();

    boolean useTeams();

    //TODO: Add Teams

    //TODO: Add scoreboard manager

    //TODO: Add PlayerStats

    //TODO: Add WinType

    ArrayList<UUID> getWInningPlayers();

    //TODO: Add get winning teams

    //Setters
    void setPlayerCount(int _count);

    //TODO: Add ArenaStatus, GameStatus, GameGoal enums

    void setJoinable(boolean _joinable);

    void setWaitId(int _waitId);

    void setStartId(int _startId);

    void setGameId(int _gameId);

    //TODO: Add PlayerStats

    //TODO: Add WinType
}