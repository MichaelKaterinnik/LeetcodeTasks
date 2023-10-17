package org.example.stringTasks;

import java.io.*;


/**
 * Read 2 file paths from the console.
 * Output to the second file all the integers that are in the first file (54y is not a number).
 * Numbers are separated by spaces.
 */
public class FilterOnlyIntegers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source = reader.readLine();
        String dest = reader.readLine();
        reader.close();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(source));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(dest))) {

            while (fileReader.ready()) {
                String[] data = fileReader.readLine().split(" ");
                for (String s : data) {
                    if (s.matches("\\b\\d+\\b")) fileWriter.write(s + " ");
                }
            }
        }
    }
}
