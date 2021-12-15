package dev.majek.aoc.DayEighteen;

import dev.majek.aoc.util.Pair;
import org.codehaus.plexus.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day18A {

    /**
     * Code for day eighteen programming challenge A
     * Answer for my values: 4297397455886
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day18inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        long runningTotal = 0;
        for (String input : inputs)
            runningTotal += Long.parseLong(evaluate(input));
        System.out.println("Answer is: " + runningTotal);
    }

    public static String evaluate(String expression) {
        while (expression.contains("(")) {
            String enclosed = getEnclosed(expression.indexOf("("), expression).getFirst();
            String parentheses = expression.substring(expression.indexOf(enclosed) - 1,
                    expression.indexOf(enclosed) + enclosed.length() + 1);
            expression = expression.replace(parentheses, evaluate(enclosed));
        }
        while (expression.contains(" ")) {
            StringBuilder expressionBuilder = new StringBuilder();
            int spaceCounter = 0;
            for (char c : expression.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    spaceCounter++;
                    if (spaceCounter > 2)
                        break;
                }
                expressionBuilder.append(c);
            }
            String[] values = expressionBuilder.toString().split(" ");
            long result;
            if (values[1].equals("+"))
                result = Math.addExact(Long.parseLong(values[0]), Long.parseLong(values[2]));
            else
                result = Math.multiplyExact(Long.parseLong(values[0]), Long.parseLong(values[2]));
            expression = StringUtils.replaceOnce(expression, expressionBuilder.toString(), String.valueOf(result));
        }
        return expression;
    }

    public static Pair<String, Integer> getEnclosed(int start, String string) {
        boolean curved = string.charAt(start) == '(';
        int depth = 1, i = start + 1;
        while(depth > 0) {
            if(i == string.length())
                return new Pair<>(null, -1);
            char c = string.charAt(i++);
            if(c == (curved ? ')' : '}'))
                -- depth;
            else if(c == (curved ? '(' : '{'))
                ++ depth;
        }
        return new Pair<>(string.substring(start + 1, i - 1), i);
    }
}