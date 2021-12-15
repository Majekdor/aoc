package dev.majek.aoc.DayFifteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day15A {

    /**
     * Code for day fifteen programming challenge A
     * Answer for my values: 421
     */
    public static void main(String[] args) {                    // placeholder
        List<Integer> starting = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE,20,0,1,11,6,3));
        for (int i = 7; i <= 2020; i++) {
            if (Collections.frequency(starting, starting.get(i-1)) > 1) {
                int mostRecent = starting.get(i-1);
                Collections.reverse(starting);
                int count = 0;
                for (int j = 1; j < starting.size(); j++) {
                    count++;
                    if (starting.get(j) == mostRecent)
                        break;
                }
                Collections.reverse(starting);
                starting.add(count);
            } else
                starting.add(0);
        }
        System.out.println("Answer is: " + starting.get(2020));
    }
}