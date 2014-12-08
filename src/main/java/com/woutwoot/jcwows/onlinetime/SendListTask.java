package com.woutwoot.jcwows.onlinetime;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.WootConfig;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendListTask implements Runnable {

    private CommandSender sender;

    public SendListTask(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        WootConfig config = new WootConfig(new File(Main.getInstance().getDataFolder() + File.separator + "data.times"));
        try {
            config.loadFile();
        } catch (IOException e) {
            sender.sendMessage("Failed to load data file");
            return;
        }
        sender.sendMessage("User times:");
        List<String> messages = config.getConfigSeparatedByVerticalLine();
        Collections.sort(messages, new ComparatorJWZ());

        for (String s : messages) {
            String uuidAndName = s.split("|")[0];
            int minutes = Integer.parseInt(s.split("|")[1]);
            int hours = minutes / 60;
            minutes -= hours * 60;

            String name = "unknown";
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(uuidAndName);
            while (m.find()) {
                name = m.group(1);
            }

            if (hours > 0) {
                sender.sendMessage(name + " - " + hours + " hours, " + minutes + " minutes.");
            } else {
                sender.sendMessage(name + " - " + minutes + " minutes.");
            }
        }

    }
}
