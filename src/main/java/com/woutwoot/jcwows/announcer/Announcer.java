package com.woutwoot.jcwows.announcer;

import com.woutwoot.jcwows.Main;
import org.bukkit.ChatColor;

/**
 * @author woutwoot
 *         Created by on 28/12/2014 - 23:58.
 */
public class Announcer implements Runnable {

    @Override
    public void run() {
        Main.getInstance().getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[NEWS] " + ChatColor.AQUA + "We now have a newsletter! It will be sent whenever the server is going to have some downtime, or when we have information worth sharing. These emails are not spam, and might sometimes contain a key for a reward in the server ;)   " + ChatColor.AQUA + "To get this newsletter do " + ChatColor.GOLD + "/wow register email");
    }

}
