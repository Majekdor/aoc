package dev.majek.adventofcode.DayTwelve;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12A {

    private static int northSouth = 0;
    private static int eastWest = 0;
    private static char facing = 'E';

    /**
     * Code for day twelve programming challenge A
     * Answer for my values: 1589
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day12inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        for (String input : inputs) {
            char function = input.charAt(0);
            int value = Integer.parseInt(input.substring(1));
            switch (function) {
                case 'N':
                    northSouth += value; break;
                case 'E':
                    eastWest += value; break;
                case 'S':
                    northSouth -= value; break;
                case 'W':
                    eastWest -= value; break;
                case 'L':
                    changeDirection(360 - value); break;
                case 'R':
                    changeDirection(value); break;
                case 'F':
                    switch (facing) {
                        case 'N':
                            northSouth += value; break;
                        case 'E':
                            eastWest += value; break;
                        case 'S':
                            northSouth -= value; break;
                        case 'W':
                            eastWest -= value; break;
                    }
            }
        }
        System.out.println("Answer is: " + (Math.abs(northSouth) + Math.abs(eastWest)));
    }

    public static void changeDirection(int clockwiseRotation) {
        switch (facing) {
            case 'N':
                if (clockwiseRotation == 90)
                    facing = 'E';
                else if (clockwiseRotation == 180)
                    facing = 'S';
                else if (clockwiseRotation == 270)
                    facing = 'W';
                break;
            case 'E':
                if (clockwiseRotation == 90)
                    facing = 'S';
                else if (clockwiseRotation == 180)
                    facing = 'W';
                else if (clockwiseRotation == 270)
                    facing = 'N';
                break;
            case 'S':
                if (clockwiseRotation == 90)
                    facing = 'W';
                else if (clockwiseRotation == 180)
                    facing = 'N';
                else if (clockwiseRotation == 270)
                    facing = 'E';
                break;
            case 'W':
                if (clockwiseRotation == 90)
                    facing = 'N';
                else if (clockwiseRotation == 180)
                    facing = 'E';
                else if (clockwiseRotation == 270)
                    facing = 'S';
                break;
        }
    }
}