package dev.majek.aoc;

import java.util.HashMap;
import java.util.Map;

public class AdventOfCode {

  private static final Map<Integer, Challenge> challengeMap = new HashMap<>();

  public static void main(String[] args) {
    registerDays();
    if (args.length == 0) {
       System.out.println("Running all days... \n");
      for (int i : challengeMap.keySet()) {
        challengeMap.get(i).run();
        System.out.println();
      }
    } else {
      challengeMap.get(Integer.parseInt(args[0])).run();
    }
  }

  private static void registerDays() {
    challengeMap.put(1, new Day01());
  }

  public static OS os() {
    return OS.LINUX;
  }

  public enum OS {
    LINUX,
    WINDOWS,
  }
}
