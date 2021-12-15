package dev.majek.aoc.DaySeven;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day7A {

    /**
     * Code for day seven programming challenge A
     * Answer for my values: 169
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day7inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        List<Bag> bags = inputs.stream().map(Bag::new).collect(Collectors.toList());
        Map<String, Set<String>> bagWithTheirParentBags = findAllBagColors(bags);
        Set<String> colors = findBagsOfColor("shiny gold", new HashSet<>(), bagWithTheirParentBags);
        System.out.println("Answer is: " + colors.size());
    }

    /**
     * Map all bags to a map that specifies the colors of the bags and their contained colors.
     * @param bags list of bags
     * @return Map with colors of bags keyed to all bags that can contain that color.
     */
    private static Map<String, Set<String>> findAllBagColors(List<Bag> bags) {
        Map<String, Set<String>> bagsAndParents = new HashMap<>();
        for (Bag bag : bags)
            for (String color : bag.getInnerBags().keySet())
                if (bagsAndParents.containsKey(color))
                    bagsAndParents.get(color).add(bag.getColor());
                else {
                    Set<String> colors = new HashSet<>();
                    colors.add(bag.getColor());
                    bagsAndParents.put(color, colors);
                }
        return bagsAndParents;
    }

    /**
     * Get all colors of bags that contain a certain color - recursively called.
     * @param color             color that should be contained by the bags
     * @param alreadyChecked    set of colors that were already checked
     * @param mappedColors      set of all bag colors
     * @return Set of bag colors that contain a certain color.
     */
    private static Set<String> findBagsOfColor(String color, Set<String> alreadyChecked, Map<String, Set<String>> mappedColors) {
        Set<String> parents = new HashSet<>();
        if (mappedColors.containsKey(color))
            for (String parent : mappedColors.get(color))
                if (!alreadyChecked.contains(parent)) {
                    alreadyChecked.add(parent); parents.add(parent);
                    parents.addAll(findBagsOfColor(parent, alreadyChecked, mappedColors));
                }
        return parents;
    }
}