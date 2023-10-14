package org.example.patterns;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *You need to adapt Scanner to PersonScanner.
 * The adapter class is PersonScannerAdapter.
 * In the adapter class, create a private final field Scanner fileScanner. The field is initialized in the
 * constructor with one argument of type Scanner.
 *
 * The data in the file is stored in the following form:
 * Ivanov Ivan Ivanovich 12 31 1950
 * Petrov Petr Petrovich 12 31 1957
 *
 * The file stores a large number of people, the data of one person is in one line. The read() method should only
 * read one person's data.
 */
public class AdapterExample {
    public static void main(String[] args) {

    }

    public static class Person {
        private String firstName;
        private String middleName;
        private String lastName;
        private Date birthDate;

        public Person(String firstName, String middleName, String lastName, Date birthDate) {
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s %s", lastName, firstName, middleName, birthDate.toString());
        }
    }

    public interface PersonScanner {
        Person read() throws IOException;

        void close() throws IOException;
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;
        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String data = fileScanner.nextLine();
            String[] personData = data.split(" ");
            String birthdate = personData[3] + " " + personData[4] + " " + personData[5];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
            Date personBirthDate = null;
            try {
                personBirthDate = dateFormat.parse(birthdate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return new Person(personData[1], personData[2], personData[0], personBirthDate);
        }
        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
