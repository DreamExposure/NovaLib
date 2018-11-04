package org.dreamexposure.novalib.api.bukkit.minigames.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.file.CustomConfig;
import org.dreamexposure.novalib.api.bukkit.minigames.enums.GameType;
import org.dreamexposure.novalib.api.bukkit.minigames.team.Team;
import org.dreamexposure.novalib.api.bukkit.region.Cuboid;

/**
 * @author NovaFox161
 * Date Created: 11/1/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ArenaConfig {
    private final int id;
    private final String gameName;
    private final CustomConfig config;
    
    public ArenaConfig(int _id, String _gameName) {
        id = _id;
        gameName = _gameName;
        
        config = new CustomConfig(NovaLibAPI.getApi().getBukkitPlugin(), "/minigames/arenas/" + gameName + "/" + id, "config.yml");
    }
    
    public ArenaConfig(int _id, String _gameName, CustomConfig _config) {
        id = _id;
        gameName = _gameName;
        config = _config;
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    public CustomConfig getConfig() {
        return config;
    }
    
    public String getDisplayName() {
        return config.get().getString("DisplayName");
    }
    
    public int getMaxPlayers() {
        return config.get().getInt("Players.Max");
    }
    
    public int getMinPlayers() {
        return config.get().getInt("Players.Min");
    }
    
    public GameType getGameType() {
        return GameType.valueOf(config.get().getString("Game.Type"));
    }
    
    public int getWaitLength() {
        return config.get().getInt("Time.Wait");
    }
    
    public int getGameLength() {
        return config.get().getInt("Time.Game");
    }
    
    public int getTeamMin() {
        return config.get().getInt("Team.Count.Min");
    }
    
    public int getTeamMax() {
        return config.get().getInt("Team.Count.Max");
    }
    
    public int getTeamMinPlayers(Team team) {
        return config.get().getInt("Team." + team.name() + ".Players.Min");
    }
    
    public int getTeamMaxPlayers(Team team) {
        return config.get().getInt("Team." + team.name() + ".Players.Max");
    }
    
    public String getTeamDisplayName(Team team) {
        return config.get().getString("Team." + team.name() + ".DisplayName");
    }
    
    public Location getEndLocation() {
        World w = Bukkit.getWorld(config.get().getString("Locations.End.world"));
        double x = config.get().getDouble("Locations.End.x");
        double y = config.get().getDouble("Locations.End.y");
        double z = config.get().getDouble("Locations.End.z");
        float ya = (float) config.get().getDouble("Locations.End.yaw");
        float pi = (float) config.get().getDouble("Locations.End.pitch");
        
        return new Location(w, x, y, z, ya, pi);
    }
    
    public Location getQuitLocation() {
        World w = Bukkit.getWorld(config.get().getString("Locations.Quit.world"));
        double x = config.get().getDouble("Locations.Quit.x");
        double y = config.get().getDouble("Locations.Quit.y");
        double z = config.get().getDouble("Locations.Quit.z");
        float ya = (float) config.get().getDouble("Locations.Quit.yaw");
        float pi = (float) config.get().getDouble("Locations.Quit.pitch");
        
        return new Location(w, x, y, z, ya, pi);
    }
    
    public Location getLobbyLocation() {
        World w = Bukkit.getWorld(config.get().getString("Locations.Lobby.world"));
        double x = config.get().getDouble("Locations.Lobby.x");
        double y = config.get().getDouble("Locations.Lobby.y");
        double z = config.get().getDouble("Locations.Lobby.z");
        float ya = (float) config.get().getDouble("Locations.Lobby.yaw");
        float pi = (float) config.get().getDouble("Locations.Lobby.pitch");
        
        return new Location(w, x, y, z, ya, pi);
    }
    
    public Location getSpectateLocation() {
        World w = Bukkit.getWorld(config.get().getString("Locations.Spectate.world"));
        double x = config.get().getDouble("Locations.Spectate.x");
        double y = config.get().getDouble("Locations.Spectate.y");
        double z = config.get().getDouble("Locations.Spectate.z");
        float ya = (float) config.get().getDouble("Locations.Spectate.yaw");
        float pi = (float) config.get().getDouble("Locations.Spectate.pitch");
        
        return new Location(w, x, y, z, ya, pi);
    }
    
    public Location getSpawnLocation() {
        return getSpawnLocation(1);
    }
    
    public Location getSpawnLocation(int num) {
        World w = Bukkit.getWorld(config.get().getString("Locations.Spawn." + num + ".world"));
        double x = config.get().getDouble("Locations.Spawn." + num + ".x");
        double y = config.get().getDouble("Locations.Spawn." + num + ".y");
        double z = config.get().getDouble("Locations.Spawn." + num + ".z");
        float ya = (float) config.get().getDouble("Locations.Spawn." + num + ".yaw");
        float pi = (float) config.get().getDouble("Locations.Spawn." + num + ".pitch");
        
        return new Location(w, x, y, z, ya, pi);
    }
    
    public Location getTeamSpawnLocation(Team team) {
        return getTeamSpawnLocation(team, 1);
    }
    
    public Location getTeamSpawnLocation(Team team, int num) {
        World w = Bukkit.getWorld(config.get().getString("Locations.Spawn." + team.name() + "." + num + ".world"));
        double x = config.get().getDouble("Locations.Spawn." + team.name() + "." + num + ".x");
        double y = config.get().getDouble("Locations.Spawn." + team.name() + "." + num + ".y");
        double z = config.get().getDouble("Locations.Spawn." + team.name() + "." + num + ".z");
        float ya = (float) config.get().getDouble("Locations.Spawn." + team.name() + "." + num + ".yaw");
        float pi = (float) config.get().getDouble("Locations.Spawn." + team.name() + "." + num + ".pitch");
        
        return new Location(w, x, y, z, ya, pi);
    }
    
    public Cuboid getArenaRegion() {
        World w1 = Bukkit.getWorld(config.get().getString("Locations.Region.1.world"));
        double x1 = config.get().getDouble("Locations.Region.1.x");
        double y1 = config.get().getDouble("Locations.Region.1.y");
        double z1 = config.get().getDouble("Locations.Region.1.z");
        
        World w2 = Bukkit.getWorld(config.get().getString("Locations.Region.2.world"));
        double x2 = config.get().getDouble("Locations.Region.2.x");
        double y2 = config.get().getDouble("Locations.Region.2.y");
        double z2 = config.get().getDouble("Locations.Region.2.z");
        
        Location loc1 = new Location(w1, x1, y1, z1);
        Location loc2 = new Location(w2, x2, y2, z2);
        
        return new Cuboid(loc1, loc2);
    }
    
    
    //Setters
    public void setDisplayName(String displayName) {
        config.get().set("DisplayName", displayName);
        config.save();
    }
    
    public void setMaxPlayers(int maxPlayers) {
        config.get().set("Players.Max", maxPlayers);
        config.save();
    }
    
    public void setMinPlayers(int minPlayers) {
        config.get().set("Players.Min", minPlayers);
        config.save();
    }
    
    public void setGameType(GameType type) {
        config.get().set("Game.Type", type.name());
        config.save();
    }
    
    public void setWaitLength(int waitLength) {
        config.get().set("Time.Wait", waitLength);
        config.save();
    }
    
    public void setGameLength(int gameLength) {
        config.get().set("Time.Game", gameLength);
        config.save();
    }
    
    public void setTeamMin(int teamMin) {
        config.get().set("Team.Count.Min", teamMin);
        config.save();
    }
    
    public void setTeamMax(int teamMax) {
        config.get().set("Team.Count.Max", teamMax);
        config.save();
    }
    
    public void setTeamMinPlayers(int teamMinPlayers, Team team) {
        config.get().set("Team." + team.name() + ".Players.Min", teamMinPlayers);
        config.save();
    }
    
    public void setTeamMaxPlayers(int teamMaxPlayers, Team team) {
        config.get().set("Team." + team.name() + ".Players.Max", teamMaxPlayers);
        config.save();
    }
    
    public void setTeamDisplayName(String teamDisplayName, Team team) {
        config.get().set("Team." + team.name() + ".DisplayName", teamDisplayName);
        config.save();
    }
    
    public void setEndLocation(Location loc) {
        config.get().set("Locations.End.world", loc.getWorld().getName());
        config.get().set("Locations.End.x", loc.getX());
        config.get().set("Locations.End.y", loc.getY());
        config.get().set("Locations.End.z", loc.getZ());
        config.get().set("Locations.End.yaw", loc.getYaw());
        config.get().set("Locations.End.pitch", loc.getPitch());
        config.save();
    }
    
    public void setQuitLocation(Location loc) {
        config.get().set("Locations.Quit.world", loc.getWorld().getName());
        config.get().set("Locations.Quit.x", loc.getX());
        config.get().set("Locations.Quit.y", loc.getY());
        config.get().set("Locations.Quit.z", loc.getZ());
        config.get().set("Locations.Quit.yaw", loc.getYaw());
        config.get().set("Locations.Quit.pitch", loc.getPitch());
        config.save();
    }
    
    public void setLobbyLocation(Location loc) {
        config.get().set("Locations.Lobby.world", loc.getWorld().getName());
        config.get().set("Locations.Lobby.x", loc.getX());
        config.get().set("Locations.Lobby.y", loc.getY());
        config.get().set("Locations.Lobby.z", loc.getZ());
        config.get().set("Locations.Lobby.yaw", loc.getYaw());
        config.get().set("Locations.Lobby.pitch", loc.getPitch());
        config.save();
    }
    
    public void setSpectateLocation(Location loc) {
        config.get().set("Locations.Spectate.world", loc.getWorld().getName());
        config.get().set("Locations.Spectate.x", loc.getX());
        config.get().set("Locations.Spectate.y", loc.getY());
        config.get().set("Locations.Spectate.z", loc.getZ());
        config.get().set("Locations.Spectate.yaw", loc.getYaw());
        config.get().set("Locations.Spectate.pitch", loc.getPitch());
        config.save();
    }
    
    public void setSpawnLocation(Location loc) {
        setSpawnLocation(loc, 1);
    }
    
    public void setSpawnLocation(Location loc, int num) {
        config.get().set("Locations.Spawn." + num + ".world", loc.getWorld().getName());
        config.get().set("Locations.Spawn." + num + ".x", loc.getX());
        config.get().set("Locations.Spawn." + num + ".y", loc.getY());
        config.get().set("Locations.Spawn." + num + ".z", loc.getZ());
        config.get().set("Locations.Spawn." + num + ".yaw", loc.getYaw());
        config.get().set("Locations.Spawn." + num + ".pitch", loc.getPitch());
        config.save();
    }
    
    public void setTeamSpawnLocation(Team team, Location loc) {
        setTeamSpawnLocation(team, loc, 1);
    }
    
    public void setTeamSpawnLocation(Team team, Location loc, int num) {
        config.get().set("Locations.Spawn." + team.name() + "." + num + ".world", loc.getWorld().getName());
        config.get().set("Locations.Spawn." + team.name() + "." + num + ".x", loc.getX());
        config.get().set("Locations.Spawn." + team.name() + "." + num + ".y", loc.getY());
        config.get().set("Locations.Spawn." + team.name() + "." + num + ".z", loc.getZ());
        config.get().set("Locations.Spawn." + team.name() + "." + num + ".yaw", loc.getYaw());
        config.get().set("Locations.Spawn." + team.name() + "." + num + ".pitch", loc.getPitch());
        config.save();
    }
    
    public void setArenaRegion(Location loc1, Location loc2) {
        config.get().set("Locations.Region.1.world", loc1.getWorld().getName());
        config.get().set("Locations.Region.1.x", loc1.getX());
        config.get().set("Locations.Region.1.y", loc1.getY());
        config.get().set("Locations.Region.1.z", loc1.getZ());
        
        config.get().set("Locations.Region.2.world", loc2.getWorld().getName());
        config.get().set("Locations.Region.2.x", loc2.getX());
        config.get().set("Locations.Region.2.y", loc2.getY());
        config.get().set("Locations.Region.2.z", loc2.getZ());
        
        config.save();
    }
    
    //Functions
}