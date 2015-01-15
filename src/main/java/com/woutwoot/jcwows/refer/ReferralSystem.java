package com.woutwoot.jcwows.refer;

import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author woutwoot
 *         Created by on 5/01/2015 - 15:53.
 */
public class ReferralSystem {

    private Map<UUID, Integer> codes = new HashMap<>();
    private Map<Integer, UUID> redeems = new HashMap<>();
    private Set<UUID> done = new HashSet<>();

    public int generate(Player p) {
        int code = new Random().nextInt(Integer.MAX_VALUE);
        codes.put(p.getUniqueId(), code);
        return code;
    }

    public void redeem(Player p, String s) {
        int code = Integer.parseInt(s);
        redeems.put(code, p.getUniqueId());
    }

    public void claim(Player p, String s) {
        int code = Integer.parseInt(s);
        if (redeems.containsKey(code)) {
            done.add(p.getUniqueId());
            done.add(redeems.get(code));
            redeems.remove(code);
            codes.remove(p.getUniqueId());
        }
    }

}
