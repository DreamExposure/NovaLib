package org.dreamexposure.novalib.api.bukkit.network.subpub;

import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.bukkit.Bukkit;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.events.network.pubsub.PubSubReceiveEvent;
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
public class BukkitPubSubscriber implements ISubscriber {
    private final RedisInfo info;
    private final String pluginName;
    private final String channelName;
    private final UUID uuid;
    
    private RedisPubSubAdapter<String, String> listener;
    private StatefulRedisPubSubConnection<String, String> connection;
    
    public BukkitPubSubscriber(RedisInfo _info, String _pluginName, String _channelName) {
        info = _info;
        pluginName = _pluginName;
        channelName = _channelName;
        uuid = UUID.randomUUID();
    }
    
    
    @Override
    public void subscribe() {
        connection = info.getClient().connectPubSub();
        
        listener = new RedisPubSubAdapter<String, String>() {
            @SuppressWarnings("Duplicates")
            @Override
            public void message(String channel, String message) {
                if (NovaLibAPI.getApi().debug())
                    System.out.println(String.format("Channel: %s, Message: %s", channel, message));
        
                try {
                    JSONObject rawData = new JSONObject(message);
                    String serverFrom = rawData.getString("server-from");
                    boolean fromBukkit = rawData.getBoolean("is-bukkit");
                    JSONObject data = rawData.getJSONObject("data");
            
                    PubSubReceiveEvent event = new PubSubReceiveEvent(data, channel, pluginName, serverFrom, fromBukkit);
            
                    Bukkit.getPluginManager().callEvent(event);
            
                } catch (JSONException e) {
                    if (NovaLibAPI.getApi().debug())
                        NovaLibAPI.getApi().getBukkitPlugin().getLogger().warning("PubSub Message received is not JSON!");
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
