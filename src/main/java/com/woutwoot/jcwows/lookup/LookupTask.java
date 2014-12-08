package com.woutwoot.jcwows.lookup;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.fishbans.BanService;
import com.woutwoot.jcwows.tools.fishbans.Bans;
import com.woutwoot.jcwows.tools.fishbans.FishBans;
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
        Bans bans = FishBans.getAllBans(name);
        if (bans != null) {
            BanService mcbans = bans.getService(FishBans.MCBANS);
            if (mcbans != null) {
                sender.sendMessage(ChatColor.AQUA + "----- MCBans bans -----");
                for (String ban : mcbans.getCombinedList()) {
                    sender.sendMessage(ChatColor.DARK_RED + ban);
                    numbans++;
                }
            }

            BanService glizer = bans.getService(FishBans.GLIZER);
            if (glizer != null) {
                sender.sendMessage(ChatColor.AQUA + "----- Glizer bans -----");
                for (String ban : glizer.getCombinedList()) {
                    sender.sendMessage(ChatColor.DARK_RED + ban);
                    numbans++;
                }
            }

            BanService mcbouncer = bans.getService(FishBans.MCBOUNCER);
            if (mcbouncer != null) {
                sender.sendMessage(ChatColor.AQUA + "--- MCbouncer bans ----");
                for (String ban : mcbouncer.getCombinedList()) {
                    sender.sendMessage(ChatColor.DARK_RED + ban);
                    numbans++;
                }
            }

            BanService mcblockkit = bans.getService(FishBans.MCBLOCKIT);
            if (mcblockkit != null) {
                sender.sendMessage(ChatColor.AQUA + "--- MCBlockit bans ----");
                for (String ban : mcblockkit.getCombinedList()) {
                    sender.sendMessage(ChatColor.DARK_RED + ban);
                    numbans++;
                }
            }
        }

        if (numbans == 0) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "This player has no bans! :)");
        }

        sender.sendMessage(ChatColor.AQUA + "-------- Google -------");
        Player p = (Player) sender;
        if (p != null)
            Main.getInstance().getServer().dispatchCommand(Main.getInstance().getServer().getConsoleSender(), "tellraw " + sender.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Google Lookup - [NAME]\",\"color\":\"gold\",\"underlined\":\"true\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.google.be/search?&q=[NAME]\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"https://www.google.be/search?&q=[NAME]\",\"color\":\"aqua\"}]}}}]}".replace("[NAME]", name));
    }

}
