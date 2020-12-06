package dev.majek.adventofcode.DayFive;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5B {

    /**
     * Code for day five programming challenge B
     * Answer for my values: 676
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day5inputs.txt")));
        List<String> inputs = new ArrayList<>(Arrays.asList(data.split("\n")));
        int[] seatIDs = inputs.stream().mapToInt(Day5B::getSeatID).sorted().toArray();
        for (int i = 1; i < seatIDs.length; i++)
            if (seatIDs[i] - seatIDs[i-1] != 1)
                System.out.println("Answer is: " + (seatIDs[i]-1));
    }

    public static int getSeatID(String input) {
        return Integer.parseInt(input.replace("F", "0").replace("B", "1")
                .replace("L", "0").replace("R", "1"), 2);
    }
}