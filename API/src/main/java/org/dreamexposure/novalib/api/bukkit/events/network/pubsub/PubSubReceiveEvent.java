package org.dreamexposure.novalib.api.bukkit.events.network.pubsub;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.JSONObject;

/**
 * @author NovaFox161
 * Date Created: 11/21/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings("unused")
public class PubSubReceiveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    
    private final JSONObject data;
    private final String channelName;
    private final String pluginName;
    private final String serverNameFrom;
    private final boolean serverIsBukkit;
    
    public PubSubReceiveEvent(JSONObject _data, String _channelName, String _pluginName, String _serverFrom, boolean _isBukkit) {
        data = _data;
        channelName = _channelName;
        pluginName = _pluginName;
        serverNameFrom = _serverFrom;
        serverIsBukkit = _isBukkit;
    }
    
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    /**
     * Gets the original data the client sent.
     *
     * @return The original data the client sent.
     */
    public JSONObject getData() {
        return data;
    }
    
    /**
     * Gets the IP of the client server the data was sent from.
     *
     * @return The IP of the client server the data was sent from.
     */
    public String getChannelName() {
        return channelName;
    }
    
    /**
     * Gets the name of the plugin that sent the data.
     *
     * @return The name of the plugin that sent the data.
     */
    public String getPluginName() {
        return pluginName;
    }
    
    /**
     * Gets the name of the server that sent the data
     *
     * @return The name of the server that sent the data.
     */
    public String getServerNameFrom() {
        return serverNameFrom;
    }
    
    /**
     * Gets whether or not the server that sent the message was a bukkit or bungee server
     *
     * @return Whether or not the server that sent the message was a bukkit or bungee server.
     */
    public boolean isBukkit() {
        return serverIsBukkit;
    }
}