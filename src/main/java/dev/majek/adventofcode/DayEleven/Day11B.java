package dev.majek.adventofcode.DayEleven;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11B {

    /**
     * Code for day eleven programming challenge B
     * Answer for my values:
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day11inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
    }
}
