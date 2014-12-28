package com.woutwoot.jcwows.commands;

import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 29/11/2014.
 */
public abstract class WoW_Command {

    private final String name = this.getClass().getName().replace("WoW_", "").toLowerCase();
    private final String permission = "wow.command." + this.getClass().getName().replace("com.woutwoot.jcwows.commands.WoW_", "").toLowerCase();


    public String getName(){
        return this.name;
    }

    public String getPermission(){
        return this.permission;
    }

    public abstract void process(CommandSender sender, String[] args);

    public abstract void sendHelp(CommandSender sender);
}
