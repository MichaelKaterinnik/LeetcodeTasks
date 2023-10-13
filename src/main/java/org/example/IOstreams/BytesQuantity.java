package org.example.IOstreams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Read file names from the console until the word "exit" is entered.
 *
 * Pass the filename to the ReadThread thread.
 *
 * The ReadThread thread must find the byte that appears in the file the maximum number of times (if there are
 * several such bytes, choose the smallest one), and add it to the resultMap dictionary, where the String parameter
 * is the file name, the Integer parameter is the byte to search for.
 */
public class BytesQuantity {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String filename = reader.readLine();
            if (filename.equals("exit")) break;
            ReadThread readThread = new ReadThread(filename);
            readThread.start();
        }
    }

    public static class ReadThread extends Thread {
        private final String filename;

        public ReadThread(String fileName) {
            //implement constructor body
            this.filename = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            int[] byteArray = new int[256];
            try (FileInputStream fis = new FileInputStream(filename)) {
                while (fis.available() > 0) {
                    int byteValue = fis.read();
                    byteArray[byteValue]++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int maxValue = 0;
            for (int j : byteArray) {
                if (j > maxValue) maxValue = j;
            }

            List<Integer> maxBytes = new ArrayList<>();
            for (int i = 0; i < byteArray.length; i++) {
                if (byteArray[i] == maxValue) maxBytes.add(i);
            }
            synchronized (resultMap) {
                resultMap.put(filename, Collections.min(maxBytes));
            }
        }
    }
}
