package com.woutwoot.jcwows.flightpenalty;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * @author woutwoot
 *         Created by on 11/01/2015 - 17:51.
 */
public class PlayerToggleFlightListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        if (event.isFlying()) {
            FlightPenalty.flying.add(event.getPlayer().getUniqueId());
        } else {
            FlightPenalty.flying.remove(event.getPlayer().getUniqueId());
        }
    }

}
