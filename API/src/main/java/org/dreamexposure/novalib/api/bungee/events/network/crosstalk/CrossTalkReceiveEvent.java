package org.dreamexposure.novalib.api.bungee.events.network.crosstalk;

import net.md_5.bungee.api.plugin.Event;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class CrossTalkReceiveEvent extends Event {
    private final JSONObject data;
    private final String clientIp;
    private final String clientPlugin;

    public CrossTalkReceiveEvent(JSONObject _data, String _clientIp, String _clientPlugin) {
        data = _data;
        clientIp = _clientIp;
        clientPlugin = _clientPlugin;
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