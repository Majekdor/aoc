package dev.majek.adventofcode.DaySixteen;

import dev.majek.adventofcode.Util.Pair;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16A {

    /**
     * Code for day sixteen programming challenge A
     * Answer for my values: 24980
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day16inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String input = inputs.get(i);
            Pair<Integer, Integer> range1 = new Pair<>(
                    Integer.parseInt(input.substring(input.indexOf(":")+2, input.indexOf(":")+4)),
                    Integer.parseInt(input.substring(input.indexOf(":")+5, input.indexOf(":")+8))
            );
            Pair<Integer, Integer> range2 = new Pair<>(
                    Integer.parseInt(input.substring(input.indexOf("or ")+3, input.indexOf("or ")+6)),
                    Integer.parseInt(input.substring(input.indexOf("or ")+7, input.indexOf("or ")+10))
            );
            ranges.add(range1); ranges.add(range2);
        }
        int counter = 0;
        for (int i = 25; i < inputs.size(); i++) {
            int[] nums = Arrays.stream(inputs.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            for (int num : nums)
                if (!isWithin(ranges, num))
                    counter += num;
        }
        System.out.println("Answer is: " + counter);
    }

    public static boolean isWithin(List<Pair<Integer, Integer>> ranges, Integer check) {
        boolean within = true;
        for (Pair<Integer, Integer> range : ranges)
            if (check >= range.getFirst() && check <= range.getSecond())
                return true;
            else
                within = false;
        return within;
    }
}
