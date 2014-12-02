package com.woutwoot.jcwows.mechanics;

import com.woutwoot.jcwows.Main;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wout on 2/12/2014.
 */
public class OpDeOp implements Listener {

    private Map<String, List<String>> ops = new HashMap<>();

    @EventHandler(ignoreCancelled = true)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        String name = event.getPlayer().getName();
        String uuid = event.getPlayer().getUniqueId().toString();
        String worldTo = event.getPlayer().getWorld().getName();

        if (ops.get(worldTo).contains(name + "(" + uuid + ")")) {
            event.getPlayer().setOp(true);
        } else if (event.getPlayer().isOp()) {
            event.getPlayer().setOp(false);
        }
    }

    public void loadWorldSettings() {
        File dataFolder = Main.getInstance().getDataFolder();
        dataFolder.mkdir();

        for (World w : Main.getInstance().getServer().getWorlds()) {
            File worldFolder = new File(dataFolder + File.separator + "worlds" + File.separator + w.getName());
            worldFolder.mkdirs();
            File worldFile = new File(worldFolder + File.separator + "ops.txt");
            try {
                FileInputStream fis = new FileInputStream(worldFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                List<String> opsInWorld = new ArrayList<>();
                String line = null;
                while ((line = br.readLine()) != null) {
                    opsInWorld.add(line);
                }
                br.close();
                this.ops.put(w.getName(), opsInWorld);
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
    }

    public void saveWorldSettings() {
        File dataFolder = Main.getInstance().getDataFolder();
        dataFolder.mkdir();

        for (World w : Main.getInstance().getServer().getWorlds()) {
            File worldFolder = new File(dataFolder + File.separator + "worlds" + File.separator + w.getName());
            worldFolder.mkdirs();
            File worldFile = new File(worldFolder + File.separator + "ops.txt");
            try {
                FileOutputStream fos = new FileOutputStream(worldFile);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                for (String op : ops.get(w.getName())) {
                    bw.write(op);
                    bw.newLine();
                }
                bw.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
    }

}
