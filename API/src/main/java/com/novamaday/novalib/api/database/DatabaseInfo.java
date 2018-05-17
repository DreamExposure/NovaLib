package com.novamaday.novalib.api.database;

import java.sql.Connection;

@SuppressWarnings("unused")
public class DatabaseInfo {
    private MySQL mySQL;
    private Connection con;
    private DatabaseSettings settings;

    public DatabaseInfo(MySQL _mySQL, Connection _con, DatabaseSettings _settings) {
        mySQL = _mySQL;
        con = _con;
        settings = _settings;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public Connection getConnection() {
        return con;
    }

    public DatabaseSettings getSettings() {
        return settings;
    }
}