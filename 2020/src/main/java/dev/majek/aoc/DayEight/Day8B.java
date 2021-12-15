package dev.majek.aoc.DayEight;

import dev.majek.aoc.util.Pair;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day8B {

    /**
     * Code for day eight programming challenge B
     * Answer for my values: 515
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\Documents\\GitHub\\" +
                "AdventOfCode\\src\\main\\resources\\day8inputs.txt")));
        List<String> inputs = Arrays.stream(data.split("\n")).collect(Collectors.toList());
        List<Instruction> instructions = inputs.stream().map(Instruction::new).collect(
                Collectors.toList());
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            Pair<Integer, Boolean> testResult = null;

            if (instruction.getOperation() == Operation.JUMP) {
                testResult = switchOps(instructions, i, Operation.NO_OPERATION);
            } else if (instruction.getOperation() == Operation.NO_OPERATION) {
                testResult = switchOps(instructions, i, Operation.JUMP);
            }

            if (testResult != null && testResult.getSecond()) {
               System.out.println("Answer is: " + testResult.getFirst());
               System.exit(0);
            }
        }
    }

    private static Pair<Integer, Boolean> runProgram(List<Instruction> instructions) {
        Set<Integer> usedIndex = new HashSet<>();
        int accumulator = 0;
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (usedIndex.contains(i)) {
                return new Pair<>(accumulator, false);
            }
            usedIndex.add(i);
            switch (instruction.getOperation()) {
                case ACCUMULATOR:
                    accumulator += instruction.getArgument();
                    break;
                case JUMP:
                    i += instruction.getArgument(); i--;
                    break;
            }
        }
        return new Pair<>(accumulator, true);
    }

    private static Pair<Integer, Boolean> switchOps(List<Instruction> instructions, Integer switchIndex, Operation newOperation) {
        List<Instruction> clone = new ArrayList<>(instructions);
        clone.set(switchIndex, new Instruction(newOperation, clone.get(switchIndex).getArgument()));
        return runProgram(clone);
    }
}
