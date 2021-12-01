package dev.majek.adventofcode.DayEighteen;

import dev.majek.adventofcode.Util.Pair;
import org.codehaus.plexus.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day18B {

    /**
     * Code for day eighteen programming challenge B
     * Answer for my values: 93000656194428
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\main\\resources\\day18inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        long runningTotal = 0;
        for (String input : inputs)
            runningTotal += Long.parseLong(evaluate(input, 0));
        System.out.println("Answer is: " + runningTotal);
    }

    public static String evaluate(String expression, Integer start) {
        while (expression.contains("(")) {
            String enclosed = getEnclosed(expression.indexOf("("), expression).getFirst();
            String parentheses = expression.substring(expression.indexOf(enclosed) - 1,
                    expression.indexOf(enclosed) + enclosed.length() + 1);
            expression = expression.replace(parentheses, evaluate(enclosed, 0));
        }
        while (expression.contains(" ")) {
            StringBuilder expressionBuilder = new StringBuilder();
            int spaceCounter = 0;
            for (int i = start; i < expression.length(); i++) {
                if (Character.isWhitespace(expression.charAt(i))) {
                    spaceCounter++;
                    if (spaceCounter > 2)
                        break;
                }
                expressionBuilder.append(expression.charAt(i));
            }
            String[] values = expressionBuilder.toString().split(" ");
            if (values.length == 1) {
                expression = evaluate(expression, 0); continue;
            }
            long result;
            if (values[1].equals("+"))
                result = Math.addExact(Long.parseLong(values[0]), Long.parseLong(values[2]));
            else {
                if (expression.contains("+")) {
                    expression = evaluate(expression, (values[0].length() + 3) + start); continue;
                }
                if (start != 0) {
                    expression = evaluate(expression, 0); continue;
                }
                result = Math.multiplyExact(Long.parseLong(values[0]), Long.parseLong(values[2]));
            }
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