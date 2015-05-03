package com.woutwoot.jcwows.checkedevents;

import com.woutwoot.jcwows.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.*;

/**
 * Created by Wout on 11/12/2014 - 13:36.
 */
public class PlayerJoinHandler implements Listener {

    int old = 0;

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        int players = Main.getInstance().getServer().getOnlinePlayers().size();
        if (players - old > 2) {
            old = players;
            return;
        }
        if (players >= 10) {
            //Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new PushBulletTask(players)); //TODO: Fix spam
        }
        if (event.getPlayer().getName().equals("woutwoot") || event.getPlayer().getName().equals("MisterChippy")) {
            CommandPreProcessHandler.log.add(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new HostNameTask(event.getHostname()));
        /*if (!event.getHostname().equalsIgnoreCase("mc.woutwoot.com:25565")) {
            String msg = ChatColor.DARK_RED + "" + ChatColor.BOLD;
            msg += "!WARNING! We are changing our IP address. Please reconnect using the new IP: " + ChatColor.UNDERLINE + "mc.woutwoot.com";
            msg += ChatColor.RESET + "" + ChatColor.DARK_RED + "" + ChatColor.BOLD;
            msg += " If you've already done that, ignore this message. Otherwise, change it! The old IP's will stop working.";
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new DelayedMessageTask(msg, event.getPlayer()));
        }*/
    }

    private class DelayedMessageTask implements Runnable {

        private String msg;
        private Player player;

        public DelayedMessageTask(String msg, Player p) {
            this.msg = msg;
            this.player = p;
        }

        @Override
        public void run() {
            if (player != null && player.isOnline()) {
                player.sendMessage(msg);
            }
        }

    }


    private class HostNameTask implements Runnable {
        String hostname;

        public HostNameTask(String hostname) {
            this.hostname = hostname;
        }

        private void addHostName(String hostname) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Main.getInstance().getDataFolder() + File.separator + "hostnames.txt", true)))) {
                out.println(hostname);
            } catch (Exception e) {
            }
        }

        private boolean hostnameExists(String hostname) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(Main.getInstance().getDataFolder() + File.separator + "hostnames.txt"));
            } catch (FileNotFoundException e) {
                return false;
            }
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase(hostname)) {
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

        @Override
        public void run() {
            if (!hostnameExists(hostname)) {
                addHostName(hostname);
            }
        }
    }

}
