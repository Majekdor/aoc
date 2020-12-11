package dev.majek.adventofcode.DayEleven;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11A {

    private static char[][] seatChart;
    private static final char OCCUPIED = '#';
    private static final char EMPTY = 'L';
    private static final char FLOOR = '.';

    /**
     * Code for day eleven programming challenge A
     * Answer for my values: 2238
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day11inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        int height = inputs.size();
        int width = inputs.get(0).length();
        seatChart = new char[height][width];
        for (int i = 0; i < inputs.size(); i++)
            seatChart[i] = inputs.get(i).toCharArray();

        boolean stabilized = updateSeatingArea();
        while(!stabilized){
            stabilized = updateSeatingArea();
        }
        System.out.println(getOccupiedSeatsCount());
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
                    else if (occupiedSurroundingSeats >= 4)
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

    public static int getOccupiedSurroundingSeats(int x, int y) {
        int occupied = 0;
        if (x > 0) {
            if (seatChart[y][x - 1] == OCCUPIED)
                occupied++;
            if (y > 0 && seatChart[y - 1][x - 1] == OCCUPIED)
                occupied++;
            if (y < seatChart.length - 1 && seatChart[y + 1][x - 1] == OCCUPIED)
                occupied++;
        }
        if (x < seatChart[y].length - 1) {
            if (seatChart[y][x + 1] == OCCUPIED)
                occupied++;
            if (y > 0 && seatChart[y - 1][x + 1] == OCCUPIED)
                occupied++;
            if (y < seatChart.length - 1 && seatChart[y + 1][x + 1] == OCCUPIED)
                occupied++;
        }
        if (y > 0 && seatChart[y - 1][x] == OCCUPIED)
            occupied++;
        if (y < seatChart.length - 1 && seatChart[y + 1][x] == OCCUPIED)
            occupied++;
        return occupied;
    }

    public static int getOccupiedSeatsCount() {
        int occupiedSeats = 0;
        for (char[] seat : seatChart)
            for (char c : seat)
                if (c == OCCUPIED)
                    occupiedSeats++;
        return occupiedSeats;
    }
}
