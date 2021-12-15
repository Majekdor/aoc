package dev.majek.aoc.DayFourteen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day14B {

    /**
     * Code for day fourteen programming challenge B
     * Answer for my values: 5272149590143
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day14inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        String mask = "";
        Map<String, Long> memory = new HashMap<>();
        for (String input : inputs) {
            String afterEquals = input.substring(input.indexOf("=") + 2);
            if (input.startsWith("mask")) {
                mask = afterEquals;
                continue;
            }
            int address = Integer.parseInt(input.substring(input.indexOf("[")+1, input.indexOf("]")));
            long value = Long.parseLong(afterEquals);
            ArrayList<String> addresses = getAddresses(address, mask);
            for (String add : addresses)
                if (memory.containsKey(add))
                    memory.replace(add, value);
                else
                    memory.put(add, value);
        }
        long answer = 0L;
        for (long val : memory.values())
            answer += val;
        System.out.println("Answer is: " + answer);
    }

    private static ArrayList<String> getAddresses(int address, String mask) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add("");

        StringBuilder value = new StringBuilder(Integer.toBinaryString(address));
        while (value.length() < 36)
            value.insert(0, "0");

        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                int s = toReturn.size();
                for (int j = 0; j < s; j++) {
                    String add = toReturn.remove(0);
                    toReturn.add(add + "0");
                    toReturn.add(add + "1");
                }
            } else if (mask.charAt(i) == '1') {
                for (int a = 0; a < toReturn.size(); a++)
                    toReturn.set(a, toReturn.get(a) + "1");
            } else if (mask.charAt(i) == '0')
                for (int a = 0; a < toReturn.size(); a++)
                    toReturn.set(a, toReturn.get(a) + value.charAt(i));
        }
        return toReturn;
    }
}
