package dev.majek.aoc.DayOne;

public class Day1A {

    /**
     * Code for day one programming challenge A
     * Answer for my values: 1,014,171
     */
    public static void main(String[] args) {
        for (int i : Inputs.getInputs())
            for (int j : Inputs.getInputs())
                if (i + j == 2020) {
                    System.out.println("Answer is: " + i * j);
                    System.exit(0);
                }
    }
}
