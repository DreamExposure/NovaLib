package com.novamaday.novalib.api.database;

import com.novamaday.novalib.api.NovaLibAPI;

import java.sql.Connection;
import java.sql.SQLException;

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

            NovaLibAPI.getApi().plugin.getLogger().info("Database connection successful!");

            return new DatabaseInfo(mySQL, mySQLConnection, settings);
        } catch (Exception e) {
            NovaLibAPI.getApi().plugin.getLogger().severe("Failed to connect to database! Are the settings provided correct?");
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
            NovaLibAPI.getApi().plugin.getLogger().info("Successfully disconnected from MySQL Database!");
            return true;
        } catch (SQLException e) {
            NovaLibAPI.getApi().plugin.getLogger().warning("MySQL Connection may not have been closed properly! Data may be invalidated!");
        }
        return false;
    }
}