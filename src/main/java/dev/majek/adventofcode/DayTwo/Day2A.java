package dev.majek.adventofcode.DayTwo;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day2A {

    /**
     * Code for day two programming challenge A
     * Answer for my values: 398
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day2inputs.txt")));
        String[] inputs = data.split("\n");
        int runningTotal = 0;
        for (String input : inputs) {
            int lowerBound = Integer.parseInt(input.substring(0,input.indexOf("-")));
            int upperBound = Integer.parseInt(input.substring(input.indexOf("-")+1, input.indexOf(":")-2));
            char letter = input.charAt(input.indexOf(":")-1);
            String password = input.substring(input.indexOf(":")+1);
            int count = 0;
            for (char a : password.toCharArray()) {
                if (a == letter)
                    count++;
            }
            if (count >= lowerBound && count <= upperBound)
                runningTotal++;
        }
        System.out.println("Answer is: " + runningTotal);
    }
}
