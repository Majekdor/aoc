package dev.majek.adventofcode.DayNine;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day9A {

    /**
     * Code for day nine programming challenge A
     * Answer for my values: 23278925
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day9inputs.txt")));
        long[] inputs = Arrays.stream(data.split("\n")).mapToLong(Long::parseLong).toArray();
        for (int k = 25; k < inputs.length; k ++) {
            long[] previous = getPreamble(inputs, k);
            boolean cont = false;
            for (long prev : previous) {
                for (long prev2 : previous) {
                    if ((prev + prev2) == inputs[k] && prev != prev2) {
                        cont = true;
                        break;
                    }
                }
            }
            if (!cont) {
                System.out.println("Answer is: " + inputs[k]);
                System.exit(0);
            }
        }
    }

    public static long[] getPreamble(long[] values, int index){
        long[] preamble = new long[25];
        for(int i = 25; i > 0; i--){
            preamble[i-1] = values[index-i];
        }
        return preamble;
    }

}
