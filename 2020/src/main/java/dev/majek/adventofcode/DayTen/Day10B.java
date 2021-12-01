package dev.majek.adventofcode.DayTen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day10B {

    /**
     * Code for day ten programming challenge B
     * Answer for my values: 14173478093824
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day10inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());

        long[] joltages = new long[inputs.size() + 2];
        for (int i = 0; i < inputs.size(); i++)
            joltages[i] = Long.parseLong(inputs.get(i));

        joltages[inputs.size()] = 0;
        joltages[inputs.size() + 1] = Arrays.stream(joltages).max().getAsLong() + 3;
        Arrays.sort(joltages);

        System.out.println("Answer is: " + getCount(joltages));
    }

    private static long getCount(long[] joltages) {
        long[] count = new long[joltages.length];
        count[0] = 1;
        for (int i = 1; i < joltages.length; i++)
            for (int j = i - 3; j < i; j++)
                if (j >= 0 && joltages[i] - joltages[j] <= 3)
                    count[i] += count[j];
        return count[count.length - 1];
    }
}
