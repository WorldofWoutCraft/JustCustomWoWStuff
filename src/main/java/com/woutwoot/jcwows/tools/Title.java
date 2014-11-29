package com.woutwoot.jcwows.tools;

import com.woutwoot.jcwows.Main;
import org.bukkit.Server;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 30/11/2014.
 */
public class Title {

    public static void showTitle(Player p, String title, String subtitle){
        Server server = Main.getInstance().getServer();
        server.dispatchCommand(server.getConsoleSender(), "title " + p.getName() + " title {text:\"" + title +  "\", color:\"red\"}");
        server.dispatchCommand(server.getConsoleSender(), "title " + p.getName() + " subtitle {text:\"" + title +  "\", color:\"yellow\"}");
    }

}
