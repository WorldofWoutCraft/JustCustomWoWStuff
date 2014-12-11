package com.woutwoot.jcwows.checkedevents;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.pushbullet.PushBulletTask;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Wout on 11/12/2014 - 13:36.
 */
public class PlayerJoinHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        int players = Main.getInstance().getServer().getOnlinePlayers().size();
        if (players >= 10) {
            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new PushBulletTask(players));
        }
    }

}
