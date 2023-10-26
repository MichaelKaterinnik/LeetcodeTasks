package org.example.IOstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Replacing numbers
 * 1. In a static block, initialize the map dictionary in [number-word] pairs from 0 to 12 inclusive.
 * For example, 0 is “zero”, 1 is “one”, 2 is “two”
 * 2. Read the file name from the console, read the contents of the file.
 * 3. Replace all numbers with words using the map dictionary.
 * 4. Display the result on the screen.
 *
 * Example data in the file:
 * This costs 1 buck, but this costs 12.
 * The variable is named file1.
 * 110 is a number.
 *
 * Example console output:
 * This costs one buck, but this costs twelve.
 * The variable is named file1.
 * 110 is a number.
 */
public class ParsingNumbersInStrings {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                int begin_index = 0;
                int end_index = 0;
                int i = 0;
                while (i < line.length()) {
                    // если цифра в начале строки
                    if (i == 0) {
                        if (Character.isDigit(line.charAt(i)) && !Character.isDigit(line.charAt(i + 1))) {
                            end_index = i + 1;
                        }
                        if (Character.isDigit(line.charAt(i)) && Character.isDigit(line.charAt(i + 1)) && !Character.isDigit(line.charAt(i + 2))) {
                            end_index = i + 2;
                        }
                    }
                    // если цифра в середине строки
                    if (i != 0) {
                        if (!Character.isDigit(line.charAt(i - 1)) && !Character.isAlphabetic(line.charAt(i - 1)) && Character.isDigit(line.charAt(i)) && !Character.isDigit(line.charAt(i + 1))) {
                            begin_index = i;
                            end_index = i + 1;
                        }
                        if (!Character.isDigit(line.charAt(i - 1)) && !Character.isAlphabetic(line.charAt(i - 1)) && Character.isDigit(line.charAt(i)) && Character.isDigit(line.charAt(i + 1)) && !Character.isDigit(line.charAt(i + 2))) {
                            begin_index = i;
                            end_index = i + 2;
                        }
                    }

                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        if (line.substring(begin_index, end_index).equals(entry.getKey().toString())) {
                            line = line.substring(0, begin_index).concat(entry.getValue()).concat(line.substring(end_index));
                        }
                    }

                    i++;
                }

                System.out.println(line);
            }
        }
    }

    // ALTERNATE SOLUTION
    public static void alternateSolution() {
        String fileName = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException ignore) {
            /* NOP */
        }

        String fileLine;

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            while ((fileLine = in.readLine()) != null) {
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    fileLine = fileLine.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
                }
                System.out.println(fileLine);
            }
        } catch (IOException ignore) {
            /* NOP */
        }
    }
}
