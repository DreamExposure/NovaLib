package org.dreamexposure.novalib.api.database;

import io.lettuce.core.RedisClient;
import org.dreamexposure.novalib.api.NovaLibAPI;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class DatabaseManager {
    /**
     * Connects to the MySQL server with the provided details in the settings.
     *
     * @param settings The DatabaseSettings with the needed info the connect to the database.
     * @return The DatabaseInfo with all related data for future use.
     */
    public static DatabaseInfo connectToMySQL(DatabaseSettings settings) {
        try {
            MySQL mySQL = new MySQL(settings.getHostname(), settings.getPort(), settings.getDatabase(), settings.getUser(), settings.getPassword());

            Connection mySQLConnection = mySQL.getConnection();

            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().info("Database connection successful!");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().info("Database connection successful!");

            return new DatabaseInfo(mySQL, mySQLConnection, settings);
        } catch (Exception e) {
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().severe("Failed to connect to database! Are the settings provided correct?");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().severe("Failed to connect to database! Are the settings provided correct?");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Disconnects from the MySQL server in the DatabaseInfo.
     * @param info The DatabaseInfo with all the required data.
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    public static boolean disconnectFromMySQL(DatabaseInfo info) {
        try {
            info.getMySQL().closeConnection();
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().info("Successfully disconnected from MySQL Database!");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().info("Successfully disconnected from MySQL Database!");
            return true;
        } catch (SQLException e) {
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().warning("MySQL Connection may not have been closed properly! Data may be invalidated!");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().warning("MySQL Connection may not have been closed properly! Data may be invalidated!");
        }
        return false;
    }
    
    public static RedisInfo connectToRedis(DatabaseSettings settings) {
        try {
            RedisClient client = RedisClient.create("redis://" + settings.getPassword() + "@" + settings.getHostname() + ":" + settings.getPort() + "/0");
            
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().info("Database connection successful!");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().info("Database connection successful!");
            
            return new RedisInfo(client, settings);
        } catch (Exception e) {
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().severe("Failed to connect to Redis! Are the settings provided correct?");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().severe("Failed to connect to Redis! Are the settings provided correct?");
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean disconnectFromRedis(RedisInfo info) {
        try {
            info.getClient().shutdown();
            
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().info("Successfully disconnected from Redis!");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().info("Successfully disconnected from Redis!");
            return true;
        } catch (Exception e) {
            if (NovaLibAPI.getApi().isBukkit())
                NovaLibAPI.getApi().getBukkitPlugin().getLogger().warning("Redis Connection may not have been closed properly! Data may be invalidated!");
            else
                NovaLibAPI.getApi().getBungeePlugin().getLogger().warning("Redis Connection may not have been closed properly! Data may be invalidated!");
        }
        return false;
    }
}