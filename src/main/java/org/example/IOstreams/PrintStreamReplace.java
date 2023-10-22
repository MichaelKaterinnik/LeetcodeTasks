package org.example.IOstreams;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * In the main method, replace the System.out object with the reader wrapper you wrote.
 * Your reader-wrapper should display contextual advertising to the console after every second println.
 * Call the ready-made printSomething() method, use testString.
 * Return the System.out variable to the original stream.
 *
 * Advertising text: "JavaRush - Java courses online"
 *
 * Example output:
 * first
 * second
 * JavaRush - Java courses online
 * third
 * fourth
 * JavaRush - Java courses online
 * fifth
 */
public class PrintStreamReplace {
    public static TestString testString = new TestString();
    public static void main(String[] args) {
        PrintStream ps = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(outputStream);
        System.setOut(newStream);
        testString.printSomething();

        String data = outputStream.toString();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '\n') {
                counter++;
            }
            sb.append(data.charAt(i));
            if (counter == 2) {
                sb.append("JavaRush - курсы Java онлайн").append(System.lineSeparator());
                counter = 0;
            }
        }

        /* ALTERNATE
        String[] byteArray = outputStream.toString().split("\\n");
        for (int i = 0; i < byteArray.length; i++) {
            System.out.println(byteArray[i]);
            if (i % 2 != 0)
                System.out.println("JavaRush - курсы Java онлайн");
        }
        */

        System.setOut(ps);
        System.out.println(sb);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
