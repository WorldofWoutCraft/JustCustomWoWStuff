package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 29/11/2014.
 */
public class WoW_announcerestart extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        for(Player p : Main.getInstance().getServer().getOnlinePlayers()){
            Title.showTitle(p, "SERVER RESTART", "We will be right back!");
        }

    }

    @Override
    public void sendHelp(CommandSender sender) {

    }
}
