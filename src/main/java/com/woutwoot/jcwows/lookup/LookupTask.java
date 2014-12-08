package com.woutwoot.jcwows.lookup;

import com.woutwoot.jcwows.tools.fishbans.BanService;
import com.woutwoot.jcwows.tools.fishbans.FishBans;
import mkremins.fanciful.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 8/12/2014 - 19:32.
 */
public class LookupTask implements Runnable {

    private String name;
    private CommandSender sender;

    public LookupTask(String name, CommandSender sender) {
        this.name = name;
        this.sender = sender;
    }

    @Override
    public void run() {
        int numbans = 0;
        BanService mcbans = FishBans.getAllBans(name).getService(FishBans.MCBANS);
        if (mcbans != null) {
            sender.sendMessage(ChatColor.AQUA + "----- MCBans bans -----");
            for (String ban : mcbans.getCombinedList()) {
                sender.sendMessage(ChatColor.DARK_RED + ban);
                numbans++;
            }
        }

        BanService glizer = FishBans.getAllBans(name).getService(FishBans.GLIZER);
        if (glizer != null) {
            sender.sendMessage(ChatColor.AQUA + "----- Glizer bans -----");
            for (String ban : glizer.getCombinedList()) {
                sender.sendMessage(ChatColor.DARK_RED + ban);
                numbans++;
            }
        }

        BanService mcbouncer = FishBans.getAllBans(name).getService(FishBans.MCBOUNCER);
        if (mcbouncer != null) {
            sender.sendMessage(ChatColor.AQUA + "--- MCbouncer bans ----");
            for (String ban : mcbouncer.getCombinedList()) {
                sender.sendMessage(ChatColor.DARK_RED + ban);
                numbans++;
            }
        }

        BanService mcblockkit = FishBans.getAllBans(name).getService(FishBans.MCBLOCKIT);
        if (mcblockkit != null) {
            sender.sendMessage(ChatColor.AQUA + "--- MCBlockit bans ----");
            for (String ban : mcblockkit.getCombinedList()) {
                sender.sendMessage(ChatColor.DARK_RED + ban);
                numbans++;
            }
        }

        if (numbans == 0) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "This player has no bans! :)");
        }

        sender.sendMessage(ChatColor.AQUA + "-------- Google -------");
        Player p = (Player) sender;
        if (p != null)
            p.sendRawMessage(new FancyMessage().text(name).color(ChatColor.BLUE).style(ChatColor.UNDERLINE).link("https://www.google.be/search?&q=" + name).toJSONString());
    }

}
