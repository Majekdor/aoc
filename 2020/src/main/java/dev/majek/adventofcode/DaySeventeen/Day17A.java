package dev.majek.adventofcode.DaySeventeen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17A {

    public static final char ACTIVE = '#';
    public static final char INACTIVE = '.';
    public static final String INPUT =
            "#.#..###\n" +
            ".#....##\n" +
            ".###...#\n" +
            "..####..\n" +
            "....###.\n" +
            "##.#.#.#\n" +
            "..#..##.\n" +
            "#.....##";

    /**
     * Code for day seventeen programming challenge A
     * Answer for my values: 391
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day17inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        char[][][] layers = new char[1][inputs.size()][inputs.get(0).length()];
        for (int y = 0; y < inputs.size(); y++)
            layers[0][y] = inputs.get(y).toCharArray();
        for (int i = 0; i < 6; i++)
            layers = nextState(layers);
        System.out.println(countActiveElements(layers));
    }

    private static char[][][] nextState(final char[][][] currentState) {
        char[][][] newState = new char[currentState.length + 2][currentState[0].length + 2][currentState[0][0].length + 2];

        for (int z = 0; z < newState.length; z++) {
            for (int y = 0; y < newState[z].length; y++) {
                for (int x = 0; x < newState[z][y].length; x++) {
                    newState[z][y][x] = nextStateForElement(z, y, x, currentState);
                }
            }
        }

        return newState;
    }

    private static char nextStateForElement(final int x, final int y, final int z, final char[][][] previousState) {
        int activeNeighbours = 0;
        char lastStateOfElement = '.';

        for (int possibleZ = z - 1; possibleZ <= z + 1; possibleZ++) {
            for (int possibleY = y - 1; possibleY <= y + 1; possibleY++) {
                for (int possibleX = x - 1; possibleX <= x + 1; possibleX++) {
                    if (possibleZ > 0 && possibleZ <= previousState.length && possibleY > 0
                            && possibleY <= previousState[possibleZ - 1].length && possibleX > 0
                            && possibleX <= previousState[possibleZ - 1][possibleY - 1].length
                            && previousState[possibleZ - 1][possibleY - 1][possibleX - 1] == ACTIVE) {
                        if (possibleZ != z || possibleY != y || possibleX != x) {
                            activeNeighbours++;
                        } else {
                            lastStateOfElement = '#';
                        }
                    }
                }
            }
        }

        if (lastStateOfElement == ACTIVE && (activeNeighbours == 2 || activeNeighbours == 3)) {
            return ACTIVE;
        } else if (lastStateOfElement == INACTIVE && activeNeighbours == 3) {
            return ACTIVE;
        }

        return INACTIVE;
    }

    private static int countActiveElements(final char[][][] layers) {
        int activeElements = 0;

        for (char[][] layer : layers) {
            for (char[] chars : layer) {
                for (char aChar : chars) {
                    if (aChar == ACTIVE) {
                        activeElements++;
                    }
                }
            }
        }
        return activeElements;
    }
}
