package dev.majek.adventofcode.DaySix;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6A {

    /**
     * Code for day six programming challenge A
     * Answer for my values: 6351
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day6inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n\n")).map(line -> line
                .replaceAll("\n", "")).collect(Collectors.toList());
        long answer = inputs.stream().mapToLong(line -> line.chars().distinct().count()).sum();
        System.out.println("Answer is: " + answer);
    }
}