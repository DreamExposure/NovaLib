package org.dreamexposure.novalib.api.network.pubsub;

import org.dreamexposure.novalib.api.database.RedisInfo;

import java.util.UUID;

/**
 * @author NovaFox161
 * Date Created: 11/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public interface ISubscriber {
    
    void subscribe();
    
    void unsubscribe();
    
    //Getters
    RedisInfo getInfo();
    
    String getPluginName();
    
    String getChannelName();
    
    UUID getUniqueId();
    
    //Setters
}