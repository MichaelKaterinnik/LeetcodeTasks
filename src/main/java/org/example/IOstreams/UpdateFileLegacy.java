package org.example.IOstreams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that reads the path to the file for CRUD operations from the console and, when launched,
 * depending on the flag passed in the parameters, updates the data of the product with the given id or physically
 * deletes the product with the given id (deletes from the file all data that relates to the passed id ).
 * -u id productName price quantity
 * -d id
 *
 * Parameter values:
 * -u - flag that means updating product data with a given id
 * -d - flag that means physical deletion of a product with a given id (all data that relates to the passed id is
 * deleted from the file)
 * id - product id, 8 characters
 * productName - product name, 30 characters
 * price - price, 8 characters
 * quantity - quantity, 4 characters
 *
 * The file stores data in the following sequence (without separating spaces):
 * id productName price quantity
 *
 * The data is padded with spaces to its length.
 *
 * To read and write a file you need to use FileReader and FileWriter respectively.
 */
public class UpdateFileLegacy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        if (args.length == 0) return;

        if (args[0].equals("-u")) {
            List<String> fileStrings = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                while (br.ready()) fileStrings.add(br.readLine());
            }

            int idToUpdate = Integer.parseInt(args[1]);
            for (int i = 0; i < fileStrings.size(); i++) {
                if (Integer.parseInt(fileStrings.get(i).substring(0, 8).trim()) == idToUpdate) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(fileStrings.get(i), 0, 8);
                    sb.append(args[2]);
                    if (args[2].length() < 30) {
                        int spaceNeeded = 30 - args[2].length();
                        for (int j = 1; j <= spaceNeeded; j++) sb.append(' ');
                    }
                    sb.append(args[3]);
                    if (args[3].length() < 8) {
                        int spaceNeeded = 8 - args[3].length();
                        for (int j = 1; j <= spaceNeeded; j++) sb.append(' ');
                    }
                    sb.append(args[4]);
                    if (args[4].length() < 4) {
                        int spaceNeeded = 4 - args[4].length();
                        for (int j = 1; j <= spaceNeeded; j++) sb.append(' ');
                    }
                    fileStrings.set(i, sb.toString());
                    break;
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
                for (String fileString : fileStrings) {
                    bw.write(fileString + System.lineSeparator());
                }
            }
        }

        if (args[0].equals("-d")) {
            List<String> fileStrings = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                while (br.ready()) fileStrings.add(br.readLine());
            }
            int idToDelete = Integer.parseInt(args[1]);
            for (String s : fileStrings) {
                if (Integer.parseInt(s.substring(0, 8).trim()) == idToDelete) {
                    fileStrings.remove(s);
                    break;
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
                for (String fileString : fileStrings) {
                    bw.write(fileString + System.lineSeparator());
                }
            }
        }

    }
}
