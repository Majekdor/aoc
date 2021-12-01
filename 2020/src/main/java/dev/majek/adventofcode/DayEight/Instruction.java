package dev.majek.adventofcode.DayEight;

public class Instruction {

    private final Operation operation;
    private final int argument;

    public Instruction(final String code) {
        String[] split = code.split(" ");
        switch (split[0]) {
            case "acc":
                this.operation = Operation.ACCUMULATOR;
                break;
            case "jmp":
                this.operation = Operation.JUMP;
                break;
            default:
                this.operation = Operation.NO_OPERATION;
        }
        this.argument = Integer.parseInt(split[1]);
    }

    public Instruction(final Operation operation, final int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }
}

enum Operation {
    ACCUMULATOR,
    JUMP,
    NO_OPERATION
}
