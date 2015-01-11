package com.woutwoot.jcwows.refer;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author woutwoot
 *         Created by on 5/01/2015 - 15:53.
 */
public class ReferralSystem {

    private Map<UUID, Integer> codes = new HashMap<>();
    private Map<Integer, UUID> redeems = new HashMap<>();
    private Map<Integer, UUID> claims = new HashMap<>();

    public void generate(Player p) {

    }

    public void redeem(Player p, String s) {

    }

    public void claim(Player p, String s) {

    }

}
