package org.dreamexposure.novalib.api.database;

import com.zaxxer.hikari.HikariDataSource;

@SuppressWarnings({"WeakerAccess", "unused"})
public class HikariInfo {
    private DatabaseSettings settings;
    private HikariDataSource source;

    public HikariInfo(HikariDataSource _source, DatabaseSettings _settings) {
        source = _source;
        settings = _settings;
    }

    public HikariDataSource getSource() {
        return source;
    }

    public DatabaseSettings getSettings() {
        return settings;
    }
}
