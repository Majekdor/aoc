package dev.majek.aoc.DayFourteen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14A {

    /**
     * Code for day fourteen programming challenge A
     * Answer for my values: 5875750429995
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day14inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        String mask = "";
        Map<Integer, Long> memory = new HashMap<>();
        for (String input : inputs) {
            String afterEquals = input.substring(input.indexOf("=") + 2);
            if (input.contains("mask")) {
                mask = afterEquals;
                continue;
            }
            int address = Integer.parseInt(input.substring(input.indexOf("[")+1, input.indexOf("]")));
            StringBuilder value = new StringBuilder(Integer.toString(Integer.parseInt(afterEquals), 2));
            while (value.length() != 36)
                value.insert(0, "0");
            StringBuilder newValue = new StringBuilder(36);
            for (int i = 0; i < 36; i++) {
                char[] maskBits = mask.toCharArray();
                if (maskBits[i] == '0')
                    newValue.append("0");
                else if (maskBits[i] == '1')
                    newValue.append("1");
                else
                    newValue.append(value.toString().toCharArray()[i]);
            }
            if (memory.containsKey(address))
                memory.replace(address, Long.parseLong(String.valueOf(newValue), 2));
            else
                memory.put(address, Long.parseLong(String.valueOf(newValue), 2));
        }
        long answer = 0L;
        for (long value : memory.values())
            answer += value;
        System.out.println("Answer is: " + answer);
    }
}