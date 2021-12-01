package dev.majek.adventofcode.DayFifteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day15B {

    /**
     * Code for day fifteen programming challenge B
     * Answer for my values: 436
     */
    public static void main(String[] args) {
        List<Integer> starting = new ArrayList<>(Arrays.asList(20, 0, 1, 11, 6, 3));
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < starting.size(); i++)
            map.put(starting.get(i), (i + 1));
        int turn = starting.size() + 1;
        int next = 0;
        while (turn < 30000000) {
            if (map.containsKey(next)) {
                int diff = turn - map.get(next);
                map.put(next, turn);
                next = diff;
            } else {
                map.put(next, turn);
                next = 0;
            }
            turn++;
        }
        System.out.println("Answer is: " + next);
    }
}