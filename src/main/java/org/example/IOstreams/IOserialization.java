package org.example.IOstreams;

import java.io.*;

/**
 * Overriding in-stream serialization
 * Solution serialization/deserialization does not work.
 * Correct errors without changing method and class signatures.
 * The main method is not involved in testing.
 *
 * Write the verification code yourself in the main method:
 * 1) create an instance of the Solution class
 * 2) write data to it - writeObject
 * 3) serialize the Solution class - writeObject(ObjectOutputStream out)
 * 4) deserialize, we get a new object
 * 5) write data to a new object - writeObject
 * 6) check that the file contains the data from step 2 and step 5
 */
public class IOserialization implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String filename;

    public IOserialization(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.filename = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(filename, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) {
        try {
            IOserialization solution = new IOserialization("data.txt");
            String testString1 = "тест";
            solution.writeObject(testString1);

            solution.writeObject(new ObjectOutputStream(solution.stream));
            solution.readObject(new ObjectInputStream(new FileInputStream(solution.filename)));

            String testString2 = "тест2";
            solution.writeObject(testString2);

            solution.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
