package org.dreamexposure.novalib.api.bukkit.network.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.dreamexposure.novalib.api.NovaLibAPI;
import org.dreamexposure.novalib.api.network.pubsub.PubSubManager;
import org.json.JSONObject;

/**
 * @author NovaFox161
 * Date Created: 11/22/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class PlayerTransfer {
    public static void sendToServer(Plugin plugin, String serverName, Player player) {
        JSONObject data = new JSONObject();
        data.put("Endpoint", "General");
        data.put("Reason", "Server-Transfer");
        data.put("Plugin-Name", plugin.getName());
        data.put("Server-To", serverName);
        data.put("Server-From", NovaLibAPI.getApi().getServerName());
        data.put("Player", player.getUniqueId());
        
        PubSubManager.get().publish("NovaLib.Internal.ToBungee", data);
    }
}
