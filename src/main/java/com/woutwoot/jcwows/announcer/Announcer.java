package com.woutwoot.jcwows.announcer;

import com.woutwoot.jcwows.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * @author woutwoot
 * Created by woutwoot on 28/12/2014 - 23:58.
 */
public class Announcer implements Runnable {

    String tag = ChatColor.LIGHT_PURPLE + "[NEWS] " + ChatColor.AQUA;

    @Override
    public void run() {
        if (!Bukkit.getServer().getOnlinePlayers().isEmpty())
            Main.getInstance().getServer().broadcastMessage(tag + "Want to protect your land? Use a golden shovel!");
    }

}
