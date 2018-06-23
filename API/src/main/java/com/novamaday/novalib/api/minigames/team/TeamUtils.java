package com.novamaday.novalib.api.minigames.team;

public class TeamUtils {
    public static int determineTeamAmount(int minTeams, int maxTeams, int playerCount) {
        if (minTeams < 2)
            minTeams = 2;

        if (maxTeams > 8)
            maxTeams = 8;

        if (playerCount == minTeams)
            return minTeams;
        else
            return maxTeams / playerCount;
    }
}
