package org.dreamexposure.novalib.api.network.pubsub;

import org.dreamexposure.novalib.api.database.RedisInfo;
import org.json.JSONObject;

/**
 * @author NovaFox161
 * Date Created: 11/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class Publisher {
    private final RedisInfo info;
    
    public Publisher(RedisInfo _info) {
        info = _info;
    }
    
    //Getters
    public RedisInfo getInfo() {
        return info;
    }
    
    //Setters
    
    //Functions
    public void publish(String channel, JSONObject data) {
        info.getClient().connect().async().publish(channel, data.toString());
    }
}