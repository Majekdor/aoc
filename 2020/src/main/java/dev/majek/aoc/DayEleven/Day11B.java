package dev.majek.aoc.DayEleven;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11B {

    private static char[][] seatChart;
    private static final char OCCUPIED = '#';
    private static final char EMPTY = 'L';
    private static final char FLOOR = '.';

    /**
     * Code for day eleven programming challenge B
     * Answer for my values: 2013
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day11inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        int height = inputs.size();
        int width = inputs.get(0).length();
        seatChart = new char[height][width];
        for (int i = 0; i < inputs.size(); i++)
            seatChart[i] = inputs.get(i).toCharArray();

        boolean stabilized = updateSeatingArea();
        while(!stabilized)
            stabilized = updateSeatingArea();

        int occupiedSeats = 0;
        for (char[] seat : seatChart)
            for (char c : seat)
                if (c == OCCUPIED)
                    occupiedSeats++;
        System.out.println("Answer is: " + occupiedSeats);
    }

    public static boolean updateSeatingArea() {
        char[][] newSeatOccupancy = new char[seatChart.length][seatChart[0].length];
        for (int y = 0; y < seatChart.length; y++) {
            for (int x = 0; x < seatChart[y].length; x++) {
                if (seatChart[y][x] == FLOOR)
                    newSeatOccupancy[y][x] = FLOOR;
                else {
                    int occupiedSurroundingSeats = getOccupiedSurroundingSeats(x, y);
                    if (occupiedSurroundingSeats == 0)
                        newSeatOccupancy[y][x] = OCCUPIED;
                    else if (occupiedSurroundingSeats >= 5)
                        newSeatOccupancy[y][x] = EMPTY;
                    else
                        newSeatOccupancy[y][x] = seatChart[y][x];
                }
            }
        }
        boolean same = Arrays.deepEquals(seatChart, newSeatOccupancy);
        seatChart = newSeatOccupancy;
        return same;
    }

    // I don't like this at all but it is what it is for now
    public static int getOccupiedSurroundingSeats(int x, int y) {
        int occupied = 0;
        if(isNextVisibleSeatOccupied(x + 1, y, 1, 0))
            occupied++;
        if(isNextVisibleSeatOccupied(x - 1, y, -1, 0))
            occupied++;
        if(isNextVisibleSeatOccupied(x, y + 1, 0, 1))
            occupied++;
        if(isNextVisibleSeatOccupied(x , y - 1, 0, -1))
            occupied++;
        if(isNextVisibleSeatOccupied(x + 1, y + 1, 1, 1))
            occupied++;
        if(isNextVisibleSeatOccupied(x + 1, y - 1, 1, -1))
            occupied++;
        if(isNextVisibleSeatOccupied(x - 1, y - 1, -1, -1))
            occupied++;
        if(isNextVisibleSeatOccupied(x - 1, y + 1, -1, 1))
            occupied++;
        return occupied;
    }

    public static boolean isNextVisibleSeatOccupied(int x, int y, int xMovement, int yMovement) {
        if (y >= 0 && y < seatChart.length && x >= 0 && x < seatChart[y].length) {
            if (seatChart[y][x] == OCCUPIED)
                return true;
            else if(seatChart[y][x] == FLOOR)
                return isNextVisibleSeatOccupied(x + xMovement, y + yMovement, xMovement, yMovement);
        }
        return false;
    }
}