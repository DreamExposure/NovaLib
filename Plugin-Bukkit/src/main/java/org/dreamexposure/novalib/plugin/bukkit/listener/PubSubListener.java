package org.dreamexposure.novalib.plugin.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.dreamexposure.novalib.api.bukkit.events.network.pubsub.PubSubReceiveEvent;

/**
 * @author NovaFox161
 * Date Created: 11/22/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class PubSubListener implements Listener {
    
    
    //TODO: handle any internal stuffs that would need to be handled.
    
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPubSubReceive(PubSubReceiveEvent event) {
        if (event.getChannelName().equals("NovaLib.Internal.ToBukkit") && event.getData().has("Endpoint")) {
            switch (event.getData().getString("Endpoint").toUpperCase()) {
                case "MINIGAMES":
                    switch (event.getData().getString("Reason").toUpperCase()) {
                        default:
                            //Unsupported reason
                            break;
                    }
                    break;
                default:
                    //Unsupported endpoint
            }
        }
    }
}