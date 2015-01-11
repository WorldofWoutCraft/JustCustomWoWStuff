package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.wakeup.WakeUpTask;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 30/11/2014.
 */
public class WoW_wakeup extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0] != null && !args[0].equals("")) {
            Player p = (Player) sender;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.ENDERMAN_SCREAM), 20L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.ENDERDRAGON_DEATH), 10L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.DOOR_OPEN), 30L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.DOOR_CLOSE), 40L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.DOOR_OPEN), 50L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.DOOR_CLOSE), 60L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new WakeUpTask(p, Sound.AMBIENCE_THUNDER), 60L);
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow register email");
    }

}
