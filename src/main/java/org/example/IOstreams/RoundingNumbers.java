package org.example.IOstreams;

import java.io.*;

/**
 * Rounding numbers.
 * Read 2 file names from the console.
 * The first file contains real (fractional) numbers separated by a space. For example, 3.1415.
 * Round the numbers to integers and write them separated by spaces into the second file.
 *
 * Rounding principle:
 * 3.49 => 3
 * 3.50 => 4
 * 3.51 => 4
 * -3.49 => -3
 * -3.50 => -3
 * -3.51 => -4
 */
public class RoundingNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sourceFile = reader.readLine();
        String destFile = reader.readLine();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destFile, true))) {
            while (fileReader.ready()) {
                String[] lineValues = fileReader.readLine().split(" ");
                for (String s : lineValues) {
                    writer.write(Math.round(Double.parseDouble(s)) + " ");
                }
            }
        }
    }
}
