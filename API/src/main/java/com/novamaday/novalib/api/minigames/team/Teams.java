package com.novamaday.novalib.api.minigames.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class Teams {
    private final int id;

    private final HashMap<Team, List<UUID>> playersOnTeam = new HashMap<>();

    private final HashMap<Team, Scoreboard> teamScoreboards = new HashMap<>();

    private ArrayList<Team> teamsInGame = new ArrayList<>();

    private int teamCount;


    public Teams(int _id) {
        id = _id;
        teamCount = 0;
        teamsInGame.clear();
    }

    //Booleans/Checkers
    public boolean isOnATeam(UUID uuid) {
        for (Team team: teamsInGame) {
            if (playersOnTeam.get(team).contains(uuid))
                return true;
        }
        return false;
    }

    public boolean isOnTeam(UUID uuid, Team team) {
        return playersOnTeam.containsKey(team) && playersOnTeam.get(team).contains(uuid);
    }

    //Functional
    public void setUpTeams(int minTeamCount, int maxTeamCount, int playerCount) {
        teamsInGame.clear();
        int teamAmount = TeamUtils.determineTeamAmount(minTeamCount, maxTeamCount, playerCount);
        int index = 1;
        for (Team team: Team.values()) {
            if (index < teamAmount + 1) {
                registerTeam(team);
                index = index + 1;
            } else {
                break;
            }
        }
    }

    public Team getTeamWithFewestPlayers() {
        if (teamsInGame.size() > 0) {
            Team teamWithFewestPlayers = teamsInGame.get(0);
            for (Team team: teamsInGame) {
                if (getPlayerCountOnTeam(team) < getPlayerCountOnTeam(teamWithFewestPlayers))
                    teamWithFewestPlayers = team;
            }
            return teamWithFewestPlayers;
        } else {
            return Team.RED;
        }
    }


    public void assignTeams(List<UUID> players) {
        Collections.shuffle(players);
        for (UUID pId: players) {
            if (!isOnATeam(pId))
                addPlayerToTeam(pId, getTeamWithFewestPlayers());
        }
    }

    private void registerTeam(Team team) {
        playersOnTeam.put(team, new ArrayList<>());
        teamsInGame.add(team);
        teamCount += 1;
    }

    //Getters
    public int getId() {
        return id;
    }

    public List<UUID> getPlayersOnTeam(Team team) {
        return playersOnTeam.get(team);
    }

    public List<Team> getTeamsInGame() {
        return teamsInGame;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public Integer getPlayerCountOnTeam(Team team) {
        return playersOnTeam.get(team).size();
    }


    public Team getTeam(UUID uuid) {
        for (Team team: teamsInGame) {
            if (playersOnTeam.containsKey(team)) {
                if (playersOnTeam.get(team).contains(uuid))
                    return team;
            }
        }
        return null;
    }

    public Team getTeam(Player player) {
        return getTeam(player.getUniqueId());
    }

    public Scoreboard getTeamScoreboard(Team team) {
        if (teamScoreboards.containsKey(team))
            return teamScoreboards.get(team);
        else
            return Bukkit.getScoreboardManager().getNewScoreboard();
    }

    public Scoreboard getTeamScoreboard(UUID uuid) {
        return getTeamScoreboard(getTeam(uuid));
    }

    //Setters
    public void setTeamScoreboard(Team team, Scoreboard board) {
        teamScoreboards.remove(team);
        teamScoreboards.put(team, board);
    }

    //Adders
    public void addPlayerToTeam(UUID uuid, Team team) {
        if (teamsInGame.contains(team)) {
            playersOnTeam.get(team).add(uuid);
        } else {
            registerTeam(team);
            addPlayerToTeam(uuid, team);
        }
    }

    //Removers
    public void removePlayerFromTeam(UUID uuid) {
        Team team = getTeam(uuid);
        playersOnTeam.get(team).remove(uuid);
    }

    public void removePlayerFromTeam(Player player) {
        removePlayerFromTeam(player.getUniqueId());
    }
}