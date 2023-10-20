package org.example.IOstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Looking for the required lines
 *
 * Read the file name from the console.
 * Print to the console all lines from the file that contain only 2 words from the words list.
 * Close streams.
 *
 * Example:
 * words contains words A, B, C (the number of words in the words list can be any).
 *
 * Lines:
 * A B A D //3 words from words, does not fit
 * D A D //1 word from words, does not fit
 * D A B D //2 words - suitable, output
 */
public class AnalysingLines1 {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            while (fileReader.ready()) {
                int counter = 0;
                String line = fileReader.readLine();
                for (String s : line.split(" ")) if (words.contains(s)) {
                    counter++;
                }
                if (counter == 2) System.out.println(line);
            }
        }
    }
}
