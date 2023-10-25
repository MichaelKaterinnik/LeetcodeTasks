package org.example.IOstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The main method takes the file name as its first parameter.
 * In this file, each line looks like this:
 * name day month year
 * where [name] can consist of several words separated by spaces and is of type String.
 * [day] - int, [month] - int, [year] - int
 * data is separated by spaces.
 *
 * Fill the PEOPLE list using data from the file.
 * Close streams.
 *
 * Example input file:
 * Ivanov Ivan Ivanovich 12 31 1987
 * Vasya 15 5 2013
 */
public class ParsingStringsWithDate {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String data = reader.readLine();
                int dataIndex = 0;
                for (int i = 0; i < data.length(); i++) {
                    if (Character.isDigit(data.charAt(i))) {
                        dataIndex = i;
                        break;
                    }
                }
                String name = data.substring(0, dataIndex).trim();
                String date = data.substring(dataIndex);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d M yyyy");
                PEOPLE.add(new Person(name, simpleDateFormat.parse(date)));
            }
        }
    }

    private static class Person {
        private String name;
        private Date birthDate;

        public Person(String name, Date birthDate) {
            this.name = name;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public Date getBirthDate() {
            return birthDate;
        }
    }
}
