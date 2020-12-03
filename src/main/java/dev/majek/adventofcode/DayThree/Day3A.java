package dev.majek.adventofcode.DayThree;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3A {

    /**
     * Code for day three programming challenge A
     * Answer for my values: 268
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day3inputs.txt")));
        String[] inputs = data.split("\n");
        int moveRight = 0, runningTotal = 0;
        for (String input : inputs) {
            if (moveRight > input.length()-1)
                moveRight -= input.length();
            if (input.charAt(moveRight) == '#')
                runningTotal++;
            moveRight += 3;
        }
        System.out.println("Answer is: " + runningTotal);
    }
}