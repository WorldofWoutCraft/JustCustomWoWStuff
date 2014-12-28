package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.*;

/**
 * @author woutwoot
 *         Created by on 28/12/2014 - 22:25.
 */
public class WoW_register extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args.length == 1) {
            String email = args[0];
            if (email.contains("@") && email.contains(".")) {
                if (!emailExists(sender.getName(), email)) {
                    addEmail("[" + sender.getName() + "] " + email);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Thank you for registering for our newsletter! We don't send a lot of them, so you should be fine ;)");
                } else {
                    sender.sendMessage("We already have your email!");
                }
            } else {
                sender.sendMessage("That email is invalid!");
            }
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {

    }

    private void addEmail(String email) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Main.getInstance().getDataFolder() + File.separator + "emails.txt", true)))) {
            out.println(email);
        } catch (Exception e) {
        }
    }

    private boolean emailExists(String name, String email) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Main.getInstance().getDataFolder() + File.separator + "emails.txt"));
        } catch (FileNotFoundException e) {
            return false;
        }
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("[" + name + "] " + email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

}
