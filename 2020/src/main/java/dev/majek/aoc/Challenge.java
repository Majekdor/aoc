package dev.majek.aoc;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface Challenge {

  @NotNull String day();

  @NotNull Object partOne();

  @NotNull Object partTwo();

  default String getInput() {
    try {
      if (AdventOfCode.os() == AdventOfCode.OS.LINUX) {
        return new String(Files.readAllBytes(
            Paths.get("/home/kevinbarnes/IdeaProjects/aoc/2020/src/main/resources/day" + day() + "inputs.txt")));
      } else {
        return new String(Files.readAllBytes(
            Paths.get("C:\\Users\\ksbar\\IdeaProjects\\aoc\\2020\\src\\main\\resources\\day" + day() + "inputs.txt")));
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to find inputs for day " + day());
    }
  }

  default void run() {
    long start = System.currentTimeMillis();
    System.out.println("Starting day " + day() + "...");
    System.out.println("Part One: " + partOne());
    System.out.println("Part Two: " + partTwo());
    System.out.println("...finished in " + (System.currentTimeMillis() - start) + "ms");
  }
}
