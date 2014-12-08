package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.onlinetime.SendListTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 8/12/2014 - 11:19.
 */
public class WoW_ot extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (sender.hasPermission("onlinetime.use")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new SendListTask(sender));
        } else {
            sender.sendMessage("No permissions. You need onlinetime.use");
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

}
