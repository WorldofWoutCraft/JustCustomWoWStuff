package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Created by Wout on 30/11/2014.
 */
public class RestartAnnouncer implements Runnable {

    private int times = 0;

    @Override
    public void run() {
        times++;
        switch (times) {
            case 1:
                show(ChatColor.DARK_RED, ChatColor.GOLD);
                return;
            case 2:
                show(ChatColor.AQUA, ChatColor.YELLOW);
                return;
            case 3:
                show(ChatColor.LIGHT_PURPLE, ChatColor.GREEN);
                return;
            case 4:
                show(ChatColor.GRAY, ChatColor.DARK_BLUE);
                return;
            case 5:
                show(ChatColor.DARK_PURPLE, ChatColor.BLUE);
                return;
            default:
                Bukkit.getScheduler().cancelTasks(Main.getInstance());
                return;
        }
    }

    private void show(ChatColor c1, ChatColor c2) {
        Title.showTitleToEveryone(c1 + "SERVER RESTART", c2 + "We will be right back!");
    }
}
