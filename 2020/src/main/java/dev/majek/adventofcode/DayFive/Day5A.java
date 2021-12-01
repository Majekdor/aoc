package dev.majek.adventofcode.DayFive;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5A {

    /**
     * Code for day five programming challenge A
     * Answer for my values: 998
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day5inputs.txt")));
        List<String> inputs = new ArrayList<>(Arrays.asList(data.split("\n")));
        int[] seatIDs = inputs.stream().mapToInt(Day5A::getSeatID).sorted().toArray();
        System.out.println("Answer is: " + (seatIDs[seatIDs.length-1]));
    }

    public static int getSeatID(String input) {
        return Integer.parseInt(input.replace("F", "0").replace("B", "1")
                .replace("L", "0").replace("R", "1"), 2);
    }
}