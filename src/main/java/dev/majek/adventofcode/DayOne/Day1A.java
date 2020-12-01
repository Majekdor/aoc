package dev.majek.adventofcode.DayOne;

public class Day1A {

    /**
     * Code for day one programming challenge A
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
