package dev.majek.adventofcode.DaySeven;

import java.util.HashMap;
import java.util.Map;

public class Bag {

    private final String color;
    private final Map<String, Integer> innerBags = new HashMap<>();

    public Bag(String info) {
        String[] innerAndOuter = info.split("contain");
        this.color = innerAndOuter[0].substring(0, innerAndOuter[0].indexOf("bags") - 1);
        String[] innerBags = innerAndOuter[1].split(",");
        for (String innerBag : innerBags) {
            innerBag = innerBag.trim();
            if (!innerBag.contains("no other bags.")) {
                int number = Integer.parseInt(innerBag.substring(0, 1));
                String color = innerBag.substring(2, innerBag.indexOf("bag") - 1);
                this.innerBags.put(color, number);
            }
        }
    }

    public String getColor() {
        return color;
    }

    public Map<String, Integer> getInnerBags() {
        return innerBags;
    }
}
