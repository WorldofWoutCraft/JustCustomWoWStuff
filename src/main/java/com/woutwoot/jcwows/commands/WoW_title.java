package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 29/11/2014.
 */
public class WoW_title extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        for(Player p : Main.getInstance().getServer().getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(args[0])){
                Title.showTitle(p, args[1], args[2]);
            }
        }

    }
}
