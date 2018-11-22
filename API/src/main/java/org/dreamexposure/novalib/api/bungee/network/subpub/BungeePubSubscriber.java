package org.dreamexposure.novalib.api.bungee.network.subpub;

import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import net.md_5.bungee.api.ProxyServer;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bungee.events.network.pubsub.PubSubReceiveEvent;
import org.dreamexposure.novalib.api.database.RedisInfo;
import org.dreamexposure.novalib.api.network.pubsub.ISubscriber;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author NovaFox161
 * Date Created: 11/21/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class BungeePubSubscriber implements ISubscriber {
    private final RedisInfo info;
    private final String pluginName;
    private final String channelName;
    private final UUID uuid;
    
    private RedisPubSubAdapter<String, String> listener;
    private StatefulRedisPubSubConnection<String, String> connection;
    
    public BungeePubSubscriber(RedisInfo _info, String _pluginName, String _channelName) {
        info = _info;
        pluginName = _pluginName;
        channelName = _channelName;
        uuid = UUID.randomUUID();
    }
    
    
    @Override
    public void subscribe() {
        connection = info.getClient().connectPubSub();
        
        listener = new RedisPubSubAdapter<String, String>() {
            @Override
            public void message(String channel, String message) {
                if (NovaLibAPI.getApi().debug())
                    System.out.println(String.format("Channel: %s, Message: %s", channel, message));
                
                try {
                    JSONObject data = new JSONObject(message);
                    
                    PubSubReceiveEvent event = new PubSubReceiveEvent(data, channel, pluginName);
                    
                    ProxyServer.getInstance().getPluginManager().callEvent(event);
                    
                } catch (JSONException e) {
                    if (NovaLibAPI.getApi().debug())
                        NovaLibAPI.getApi().getBungeePlugin().getLogger().warning("PubSub Message received is not JSON!");
                }
            }
        };
        
        connection.addListener(listener);
        connection.sync().subscribe(channelName);
    }
    
    @Override
    public void unsubscribe() {
        connection.removeListener(listener);
        connection.sync().unsubscribe(channelName);
        
        connection.close();
    }
    
    @Override
    public RedisInfo getInfo() {
        return info;
    }
    
    @Override
    public String getPluginName() {
        return pluginName;
    }
    
    @Override
    public String getChannelName() {
        return channelName;
    }
    
    @Override
    public UUID getUniqueId() {
        return uuid;
    }
}
