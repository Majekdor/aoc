package dev.majek.adventofcode.DayFive;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day5A {

    /**
     * Code for day five programming challenge A
     * Answer for my values: 998
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day5inputs.txt")));
        String[] inputs = data.split("\n");
        int highestPID = 0;
        for (String input : inputs) {
            int frontRow = 0; int backRow = 127; double middleRow = Math.ceil((backRow + frontRow) / 2.0);
            int leftColumn = 0; int rightColumn = 7; double middleColumn = Math.ceil((leftColumn + rightColumn) / 2.0);
            char[] chars = input.toCharArray();
            for (char character : chars) {
                switch (character) {
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
            highestPID = Math.max(((row * 8) + column), highestPID);
        }
        System.out.println("Answer is: " + highestPID);
    }
}
