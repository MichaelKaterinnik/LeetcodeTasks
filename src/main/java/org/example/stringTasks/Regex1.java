package org.example.stringTasks;

import java.io.*;

/**
 * Read 2 file names from the console.
 * The first File contains text.
 * Read the contents of the first file, removing all punctuation, including newlines.
 *
 * Output the result to a second file.
 */
public class Regex1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source = reader.readLine();
        String dest = reader.readLine();
        reader.close();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(source));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(dest))) {

            while (fileReader.ready()) {
                String data = fileReader.readLine();
                fileWriter.write(data.replaceAll("[\\p{P}\\r?\\n]+", ""));
            }
        }
    }
}
