package com.woutwoot.jcwows.onlinetime;

import java.util.Comparator;

public class ComparatorJWZ implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1 != null && o2 != null) {
            Integer mins1 = Integer.parseInt(o1.split("=")[1]);
            Integer mins2 = Integer.parseInt(o2.split("=")[1]);
            if (mins1 > mins2) {
                return -1;
            } else if (mins1 < mins2) {
                return 1;
            }
        }
        return 0;
    }

}
