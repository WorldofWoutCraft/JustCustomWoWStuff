package com.woutwoot.jcwows.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 30/11/2014.
 */
public class WoW_wakeup extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0] != null && !args[0].equals("")) {
            Player p = (Player) sender;
            //TODO: Play a lot of random sounds to player.
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow wakeup (playername)");
    }

}
