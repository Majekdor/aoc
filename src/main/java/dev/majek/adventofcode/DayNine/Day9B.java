package dev.majek.adventofcode.DayNine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day9B {

    /**
     * Code for day nine programming challenge B
     * Answer for my values: 4011064
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day9inputs.txt")));
        String[] inputs = data.split("\n");
        List<Integer> list = new ArrayList<>();
        for (String input : inputs) {
            char[] chars = input.toCharArray();
            int val = 0;
            for (char c : chars) {
                val *= 10;
                val += c - '0';
            }
            list.add(val);
        }

        int ensemble = 25;
        int[] bad = findBadPreamble(ensemble, list);
        List<Integer> numbers = findNumbersThatEqual(bad[1], list).stream().sorted().collect(Collectors.toList());
        Integer min = numbers.get(0);
        Integer max = numbers.get(numbers.size() - 1);
        System.out.println("Answer is: " + (min + max));

    }

    private static List<Integer> findNumbersThatEqual(int no, List<Integer> list) {
        int val = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            val += list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                val += list.get(j);
                if (val == no) {
                    List<Integer> tr = new ArrayList<>();
                    for (int k = i; k <= j; k++) {
                        tr.add(list.get(k));
                    }
                    return tr;
                } else if (val > no) break;
            }
            val = 0;
        }
        return new ArrayList<>();
    }

    private static int[] findBadPreamble(int preamble, List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < preamble; i++) {
            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        for (int i = preamble; i < list.size(); i++) {
            int target = list.get(i);
            boolean wasFound = false;
            for (int j = i - preamble; j < i; j++) {
                int test = target - list.get(j);
                Integer found = map.get(test);
                if (found != null && found > 0 && (test != list.get(j) || (test == list.get(j) && found > 1))) {
                    wasFound = true;
                    break;
                }
            }
            if (!wasFound) {
                return new int[]{i, list.get(i)};
            }

            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
            int prev = map.getOrDefault(list.get(i - preamble), 0);
            if (prev <= 1) {
                map.remove(list.get(i - preamble));
            } else {
                map.put(list.get(i - preamble), --prev);
            }
        }
        return new int[0];
    }
}