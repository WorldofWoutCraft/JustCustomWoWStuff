package com.woutwoot.jcwows.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by Wout on 30/11/2014.
 */
public class WoW_head extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
            ItemStack skull = new ItemStack(397, 1, (short) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            if (args[0] != null) {
                meta.setOwner(args[0]);
            } else {
                meta.setOwner(sender.getName());
            }
            skull.setItemMeta(meta);
            p.getInventory().addItem(skull);
        } else {
            sender.sendMessage("You have to be a player!");
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow head (playername)");
    }
}
