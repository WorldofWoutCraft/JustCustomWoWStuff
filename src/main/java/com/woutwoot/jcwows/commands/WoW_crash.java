package com.woutwoot.jcwows.commands;

import com.woutwoot.jcwows.Main;
import com.woutwoot.jcwows.tools.UUIDFetcher;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Wout on 30/11/2014.
 */
public class WoW_crash extends WoW_Command {

    @Override
    public void process(CommandSender sender, String[] args) {
        if (args.length > 1 && args[0] != null && !args[0].equals("")) {
            try {
                UUID uuid = UUIDFetcher.getUUIDOf(args[0]);
                Player player = Main.getInstance().getServer().getPlayer(uuid);
                EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
                nmsPlayer.playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(nmsPlayer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage("/wow crash (playername)");
    }
}
