package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author woutwoot
 *         Created by on 5/01/2015 - 15:54.
 */
public class WoW_refer extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        Player p;
        if (sender instanceof Player) {
            p = (Player) sender;
        } else {
            sender.sendMessage("Player only command.");
            return;
        }

        if (args.length == 1) {
            if (args[0] != null && args[0].equalsIgnoreCase("generate")) {
                Main.getReferralSystem().generate(p);
            }
        } else if (args.length == 2) {
            if (args[0] != null && args[0].equalsIgnoreCase("redeem") && args[1] != null) {
                Main.getReferralSystem().redeem(p, args[1]);
            } else if (args[0] != null && args[0].equalsIgnoreCase("claim") && args[1] != null) {
                Main.getReferralSystem().claim(p, args[1]);
            }
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow refer playername");
    }

}
