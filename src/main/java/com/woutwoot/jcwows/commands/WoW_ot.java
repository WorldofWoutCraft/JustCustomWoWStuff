package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.onlinetime.SendListTask;
import com.woutwoot.jcwows.tools.WootConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wout on 8/12/2014 - 11:19.
 */
public class WoW_ot extends WoW_Command {

    @Override
    public String getPermission() {
        return "onlinetime.use";
    }

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args.length == 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new SendListTask(sender));
        } else {
            WootConfig config = new WootConfig(new File(Main.getInstance().getDataFolder() + File.separator + "data.times"));
            try {
                config.loadFile();
            } catch (IOException e) {
            }
            List<String> messages = config.getConfigSeparatedByEquals();

            for (String s : messages) {
                String uuidAndName = s.split("=")[0];

                int minutes = Integer.parseInt(s.split("=")[1]);
                int hours = minutes / 60;
                minutes -= hours * 60;

                String name = "unknown";
                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(uuidAndName);
                while (m.find()) {
                    name = m.group(1);
                }

                if (name.equalsIgnoreCase(args[0])) {
                    if (hours > 0) {
                        sender.sendMessage(name + " - " + hours + " hours, " + minutes + " minutes.");
                    } else {
                        sender.sendMessage(name + " - " + minutes + " minutes.");
                    }
                    return;
                }
            }
        }

    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

}
