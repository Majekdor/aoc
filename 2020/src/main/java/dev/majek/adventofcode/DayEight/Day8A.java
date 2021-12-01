package dev.majek.adventofcode.DayEight;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day8A {

    /**
     * Code for day eight programming challenge A
     * Answer for my values: 1749
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day8inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        List<Integer> tested = new ArrayList<>();
        int accumulator = 0;
        for (int i = 0; i < inputs.size(); i++) {
            String action = inputs.get(i).substring(0, 3);
            int num = Integer.parseInt(inputs.get(i).substring(4));
            if (tested.contains(i))
                break;
            if (action.equalsIgnoreCase("acc")) {
                accumulator += num;
                tested.add( i);
            }
            if (action.equalsIgnoreCase("nop"))
                continue;
            if (action.equalsIgnoreCase("jmp")) {
                i += num-1;
                tested.add(i);
            }
        }
        System.out.println("Answer is: " + accumulator);
    }
}