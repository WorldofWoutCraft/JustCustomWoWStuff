package com.woutwoot.jcwows.commands;

import org.bukkit.Effect;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Wout on 30/11/2014.
 */
public class WoW_crash extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0] != null && !args[0].equals("")) {
            Player p = (Player) sender;
            Random r = new Random();
            for (int i = 1; i < 10000; i++)
                p.playEffect(p.getLocation().add(r.nextInt(20) - 10, r.nextInt(10) - 5, r.nextInt(10) - 5), Effect.MOBSPAWNER_FLAMES, 0);
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow crash (playername)");
    }
}
