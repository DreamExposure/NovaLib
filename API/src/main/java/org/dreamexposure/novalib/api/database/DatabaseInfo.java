package org.dreamexposure.novalib.api.database;

import java.sql.Connection;

@SuppressWarnings({"unused", "WeakerAccess"})
public class DatabaseInfo {
    private MySQL mySQL;
    private Connection con;
    private DatabaseSettings settings;

    /**
     * Creates a new instance of the DatabaseInfo with the  needed data.
     *
     * @param _mySQL    The MySQL database the connection was made to.
     * @param _con      The connection made to the MySQL database.
     * @param _settings The DatabaseSettings containing all other needed data.
     */
    public DatabaseInfo(MySQL _mySQL, Connection _con, DatabaseSettings _settings) {
        mySQL = _mySQL;
        con = _con;
        settings = _settings;
    }

    /**
     * Gets the MySQL database the connection was made to.
     * @return The MySQL database the connection was made to.
     */
    public MySQL getMySQL() {
        return mySQL;
    }

    /**
     * Gets the connection made to the MySQL database.
     * @return The connection made to the MySQL database.
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Gets the DatabaseSettings object containing all other needed data.
     * @return The DatabaseSettings object containing all other needed data.
     */
    public DatabaseSettings getSettings() {
        return settings;
    }
}