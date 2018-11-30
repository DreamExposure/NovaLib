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
    private boolean requiresResponse;
    private String oneTimeResponseChannel;
    
    public PubSubReceiveEvent(JSONObject _data, String _channelName, String _pluginName, String _serverFrom, boolean _isBukkit) {
        data = _data;
        channelName = _channelName;
        pluginName = _pluginName;
        serverNameFrom = _serverFrom;
        serverIsBukkit = _isBukkit;
        
        requiresResponse = false;
        oneTimeResponseChannel = "N/a";
    }
    
    public PubSubReceiveEvent(JSONObject _data, String _channelName, String _pluginName, String _serverFrom, boolean _isBukkit, String _responseChannel) {
        data = _data;
        channelName = _channelName;
        pluginName = _pluginName;
        serverNameFrom = _serverFrom;
        serverIsBukkit = _isBukkit;
        
        requiresResponse = true;
        oneTimeResponseChannel = _responseChannel;
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
     * Gets the name of the channel the data was received through.
     *
     * @return The name of the channel the data was received through.
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
    
    /**
     * Gets whether or not the server is waiting for a response to this message.
     *
     * @return Whether or not the server is waiting for a response to this message.
     */
    public boolean isRequiresResponse() {
        return requiresResponse;
    }
    
    
    /**
     * Gets the name of the one-time-response channel to send data through in order to respond to the requires.
     *
     * @return The name of the one-time-response channel.
     */
    public String getOneTimeResponseChannel() {
        return oneTimeResponseChannel;
    }
}