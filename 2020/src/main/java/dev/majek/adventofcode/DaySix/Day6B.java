package dev.majek.adventofcode.DaySix;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6B {

    /**
     * Code for day six programming challenge B
     * Answer for my values: 3143
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day6inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n\n")).collect(Collectors.toList());
        long answer = inputs.stream().map(line -> line.split("\n")).mapToLong(chars -> chars[0].chars()
                .filter(ch  -> Arrays.stream(chars).allMatch(c -> c.indexOf(ch) >= 0)).count()).sum();
        System.out.println("Answer is: " + answer);
    }
}