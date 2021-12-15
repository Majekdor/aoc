package dev.majek.aoc.DayTwo;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day2B {

    /**
     * Code for day two programming challenge B
     * Answer for my values: 562
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day2inputs.txt")));
        String[] inputs = data.split("\n");
        int runningTotal = 0;
        for (String input : inputs) {
            int firstNum = Integer.parseInt(input.substring(0,input.indexOf("-")));
            int secondNum = Integer.parseInt(input.substring(input.indexOf("-")+1, input.indexOf(":")-2));
            char letter = input.charAt(input.indexOf(":")-1);
            String password = input.substring(input.indexOf(":")+1);
            if (letter == password.charAt(firstNum) && letter != password.charAt(secondNum) ||
                    letter == password.charAt(secondNum) && letter != password.charAt(firstNum))
                runningTotal++;
        }
        System.out.println("Answer is: " + runningTotal);
    }
}
