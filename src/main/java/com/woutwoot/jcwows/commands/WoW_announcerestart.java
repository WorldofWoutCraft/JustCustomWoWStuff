package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 29/11/2014.
 */
public class WoW_announcerestart extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new RestartAnnouncer(), 0L, 100L);
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }
}
