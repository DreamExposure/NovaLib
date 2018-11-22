package org.dreamexposure.novalib.plugin.bungee.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import org.dreamexposure.novalib.api.bungee.events.network.pubsub.PubSubReceiveEvent;

import java.util.UUID;

/**
 * @author NovaFox161
 * Date Created: 11/21/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public class PubSubListener implements Listener {
    
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPubSubReceive(PubSubReceiveEvent event) {
        if (event.getChannelName().equals("NovaLib.Internal.ToBungee") && event.getData().has("Endpoint")) {
            switch (event.getData().getString("Endpoint").toUpperCase()) {
                case "GENERAL":
                    switch (event.getData().getString("Reason").toUpperCase()) {
                        case "SERVER-TRANSFER":
                            UUID playerId = UUID.fromString(event.getData().getString("Player"));
                            String serverTo = event.getData().getString("Server-To");
                            
                            ServerInfo serverToInfo = ProxyServer.getInstance().getServerInfo(serverTo);
                            
                            ProxyServer.getInstance().getPlayer(playerId).connect(serverToInfo, ServerConnectEvent.Reason.PLUGIN);
                            break;
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
