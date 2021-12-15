package dev.majek.aoc.DayFour;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day4B {

    /**
     * Code for day four programming challenge A
     * Answer for my values: 224
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day4inputs.txt")));
        String[] inputs = data.split("\n\n");
        int counter = 0;
        for (String input : inputs) {
            if (input.contains("ecl") && input.contains("pid") && input.contains("eyr") && input.contains("hcl") &&
                    input.contains("byr") && input.contains("iyr") && input.contains("hgt")) {
                String ecl = getField(input, "ecl:");
                boolean validEcl = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl);
                String pid = getField(input, "pid:");
                boolean validPid = pid.matches("[0-9]{9}");
                String eyr = getField(input, "eyr:");
                boolean validEyr = eyr.length() == 4 && Integer.parseInt(eyr) >= 2020 && Integer.parseInt(eyr) <= 2030;
                String hcl = getField(input, "hcl:");
                boolean validHcl = hcl.matches("#[a-fA-F0-9]{6}");
                String byr = getField(input, "byr:");
                boolean validByr = byr.length() == 4 && Integer.parseInt(byr) >= 1920 && Integer.parseInt(byr) <= 2002;
                String iyr = getField(input, "iyr:");
                boolean validIyr = iyr.length() == 4 && Integer.parseInt(iyr) >= 2010 && Integer.parseInt(iyr) <= 2020;
                String hgt = getField(input, "hgt:"); String num = hgt.substring(0, hgt.length()-2);
                boolean validHgt = (hgt.contains("cm") && Integer.parseInt(num) >= 150 && Integer.parseInt(num) <= 193)
                        || (hgt.contains("in") && Integer.parseInt(num) >= 59 && Integer.parseInt(num) <= 76);
                if (validEyr && validByr && validIyr && validHcl && validPid && validHgt && validEcl)
                    counter++;
            }
        }
        System.out.println("Answer is: " + counter);
    }

    public static String getField(String input, String field) {
        try {
            return input.substring(input.indexOf(field) + 4, input.indexOf(findWhiteSpace(
                    input.indexOf(field) + 4, input), input.indexOf(field) + 4));
        } catch (Exception ignored) {
            return input.substring(input.indexOf(field) + 4);
        }
    }

    public static char findWhiteSpace(int start, String input) {
        int index = -1;
        for (int i = start; i < input.length(); i++)
            if (Character.isWhitespace(input.charAt(i)) || input.charAt(i) == '\n') {
                index = i;
                break;
            }
        if (index == -1)
            index = input.length();
        return input.charAt(index);
    }
}