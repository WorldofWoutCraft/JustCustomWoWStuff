package com.woutwoot.jcwows;

import com.woutwoot.jcwows.announcer.Announcer;
import com.woutwoot.jcwows.checkedevents.CommandPreProcessHandler;
import com.woutwoot.jcwows.checkedevents.PlayerChatHandler;
import com.woutwoot.jcwows.checkedevents.PlayerJoinHandler;
import com.woutwoot.jcwows.commands.WoW_Command;
import com.woutwoot.jcwows.flightpenalty.FlightPenaltyTask;
import com.woutwoot.jcwows.flightpenalty.PlayerToggleFlightListener;
import com.woutwoot.jcwows.onlinetime.UpdateTimesTask;
import com.woutwoot.jcwows.refer.ReferralSystem;
import com.woutwoot.jcwows.tools.Lag;
import org.bukkit.Bukkit;
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
    private static ReferralSystem referralSystem = new ReferralSystem();
    private CommandPreProcessHandler preProcessHandler = new CommandPreProcessHandler();
    private PlayerChatHandler playerChatHandler = new PlayerChatHandler();
    private PlayerJoinHandler playerJoinHandler = new PlayerJoinHandler();
    private PlayerToggleFlightListener playerToggleFlightHandler = new PlayerToggleFlightListener();

    public static Main getInstance() {
        return instance;
    }

    public static void log(String message) {
        instance.getLogger().log(Level.WARNING, message);
    }

    public static ReferralSystem getReferralSystem() {
        return referralSystem;
    }

    @Override
    public void onEnable(){
        instance = this;
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Announcer(), 1200L, 8000L);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new FlightPenaltyTask(), 400L, 100L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new UpdateTimesTask(this), 60L, 2400L);
        registerEvents();
        this.getConfig().addDefault("pushbullet_API_key", "key");
        saveConfig();
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
                    sender.sendMessage("Command list:");
                    sender.sendMessage("/wow ot (playername) - Get online time top 10 or for one player.");
                    sender.sendMessage("/wow announcerestart - Announces server restart.");
                    sender.sendMessage("/wow head (playername) - Same as /skull.");
                    sender.sendMessage("/wow lookup (playername) - Searches for bans for this player.");
                    sender.sendMessage("/wow register email - Registers email for newsletter.");
                    sender.sendMessage("/wow refer generate - Generates unique referral code.");
                    sender.sendMessage("/wow refer redeem (code) - Redeems unique referral code.");
                    sender.sendMessage("/wow refer claim (code) - Claims referral prize.");
                    sender.sendMessage("/wow crash (playername) - Tries to crash client by sending lots of particles.");
                    sender.sendMessage("/wow wakeup (playername) - Plays lots of sounds to un-AFK someone.");
                }
            } else {
                sender.sendMessage("This command needs the right arguments!");
            }
            return true;
        }
        return false;
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(preProcessHandler, this);
        this.getServer().getPluginManager().registerEvents(playerChatHandler, this);
        this.getServer().getPluginManager().registerEvents(playerJoinHandler, this);
        this.getServer().getPluginManager().registerEvents(playerToggleFlightHandler, this);
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
