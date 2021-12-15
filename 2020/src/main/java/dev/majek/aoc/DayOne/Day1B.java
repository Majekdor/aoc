package dev.majek.aoc.DayOne;

public class Day1B {

    /**
     * Code for day one programming challenge B
     * Answer for my values: 46,584,630
     */
    public static void main(String[] args) {
        for (int i : Inputs.getInputs())
            for (int j : Inputs.getInputs())
                for (int k : Inputs.getInputs())
                    if (i + j + k == 2020) {
                        System.out.println("Answer is: " + i * j * k);
                        System.exit(0);
                    }
    }
}
