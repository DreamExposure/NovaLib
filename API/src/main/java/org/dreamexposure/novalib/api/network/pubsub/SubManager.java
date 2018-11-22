package org.dreamexposure.novalib.api.network.pubsub;

import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.bukkit.network.subpub.BukkitPubSubscriber;
import org.dreamexposure.novalib.api.bungee.network.subpub.BungeePubSubscriber;
import org.dreamexposure.novalib.api.database.DatabaseManager;
import org.dreamexposure.novalib.api.database.DatabaseSettings;
import org.dreamexposure.novalib.api.database.RedisInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author NovaFox161
 * Date Created: 11/21/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class SubManager {
    private static SubManager instance;
    
    private RedisInfo info;
    
    private List<ISubscriber> subscribers = new ArrayList<>();
    
    private SubManager() {
    } //Prevent initialization
    
    public static SubManager get() {
        if (instance == null) instance = new SubManager();
        
        return instance;
    }
    
    //Getters
    public RedisInfo getInfo() {
        return info;
    }
    
    public ISubscriber getSubscriber(UUID id) {
        for (ISubscriber s : subscribers) {
            if (s.getUniqueId().equals(id)) return s;
        }
        return null;
    }
    
    //Setters
    
    //Functions
    public void init() {
        if (NovaLibAPI.getApi().isBukkit()) {
            String hostname = NovaLibAPI.getApi().getBukkitConfig().get().getString("Redis.SubPub.Hostname");
            int port = NovaLibAPI.getApi().getBukkitConfig().get().getInt("Redis.SubPub.Port");
            String user = NovaLibAPI.getApi().getBukkitConfig().get().getString("Redis.SubPub.User");
            String pass = NovaLibAPI.getApi().getBukkitConfig().get().getString("Redis.SubPub.Password");
            
            DatabaseSettings settings = new DatabaseSettings(hostname, port + "", "N/a", user, pass, "N/a");
            
            info = DatabaseManager.connectToRedis(settings);
        } else {
            String hostname = NovaLibAPI.getApi().getBungeeConfig().get().getString("Redis.SubPub.Hostname");
            int port = NovaLibAPI.getApi().getBungeeConfig().get().getInt("Redis.SubPub.Port");
            String user = NovaLibAPI.getApi().getBungeeConfig().get().getString("Redis.SubPub.User");
            String pass = NovaLibAPI.getApi().getBungeeConfig().get().getString("Redis.SubPub.Password");
            
            DatabaseSettings settings = new DatabaseSettings(hostname, port + "", "N/a", user, pass, "N/a");
            
            info = DatabaseManager.connectToRedis(settings);
        }
    }
    
    public boolean isRegistered(UUID id) {
        for (ISubscriber s : subscribers) {
            if (s.getUniqueId().equals(id)) return true;
        }
        return false;
    }
    
    public UUID register(String pluginName, String channel) {
        if (NovaLibAPI.getApi().isBukkit()) {
            BukkitPubSubscriber subscriber = new BukkitPubSubscriber(info, pluginName, channel);
            
            subscribers.add(subscriber);
            
            subscriber.subscribe();
            
            return subscriber.getUniqueId();
        } else {
            BungeePubSubscriber subscriber = new BungeePubSubscriber(info, pluginName, channel);
            
            subscribers.add(subscriber);
            
            subscriber.subscribe();
            
            return subscriber.getUniqueId();
        }
    }
    
    public void unregister(UUID id) {
        if (isRegistered(id)) {
            ISubscriber sub = getSubscriber(id);
            sub.unsubscribe();
            
            subscribers.remove(sub);
        }
    }
}
