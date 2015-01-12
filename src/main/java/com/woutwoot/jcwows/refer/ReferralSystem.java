package com.woutwoot.jcwows.refer;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author woutwoot
 *         Created by on 5/01/2015 - 15:53.
 */
public class ReferralSystem {

    private Map<UUID, Integer> codes = new HashMap<>();
    private Map<Integer, UUID> redeems = new HashMap<>();
    private Map<Integer, UUID> claims = new HashMap<>();

    public int generate(Player p) {
        int code = new Random().nextInt(Integer.MAX_VALUE);
        codes.put(p.getUniqueId(), code);
        return code;
    }

    public void redeem(Player p, String s) {

    }

    public void claim(Player p, String s) {

    }

}
