import java.nio.file.Files;
import java.nio.file.Paths;

public class DayOneFromFile {

    /**
     * Experimenting with reading value from files for day one challenge.
     * Answer for my values: 1,014,171 for 1A and 46,584,630 1B
     */
    public static void main(String[] args) throws Exception {
        String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\ksbar\\IdeaProjects\\" +
                "AdventOfCode\\src\\test\\resources\\day1inputs.txt")));
        String[] delim = data.split("\n");
        int[] inputs = new int[data.length()];
        int counter = 0;
        for (String str : delim) {
            inputs[counter] = Integer.parseInt(str);
            counter++;
        }
        boolean found = false;
        for (int i : inputs)
            for (int j : inputs)
                if (i + j == 2020 && !found) {
                    System.out.println("Answer for 1A is: " + i * j);
                    found = true;
                }
        for (int i : inputs)
            for (int j : inputs)
                for (int k : inputs)
                    if (i + j + k == 2020) {
                        System.out.println("Answer for 1B is: " + i * j * k);
                        System.exit(13);
                    }
    }
}

