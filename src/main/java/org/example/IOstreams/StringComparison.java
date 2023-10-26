package org.example.IOstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Read 2 file paths from the console - file1, file2.
 * The files contain strings. Since file2 is an updated version of file1, some of the lines are the same.
 * We need to create a combined version of the lines from both files and write these lines into the lines list.
 * Association rules:
 *
 * If the line in both files matches, then it is included in the result with the operation (prefix) SAME.
 * For example, SAME line1.
 * If the line is in file1, but it is not in file2, then we consider that the line was deleted and the result ends up
 * with the operation (prefix) REMOVED.
 * For example, REMOVED line2.
 * If the line is not in file1, but it is in file2, then we consider that the line was added and it ends up in the
 * result with the operation (prefix) ADDED.
 * For example, ADDED line0.
 * ADDED and REMOVED operations cannot be consecutive; they are always separated by SAME.
 * Blank lines are given in the example for clarity and mean that this line does not exist in a particular file.
 * There are no empty lines in the original and edited files!
 * Example 1:
 * contents of the original file (file1):
 * line1
 * line2
 * line3
 * line4
 * line5
 * line1
 * line2
 * line3
 * line5
 * line0
 *
 * contents of the "edited" file (file2):
 * line1
 * line3
 * line5
 * line0
 * line1
 * line3
 * line4
 * line5
 *
 * result of the union:
 * original edited general
 * file1: file2: result:(lines)
 * line1 line1  SAME line1
 * line2        REMOVED line2
 * line3 line3  SAME line3
 * line4        REMOVED line4
 * line5 line5  SAME line5
 * line0        ADDED line0
 * line1 line1  SAME line1
 * line2        REMOVED line2
 * line3 line3  SAME line3
 * line4        ADDED line4
 * line5 line5  SAME line5
 * line0        REMOVED line0
 */
public class StringComparison {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        ReadFile readFile1 = new ReadFile(file1);
        Thread thread1 = new Thread(readFile1);
        ReadFile readFile2 = new ReadFile(file2);
        Thread thread2 = new Thread(readFile2);
        thread1.start();
        thread2.start();
        Thread.sleep(500);
        thread1.join();
        thread2.join();

        List<String> file1Strings = readFile1.getFileStrings();
        List<String> file2Strings = readFile2.getFileStrings();

        while (file1Strings.size() > 0 && file2Strings.size() > 0) {
            if (file1Strings.get(0).equals(file2Strings.get(0))) {
                lines.add(new LineItem(Type.SAME, file1Strings.get(0)));
                file1Strings.remove(0);
                file2Strings.remove(0);
            } else if (file1Strings.get(0).equals(file2Strings.get(1))) {
                lines.add(new LineItem(Type.ADDED, file2Strings.get(0)));
                file2Strings.remove(0);
            } else if (file1Strings.get(1).equals(file2Strings.get(0))) {
                lines.add(new LineItem(Type.REMOVED, file1Strings.get(0)));
                file1Strings.remove(0);
            }
        }
        while (file1Strings.size() > 0) {
            lines.add(new LineItem(Type.REMOVED, file1Strings.get(0)));
            file1Strings.remove(0);
        }
        while (file2Strings.size() > 0) {
            lines.add(new LineItem(Type.ADDED, file2Strings.get(0)));
            file2Strings.remove(0);
        }
    }

    public static class ReadFile implements Runnable {
        private final List<String> fileStrings = new ArrayList<>();
        private final String fileName;

        public ReadFile(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                while (reader.ready()) {
                    fileStrings.add(reader.readLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public List<String> getFileStrings() {
            return fileStrings;
        }
    }

    public enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
