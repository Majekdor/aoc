package dev.majek.aoc;

import org.jetbrains.annotations.NotNull;

public class Day01 implements Challenge {

  @Override
  public @NotNull String day() {
    return "1";
  }

  // 1,014,171
  @Override
  public @NotNull Object partOne() {
    String[] lines = getInput().split("\n");
    for (String i : lines) {
      for (String j : lines) {
        if (Integer.parseInt(i) + Integer.parseInt(j) == 2020) {
          return Integer.parseInt(i) * Integer.parseInt(j);
        }
      }
    }
    return "Not found!";
  }

  // 46,584,630
  @Override
  public @NotNull Object partTwo() {
    String[] lines = getInput().split("\n");
    for (String i : lines) {
      for (String j : lines) {
        for (String k : lines) {
          if (Integer.parseInt(i) + Integer.parseInt(j) + Integer.parseInt(k) == 2020) {
            return Integer.parseInt(i) * Integer.parseInt(j) * Integer.parseInt(k);
          }
        }
      }
    }
    return "Not found!";
  }
}
