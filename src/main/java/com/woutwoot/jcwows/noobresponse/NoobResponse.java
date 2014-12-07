package com.woutwoot.jcwows.noobresponse;

import com.woutwoot.jcwows.tools.Lag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 7/12/2014 - 10:52.
 */
public class NoobResponse {

    private String message;
    private Player player;
    private String warning;
    private boolean cancel;

    public NoobResponse(String message, Player player) {
        this.message = message;
        this.player = player;
        parse();
    }

    public boolean shouldBeCancelled() {
        return cancel;
    }

    public String getWarningMessage() {
        return warning;
    }

    public String getMessage() {
        return message;
    }

    public Player getPlayer() {
        return player;
    }

    private void parse() {
        if (message.toLowerCase().startsWith("lag")) {
            cancel = true;
            warning = ChatColor.DARK_RED + "Did you know? Saying lag does not fix it?";
            if (Lag.getTPS() >= 19) {
                warning += ChatColor.RED + " The server is running perfectly fine. It is your client.";
            } else {
                if (Lag.getTPS() < 10) {
                    warning += ChatColor.RED + " The server is lagging a lot. Please notify an admin. (In case the admin is the cause, just wait)";
                } else {
                    warning += ChatColor.RED + " The server is currently lagging a little. It might be our fault";
                }
            }
        }
    }

}
