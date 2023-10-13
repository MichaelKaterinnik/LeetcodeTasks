package org.example.IOstreams;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Symbol occurrence
 * The program is launched with one parameter - the name of the file that contains English text.
 * The program should count the frequency of occurrence of each symbol.
 * Sort result by ascending ASCII code.
 * Print the sorted result to the console:
 * [char1] frequency1
 * [character2] frequency2
 */

public class SymbolsQuantity {
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> symbolsCounter = new TreeMap<>();
        case1(symbolsCounter, args[0]);
        case2(symbolsCounter, args[0]);
    }

    public static void case1(Map<Integer, Integer> symbolsMap, String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            while (fis.available() > 0) {
                int nextSymbol = fis.read();
                symbolsMap.put(nextSymbol, symbolsMap.getOrDefault(nextSymbol, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : symbolsMap.entrySet()) {
            int asciiSymbol = entry.getKey();
            char symbol = (char) asciiSymbol;
            System.out.println(symbol + " " + entry.getValue());
        }
    }

    public static void case2(Map<Integer, Integer> symbolsMap, String filename) throws IOException {
        int[] arr = new int[256];
        try (FileInputStream fis = new FileInputStream(filename)) {
            while (fis.available() > 0) {
                int i = fis.read();
                arr[i]++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                System.out.println((char) i + " " + arr[i]);
            }
        }
    }
}
