package dev.majek.adventofcode.DayFive;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day5B {

    /**
     * Code for day five programming challenge B
     * Answer for my values: 676
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day5inputs.txt")));
        String[] inputs = data.split("\n");
        int[] seatIDs = new int[961];
        for (int i = 0; i < inputs.length; i++) {
            int frontRow = 0; int backRow = 127; double middleRow = Math.ceil((backRow + frontRow) / 2.0);
            int leftColumn = 0; int rightColumn = 7; double middleColumn = Math.ceil((leftColumn + rightColumn) / 2.0);
            char[] chars = inputs[i].toCharArray();
            for (char c : chars) {
                switch (c) {
                    case 'F':
                        backRow = (int) middleRow;
                        middleRow = Math.ceil((backRow + frontRow) / 2.0);
                        break;
                    case 'B':
                        frontRow = (int) middleRow;
                        middleRow = Math.ceil((backRow + frontRow) / 2.0);
                        break;
                    case 'L':
                        rightColumn = (int) middleColumn;
                        middleColumn = Math.ceil((leftColumn + rightColumn) / 2.0);
                        break;
                    case 'R':
                        leftColumn = (int) middleColumn;
                        middleColumn = Math.ceil((leftColumn + rightColumn) / 2.0);
                        break;
                }
            }
            int row = (int) Math.min(frontRow, Math.min(middleRow, backRow));
            int column = (int) Math.min(leftColumn, Math.min(middleColumn, rightColumn));
            seatIDs[i] = ((row * 8) + column);
        }
        Arrays.sort(seatIDs); int counter = seatIDs[1];
        for (int i = 1; i < seatIDs.length; i++) {
            try {
                if (seatIDs[i] + 1 != seatIDs[i + 1] || seatIDs[i] - 1 == seatIDs[i - 1]) {
                    counter++; continue;
                }
            } catch (Exception ex) {
                counter++; continue;
            }
            if (seatIDs[i] != counter) {
                System.out.println("Answer is: " + counter); break;
            }
            else
                counter++;
        }
    }
}