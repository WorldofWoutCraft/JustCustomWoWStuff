package com.woutwoot.jcwows.checkedevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by woutwoot on 3/12/14.
 */
public class CommandPreProcessHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onCommandPreProccess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/ptool disable JustCustomWowStuff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Nope.");
        }
        if (event.getMessage().equalsIgnoreCase("/plugintools disable JustCustomWowStuff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Nope.");
        }
    }

}
