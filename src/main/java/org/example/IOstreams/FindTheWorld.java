package org.example.IOstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Read the file name from the console.
 * The file contains words separated by punctuation marks.
 * Print to the console the number of words "world" that appear in the file.
 */
public class FindTheWorld {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source = reader.readLine();
        reader.close();
        int counter = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(source))) {
            while (fileReader.ready()) {
                String[] data = fileReader.readLine().split("[\\p{P}\\s]");
                for (String s : data) {
                    if (s.equals("world")) counter++;
                }
            }
        }

        System.out.println(counter);
    }
}
