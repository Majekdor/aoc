package dev.majek.aoc.DayFour;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day4A {

    /**
     * Code for day four programming challenge A
     * Answer for my values: 264
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day4inputs.txt")));
        String[] inputs = data.split("\n\n");
        int counter = 0;
        for (String input : inputs)
            if (input.contains("ecl") && input.contains("pid") && input.contains("eyr") && input.contains("hcl") &&
                    input.contains("byr") && input.contains("iyr") && input.contains("hgt"))
                counter++;
        System.out.println("Answer is: " + counter);
    }
}
