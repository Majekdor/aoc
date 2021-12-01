package dev.majek.adventofcode.DayTwelve;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12B {

    private static int northSouth = 0;
    private static int eastWest = 0;
    private static int waypointNorthSouth = 1;
    private static int waypointEastWest = 10;

    /**
     * Code for day twelve programming challenge B
     * Answer for my values: 23960
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
                    waypointNorthSouth += value; break;
                case 'E':
                    waypointEastWest += value; break;
                case 'S':
                    waypointNorthSouth -= value; break;
                case 'W':
                    waypointEastWest -= value; break;
                case 'L':
                    rotateWaypoint(360 - value); break;
                case 'R':
                    rotateWaypoint(value); break;
                case 'F':
                    northSouth += waypointNorthSouth * value;
                    eastWest += waypointEastWest * value;
                    break;
            }
        }
        System.out.println("Answer is: " + (Math.abs(northSouth) + Math.abs(eastWest)));
    }

    public static void rotateWaypoint(int clockwiseRotation) {
        int tempEastWest = waypointEastWest;
        int tempNorthSouth = waypointNorthSouth;
        switch (clockwiseRotation) {
            case 90:
                waypointEastWest = tempNorthSouth;
                waypointNorthSouth = -tempEastWest;
                break;
            case 180:
                waypointEastWest = -tempEastWest;
                waypointNorthSouth = -tempNorthSouth;
                break;
            case 270:
                waypointEastWest = -tempNorthSouth;
                waypointNorthSouth = tempEastWest;
                break;
        }
    }
}