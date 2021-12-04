package dev.majek.adventofcode.DaySixteen;

import dev.majek.adventofcode.Util.Triplet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day16B {

    /**
     * Code for day sixteen programming challenge B
     * Answer for my values:
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("/home/kevinbarnes/IdeaProjects/AdventOfCode/2020/src/main/resources/day16inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        List<Triplet<String, Integer, Integer>> ranges = new ArrayList<>();
        Map<Integer, String> match = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            String input = inputs.get(i);
            Triplet<String, Integer, Integer> range1 = new Triplet<>(
                    input.substring(0, input.indexOf(":")),
                    Integer.parseInt(input.substring(input.indexOf(":")+2, input.indexOf(":")+4)),
                    Integer.parseInt(input.substring(input.indexOf(":")+5, input.indexOf(":")+8))
            );
            Triplet<String, Integer, Integer> range2 = new Triplet<>(
                    input.substring(0, input.indexOf(":")),
                    Integer.parseInt(input.substring(input.indexOf("or ")+3, input.indexOf("or ")+6)),
                    Integer.parseInt(input.substring(input.indexOf("or ")+7, input.indexOf("or ")+10))
            );
            ranges.add(range1); ranges.add(range2);
        }
        for (int i = 25; i < inputs.size(); i++) {
            int[] nums = Arrays.stream(inputs.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            for (int num : nums)
                if (!isWithin(ranges, num))
                    inputs.remove(inputs.get(i));
        }
        for (int i = 25; i < inputs.size(); i++) {
            int[] nums = Arrays.stream(inputs.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < nums.length; j++) {
                if (getField(ranges, nums[j]).contains("departure"))
                    match.put(i+1, getField(ranges, nums[j]));
            }
        }
        int counter = 1;
        int[] nums = Arrays.stream(inputs.get(22).split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < nums.length; i++) {
            if (match.containsKey(i+1))
                counter *= nums[i];
        }
        System.out.println(counter);
    }

    public static String getField(List<Triplet<String, Integer, Integer>> ranges, Integer check) {
        String answer = "";
        for (Triplet<String, Integer, Integer> range : ranges)
            if (check >= range.getSecond() && check <= range.getThird())
                answer = range.getFirst();
        return answer;
    }

    public static boolean isWithin(List<Triplet<String, Integer, Integer>> ranges, Integer check) {
        boolean within = true;
        for (Triplet<String, Integer, Integer> range : ranges)
            if (check >= range.getSecond() && check <= range.getThird())
                return true;
            else
                within = false;
        return within;
    }
}