package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.Title;
import org.bukkit.Bukkit;

/**
 * Created by Wout on 30/11/2014.
 */
public class RestartAnnouncer implements Runnable {

    private int times;

    @Override
    public void run() {
        times++;
        if (times == 5) {
            Bukkit.getScheduler().cancelTasks(Main.getInstance());
            return;
        }
        Title.showTitleToEveryone("SERVER RESTART", "We will be right back!");
    }
}
