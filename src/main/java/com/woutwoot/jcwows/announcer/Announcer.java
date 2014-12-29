package com.woutwoot.jcwows.announcer;

import com.woutwoot.jcwows.Main;
import org.bukkit.ChatColor;

/**
 * @author woutwoot
 * Created by woutwoot on 28/12/2014 - 23:58.
 */
public class Announcer implements Runnable {

    int curr = 0;
    String tag = ChatColor.LIGHT_PURPLE + "[NEWS] " + ChatColor.AQUA;

    @Override
    public void run() {
        if (curr == 0) {
            Main.getInstance().getServer().broadcastMessage(tag + "We now have a newsletter! It will be sent whenever the server is going to have some downtime, or when we have information worth sharing. These emails are not spam, and might sometimes contain a key for a reward in the server ;)   " + ChatColor.AQUA + "To get this newsletter do " + ChatColor.GOLD + "/wow register email");
            curr = 1;
        } else {
            Main.getInstance().getServer().broadcastMessage(tag + "Check out our website! " + ChatColor.GOLD + ChatColor.UNDERLINE + "http://woutwoot.com/");
            curr = 0;
        }
    }

}
