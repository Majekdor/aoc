package dev.majek.adventofcode.DayThirteen;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13B {

    /**
     * Code for day thirteen programming challenge B
     * Answer for my values: 842186186521918
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day13inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        String[] busIDs = inputs.get(1).split(",");
        long[][] numbers = IntStream.range(0, busIDs.length).filter(i -> !busIDs[i].equals("x"))
                .mapToObj(i -> new long[]{Long.parseLong(busIDs[i]), i})
                .toArray(long[][]::new);
        long product = Arrays.stream(numbers).mapToLong(a -> a[0]).reduce((a, b) -> a * b).getAsLong();
        long sum = Arrays.stream(numbers).mapToLong(a -> a[1] * (product/a[0]) * inverseModulo(product/a[0], a[0])).sum();
        System.out.println("Answer is: " + (product - sum % product));
    }

    // This method took me 30 minutes to get right -.-
    public static long inverseModulo(long x, long y) {
        if(x != 0) {
            long modulo = y % x;
            return modulo == 0 ? 1 : y - inverseModulo(modulo, x) * y / x;
        }
        return 0;
    }
}