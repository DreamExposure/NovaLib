package com.novamaday.novalib.api.bukkit.events.network.crosstalk;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class CrossTalkReceiveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final JSONObject data;
    private final String clientIp;
    private final String clientPlugin;

    public CrossTalkReceiveEvent(JSONObject _data, String _clientIp, String _clientPlugin) {
        data = _data;
        clientIp = _clientIp;
        clientPlugin = _clientPlugin;
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
    public String getClientIp() {
        return clientIp;
    }

    /**
     * Gets the name of the plugin that sent the data.
     *
     * @return The name of the plugin that sent the data.
     */
    public String getClientPlugin() {
        return clientPlugin;
    }
}