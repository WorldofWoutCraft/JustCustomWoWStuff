package com.woutwoot.jcwows.commands;

import org.bukkit.command.CommandSender;

/**
 * @author woutwoot
 *         Created by on 5/01/2015 - 15:54.
 */
public class WoW_refer extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        //TODO
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow refer playername");
    }

}
