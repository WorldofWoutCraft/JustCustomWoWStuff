package com.woutwoot.jcwows;

import com.woutwoot.jcwows.checkedevents.CommandPreProccessHandler;
import com.woutwoot.jcwows.commands.WoW_Command;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * Created by Wout on 29/11/2014.
 */
public class Main extends JavaPlugin {

    private static Main instance;

    private CommandPreProccessHandler preProccessHandler = new CommandPreProccessHandler();

    public static Main getInstance() {
        return instance;
    }

    public static void log(String message) {
        instance.getLogger().log(Level.WARNING, message);
    }

    @Override
    public void onEnable(){
        instance = this;
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wow")) {
            if (args.length > 0) {
                WoW_Command command = this.findCommand(args[0].toLowerCase());
                if (command != null) {
                    if (sender.hasPermission(command.getPermission())) {
                        command.process(sender, Arrays.copyOfRange(args, 1, args.length));
                    } else {
                        sender.sendMessage("You don't have permission for this command. (" + command.getPermission() + ")");
                    }
                } else {
                    sender.sendMessage("Invalid argument.");
                }
            } else {
                sender.sendMessage("This command needs the right arguments!");
            }
            return true;
        }
        return false;
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(preProccessHandler, this);
    }

    private WoW_Command findCommand(String name){
        try {
            Class<?> act = Class.forName("com.woutwoot.jcwows.commands.WoW_" + name);
            WoW_Command command = (WoW_Command) act.newInstance();
            return command;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}
