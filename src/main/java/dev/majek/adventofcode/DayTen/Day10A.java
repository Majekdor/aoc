package dev.majek.adventofcode.DayTen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day10A {

    /**
     * Code for day ten programming challenge A
     * Answer for my values: 1876
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day10inputs.txt")));
        int[] inputs = Arrays.stream(data.split("\n")).mapToInt(Integer::parseInt).sorted().toArray();
        int ones = 1; int threes = 1;
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i]-inputs[i-1] == 3)
                threes++;
            if (inputs[i]-inputs[i-1] == 1)
                ones++;
        }
        System.out.println("Answer is: " + ones * threes);
    }
}
