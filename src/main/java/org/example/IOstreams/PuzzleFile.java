package org.example.IOstreams;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Collecting the file
 * We assemble the file from pieces.
 * Read file names from the console.
 * Each file has a name: [someName].partN.
 *
 * For example, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
 *
 * File names are presented in random order. The input ends with the word "end".
 * In the folder where all read files are located, create a file without the [.partN] suffix.
 *
 * For example, Lion.avi.
 *
 * We need to rewrite all the bytes from the part files into it using a buffer.
 * Copy the files in strict sequence, first the first part, then the second, ..., and finally the last.
 */

public class PuzzleFile {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> fileParts = new ArrayList<>();
        String filename;
        while (!(filename = reader.readLine()).equals("end")) {
            fileParts.add(filename);
        }

        fileParts.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int partnumber1 = extractNumber(o1);
                int partnumber2 = extractNumber(o2);
                return Integer.compare(partnumber1, partnumber2);
            }

            private int extractNumber(String s) {
                Pattern pattern = Pattern.compile("part(\\d+)$");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    return Integer.parseInt(matcher.group(1));
                }
                return 0;
            }
        });

        String s = fileParts.get(0);
        String destFileName = s.substring(0, s.lastIndexOf("."));
        try (BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(destFileName, true))) {
            for (String filePart : fileParts) {
                try (BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(filePart))) {
                    byte[] buffer = new byte[fileReader.available()];
                    while (fileReader.available() > 0) {
                        fileReader.read(buffer);
                        fileWriter.write(buffer);
                    }
                }
            }
        }
    }
}
