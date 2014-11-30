package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.tools.Title;
import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 29/11/2014.
 */
public class WoW_announcerestart extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        Title.showTitleToEveryone("SERVER RESTART", "We will be right back!");
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }
}
