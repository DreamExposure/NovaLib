package com.novamaday.novalib.api.database;

public class DatabaseSettings {
    private final String hostname;
    private final String port;
    private final String database;
    private final String user;
    private final String password;
    private final String prefix;

    public DatabaseSettings(String _hostname, String _port, String _database, String _user, String _password, String _prefix) {
        hostname = _hostname;
        port = _port;
        database = _database;
        user = _user;
        password = _password;
        prefix = _prefix;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPrefix() {
        return prefix;
    }
}