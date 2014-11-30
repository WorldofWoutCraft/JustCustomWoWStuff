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
        if (args.length != 3 || args[0] == null || args[1] == null || args[2] == null) {
            sendHelp(sender);
            return;
        }
        for(Player p : Main.getInstance().getServer().getOnlinePlayers()){
            if (args[0].equalsIgnoreCase("all")) {
                Title.showTitle(p, args[1].replace("_", " "), args[2].replace("_", " "));
            } else {
                if (p.getName().equalsIgnoreCase(args[0])) {
                    Title.showTitle(p, args[1].replace("_", " "), args[2].replace("_", " "));
                }
            }
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow title [playername|all] [title_with_words] [subtitle_with_words]");
    }
}
