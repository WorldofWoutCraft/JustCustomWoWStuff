package com.woutwoot.jcwows.flightpenalty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author woutwoot
 *         Created by on 11/01/2015 - 17:51.
 */
public class FlightPenaltyTask implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (FlightPenalty.flying.contains(p.getUniqueId())) {
                if (p.getFoodLevel() > 0) {
                    if (p.isFlying())
                        p.setFoodLevel(p.getFoodLevel() - 1);
                } else {
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    FlightPenalty.flying.remove(p.getUniqueId());
                    p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You need to eat some food to fly again!");
                }
            }
        }
    }

}
