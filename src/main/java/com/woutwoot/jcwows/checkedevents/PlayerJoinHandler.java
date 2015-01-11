package com.woutwoot.jcwows.checkedevents;

import com.woutwoot.jcwows.Main;
import org.bukkit.Bukkit;
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
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new HostNameTask(event.getHostname()));
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
