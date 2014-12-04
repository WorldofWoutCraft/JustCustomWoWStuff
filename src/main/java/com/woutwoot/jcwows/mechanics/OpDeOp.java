package com.woutwoot.jcwows.mechanics;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.UUIDFetcher;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.io.*;
import java.util.*;

/**
 * Created by Wout on 2/12/2014.
 */
public class OpDeOp implements Listener {

    private Map<String, List<String>> ops = new HashMap<>();

    @EventHandler(ignoreCancelled = true)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        String name = event.getPlayer().getName();
        UUID uuid = event.getPlayer().getUniqueId();
        String worldTo = event.getPlayer().getWorld().getName();

        if (ops != null && !ops.isEmpty()) {
            List<String> worldOps = ops.get(worldTo);
            if (worldOps != null) {
                if (worldOps.contains(name + "(" + uuid + ")")) {
                    event.getPlayer().setOp(true);
                } else if (event.getPlayer().isOp()) {
                    event.getPlayer().setOp(false);
                }
            }
        }
    }

    public void loadWorldSettings() {
        File dataFolder = Main.getInstance().getDataFolder();
        dataFolder.mkdir();

        for (World w : Main.getInstance().getServer().getWorlds()) {
            File worldFolder = new File(dataFolder + File.separator + "worlds" + File.separator + w.getName());
            worldFolder.mkdirs();
            try {
                File worldFile = new File(worldFolder + File.separator + "ops.txt");
                worldFile.createNewFile();
                FileInputStream fis = new FileInputStream(worldFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                List<String> opsInWorld = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.equals(""))
                        opsInWorld.add(line);
                }
                br.close();
                this.ops.put(w.getName(), opsInWorld);
            } catch (FileNotFoundException e) {
                Main.log(e.getMessage());
            } catch (IOException e) {
                Main.log(e.getMessage());
            }
        }
    }

    public void saveWorldSettings() {
        File dataFolder = Main.getInstance().getDataFolder();
        dataFolder.mkdir();

        for (World w : Main.getInstance().getServer().getWorlds()) {
            File worldFolder = new File(dataFolder + File.separator + "worlds" + File.separator + w.getName());
            worldFolder.mkdirs();
            try {
                File worldFile = new File(worldFolder + File.separator + "ops.txt");
                worldFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(worldFile);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                for (String op : ops.get(w.getName())) {
                    bw.write(op);
                    bw.newLine();
                }
                bw.close();
            } catch (FileNotFoundException e) {
                Main.log(e.getMessage());
            } catch (IOException e) {
                Main.log(e.getMessage());
            }
        }
    }

    public void addGlobalOp(Player sender) {
        for (World w : Main.getInstance().getServer().getWorlds()) {
            if (ops.get(w.getName()) != null) {
                ops.get(w.getName()).add(sender.getName() + "(" + sender.getUniqueId() + ")");
            } else {
                List<String> o = new ArrayList<>();
                o.add(sender.getName() + "(" + sender.getUniqueId() + ")");
                ops.put(w.getName(), o);
            }
        }
    }

    public void addGlobalOp(String name) throws Exception {
        UUID uuid = UUIDFetcher.getUUIDOf(name);
        for (World w : Main.getInstance().getServer().getWorlds()) {
            if (ops.get(w.getName()) != null) {
                ops.get(w.getName()).add(name + "(" + uuid + ")");
            } else {
                List<String> o = new ArrayList<>();
                o.add(name + "(" + uuid + ")");
                ops.put(w.getName(), o);
            }
        }
    }

    public void removeGlobalOp(String name) throws Exception {
        UUID uuid = UUIDFetcher.getUUIDOf(name);
        for (World w : Main.getInstance().getServer().getWorlds()) {
            if (ops.get(w.getName()) != null) {
                ops.get(w.getName()).remove(name + "(" + uuid + ")");
            }
        }
    }

    public void removeGlobalOp(Player player) {
        UUID uuid = player.getUniqueId();
        for (World w : Main.getInstance().getServer().getWorlds()) {
            if (ops.get(w.getName()) != null) {
                ops.get(w.getName()).remove(player.getName() + "(" + uuid + ")");
            }
        }
    }
}
