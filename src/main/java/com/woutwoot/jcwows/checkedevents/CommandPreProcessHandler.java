package com.woutwoot.jcwows.checkedevents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by woutwoot on 3/12/14.
 */
public class CommandPreProcessHandler implements Listener {

    public static List<UUID> log = new ArrayList<UUID>();

    @EventHandler(ignoreCancelled = true)
    public void onCommandPreProccess(PlayerCommandPreprocessEvent event) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOp() && log.contains(p.getUniqueId()) && !event.getPlayer().getUniqueId().equals(p.getUniqueId())) {
                p.sendMessage(ChatColor.GREEN + "[WL] " + ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.WHITE + " used: " + ChatColor.YELLOW + event.getMessage());
            }
        }

        if (event.getMessage().equalsIgnoreCase("/ptool disable JustCustomWowStuff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Nope.");
        }
        if (event.getMessage().equalsIgnoreCase("/plugintools disable JustCustomWowStuff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Nope.");
        }
        if (event.getMessage().equalsIgnoreCase("/plugintools disable JustCustomWowStuff")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Nope.");
        }
        if (event.getMessage().equalsIgnoreCase("/deop woutwoot")) {
            if (!event.getPlayer().getName().equals("woutwoot")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You shall not deop the owner!");
            }
        }
        if (event.getMessage().startsWith("/nick woutw")) {
            if (!event.getPlayer().getName().equals("woutwoot")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You shall not nickname the mighty owner!");
            }
        }
    }

}
