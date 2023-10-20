package org.example.algorhytmical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The main method takes the file name as its first parameter.
 * In this file, each line looks like this:
 * name value
 * where [name] is String, [value] is double. [name] and [value] are separated by a space.
 *
 * For each name, calculate the sum of all its values.
 * Print to the console the names in alphabetical order that have the maximum amount.
 * Separate names with a space or start on a new line.
 *
 * Example input file:
 * Petrov 0.501
 * Ivanov 1.35
 * Petrov 0.85
 *
 * Example output:
 * Petrov
 */
public class HashMaxValue {
    public static void main(String[] args) throws IOException {
        Map<String, Double> valuesSum = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String[] data = reader.readLine().split(" ");
                valuesSum.put(data[0], (valuesSum.getOrDefault(data[0], 0.0) + Double.parseDouble(data[1])));
            }
        }

        double max = 0.0;
        List<String> maxS = new ArrayList<>();
        for (Map.Entry<String, Double> entry : valuesSum.entrySet()) {
            if (entry.getValue() > max) {
                if (maxS.size() != 0) {
                    maxS.remove(maxS.size() - 1);
                }
                max = entry.getValue();
                maxS.add(entry.getKey());
            } else if (entry.getValue() == max) {
                maxS.add(entry.getKey());
            }
        }

        maxS.forEach(System.out::println);
    }
}
