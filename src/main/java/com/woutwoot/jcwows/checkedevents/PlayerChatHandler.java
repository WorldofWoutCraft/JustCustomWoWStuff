package com.woutwoot.jcwows.checkedevents;

import com.woutwoot.jcwows.noobresponse.NoobResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Wout on 7/12/2014 - 10:47.
 */
public class PlayerChatHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        NoobResponse nr = new NoobResponse(message, player);

        event.setCancelled(nr.shouldBeCancelled());
        event.setMessage(nr.getMessage());
        if (nr.getWarningMessage() != null) {
            if (!nr.getWarningMessage().isEmpty()) {
                player.sendMessage(nr.getWarningMessage());
            }
        }

    }

}
