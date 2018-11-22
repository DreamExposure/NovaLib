package org.dreamexposure.novalib.api.database;

import io.lettuce.core.RedisClient;

/**
 * @author NovaFox161
 * Date Created: 11/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class RedisInfo {
    private RedisClient client;
    private DatabaseSettings settings;
    
    /**
     * Creates a new instance of the DatabaseInfo with the  needed data.
     *
     * @param _client   The RedisClient that is connected.
     * @param _settings The DatabaseSettings containing all other needed data.
     */
    public RedisInfo(RedisClient _client, DatabaseSettings _settings) {
        client = _client;
        settings = _settings;
    }
    
    
    /**
     * Gets the connected Redis Client
     *
     * @return The connected Redis Client
     */
    public RedisClient getClient() {
        return client;
    }
    
    /**
     * Gets the DatabaseSettings object containing all other needed data.
     *
     * @return The DatabaseSettings object containing all other needed data.
     */
    public DatabaseSettings getSettings() {
        return settings;
    }
}