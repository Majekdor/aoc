package dev.majek.aoc.DayThree;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3B {

    /**
     * Code for day three programming challenge B
     * Answer for my values: 3093068400
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day3inputs.txt")));
        String[] inputs = data.split("\n");
        System.out.println("Answer is: " + ((long) 268 * run(1, 1, inputs) * run(5, 1, inputs)
                * run(7, 1, inputs) * run(1, 2, inputs)));
    }

    public static int run(int moveRight, int moveDown, String[] inputs) {
        int toReturn = 0, toMove = 0;
        for (int i = 0; i < 323; i+=moveDown) {
            if (toMove > inputs[i].length()-1)
                toMove -= inputs[i].length();
            if (inputs[i].charAt(toMove) == '#')
                toReturn++;
            toMove += moveRight;
        }
        return toReturn;
    }
}