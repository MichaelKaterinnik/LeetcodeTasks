package org.example.IOstreams;

import java.io.*;

/**
 * Words with numbers
 * The main method receives the name of file1 as its first parameter, and file2 as its second parameter.
 * File1 contains lines with words separated by spaces.
 * Write, separated by spaces, into File2 all words that contain numbers, for example, a1, abc3d or 564.
 */
public class FindDigits {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
            while (reader.ready()) {
                String[] lineData = reader.readLine().split(" ");
                for (String s : lineData) {
                    if (s.chars().anyMatch(Character::isDigit)) {
                        writer.write(s + " ");
                    }
                }
            }
        }
    }
}
