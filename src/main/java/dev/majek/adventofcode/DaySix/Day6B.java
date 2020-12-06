package dev.majek.adventofcode.DaySix;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day6B {

    /**
     * Code for day six programming challenge A
     * Answer for my values: 3143
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day6inputs.txt")));
        String[] inputs = data.split("\n\n");
        int runningTotal = 0;
        for (String groups : inputs) {
            String[] people = groups.split("\n");
            String builder = people[0];
            for (int i = 1; i <= people.length; i++) {
                for (char c : inputs[i].toCharArray()) {
                    if (builder.indexOf(c) == -1)
                        builder = builder.replaceAll(String.valueOf(c), "");
                    for (char d : builder.toCharArray())
                        if (people[i - 1].indexOf(d) == -1)
                            builder = builder.replaceAll(String.valueOf(d), "");
                }
            }
            runningTotal += builder.length();
        }
        System.out.println("Answer is: " + runningTotal);
    }
}