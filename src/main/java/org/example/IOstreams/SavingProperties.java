package org.example.IOstreams;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The main() method reads the file path from the console and fills runtimeStorage with data from the file.
 * You need to implement the logic for writing to a file for the runtimeStorage card in the save() method, and the
 * logic for reading from the file for runtimeStorage in the load() method.
 * The file must be in .properties format. Ignore comments in the file.
 */
public class SavingProperties {
    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        //напишите тут ваш код
        Properties saveProperties = new Properties();
        saveProperties.putAll(runtimeStorage);
        saveProperties.store(outputStream, "");
    }

    public static void load(InputStream inputStream) throws IOException {
        //напишите тут ваш код
        Properties loadedProperties = new Properties();
        loadedProperties.load(inputStream);
        for (Map.Entry<Object, Object> properties : loadedProperties.entrySet()) {
            runtimeStorage.put(properties.getKey().toString() ,properties.getValue().toString());
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fis = new FileInputStream(reader.readLine())) {
            load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }
}
