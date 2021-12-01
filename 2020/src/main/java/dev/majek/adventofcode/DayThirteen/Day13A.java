package dev.majek.adventofcode.DayThirteen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13A {

    /**
     * Code for day thirteen programming challenge A
     * Answer for my values: 104
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day13inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        int target = Integer.parseInt(inputs.get(0));
        List<String> numbers = Arrays.stream(inputs.get(1).split(",")).collect(Collectors.toList());
        int bestBusID = Integer.MAX_VALUE, waitTime = 0, departTime = Integer.MAX_VALUE;
        for (String num : numbers) {
            if (!num.equals("x")) {
                int nextDeparture = target + (Integer.parseInt(num) -
                        (target % Integer.parseInt(num))) % Integer.parseInt(num);
                if (nextDeparture < departTime) {
                    departTime = nextDeparture;
                    bestBusID = Integer.parseInt(num);
                    waitTime = nextDeparture - target;
                }
            }
        }
        System.out.println("Answer is: " + bestBusID * waitTime);
    }
}