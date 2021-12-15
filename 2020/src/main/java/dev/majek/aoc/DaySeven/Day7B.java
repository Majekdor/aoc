package dev.majek.aoc.DaySeven;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day7B {

    /**
     * Code for day seven programming challenge B
     * Answer for my values: 82372
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day7inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        List<Bag> bags = inputs.stream().map(Bag::new).collect(Collectors.toList());
        Map<String, Bag> colorBagMap = createBagMap(bags);
        System.out.println("Answer is: " + getNeededBags("shiny gold", colorBagMap));
    }

    /**
     * Create a mapping between a color and the bag representing the color.
     * @param bags list of bags
     * @return Map with colors keyed to corresponding bags.
     */
    private static Map<String, Bag> createBagMap(List<Bag> bags) {
        Map<String, Bag> createColorBagMap = new HashMap<>();
        for (Bag bag : bags)
            createColorBagMap.put(bag.getColor(), bag);
        return createColorBagMap;
    }

    /**
     * Get the number of bags needed within a bag of a given color - recursively called.
     * @param color         starting color
     * @param colorBagMap   map of colors and corresponding bags
     * @return Number of bags.
     */
    private static int getNeededBags(String color, Map<String, Bag> colorBagMap) {
        int neededBags = 1;
        Bag bag = colorBagMap.get(color);
        if (color.equals("shiny gold"))
            neededBags = 0;
        for (String neededColor : bag.getInnerBags().keySet())
            neededBags += bag.getInnerBags().get(neededColor) * getNeededBags(neededColor, colorBagMap);
        return neededBags;
    }
}