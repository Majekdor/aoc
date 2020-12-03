package dev.majek.adventofcode.DayThree;

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
        int moveRight = 0;
        int a = 0, b = 0, c = 0, d = 0;
        for (String input : inputs) {
            input = input.repeat(1000);
            if (input.charAt(moveRight) == '#') {
                a++;
            }
            moveRight += 1;
        }
        moveRight = 0;
        for (String input : inputs) {
            input = input.repeat(1000);
            if (input.charAt(moveRight) == '#') {
                b++;
            }
            moveRight += 5;
        }
        moveRight = 0;
        for (String input : inputs) {
            input = input.repeat(1000);
            if (input.charAt(moveRight) == '#') {
                c++; // haha
            }
            moveRight += 7;
        }
        moveRight = 0;
        for (int i = 0; i <= 323; i+=2) {
            inputs[i] = inputs[i].repeat(1000);
            if (inputs[i].charAt(moveRight) == '#') {
                d++;
            }
            moveRight += 1;
        }
        System.out.println("Answer is: " + ((long) a * 268 * b * c * d));
    }
}