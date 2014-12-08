package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.lookup.LookupTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 8/12/2014 - 19:29.
 */
public class WoW_lookup extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args[0] != null) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new LookupTask(args[0], sender));
        } else {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new LookupTask(sender.getName(), sender));
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow lookup name");
    }
}
