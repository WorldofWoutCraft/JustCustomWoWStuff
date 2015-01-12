package com.woutwoot.jcwows.wakeup;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author woutwoot
 *         Created by on 9/01/2015 - 9:05.
 */
public class WakeUpTask implements Runnable {

    private Player player;
    private Sound sound;

    public WakeUpTask(Player player, Sound sound) {
        this.player = player;
        this.sound = sound;
    }

    @Override
    public void run() {
        player.playSound(player.getLocation(), sound, 2F, 0F);
        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "!!!!! WAKE UP !!!!!");
    }
}
