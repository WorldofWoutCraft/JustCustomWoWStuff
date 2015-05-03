package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.checkedevents.CommandPreProcessHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 8/12/2014 - 11:19.
 */
public class WoW_log extends WoW_Command {

    @Override
    public String getPermission() {
        return "wow.commandlog";
    }

    @Override
    public void process(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (CommandPreProcessHandler.log.contains(p.getUniqueId())) {
            CommandPreProcessHandler.log.remove(p.getUniqueId());
            p.sendMessage("CommandLog disabled!");
        } else {
            CommandPreProcessHandler.log.add(p.getUniqueId());
            p.sendMessage("CommandLog enabled!");
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

}
