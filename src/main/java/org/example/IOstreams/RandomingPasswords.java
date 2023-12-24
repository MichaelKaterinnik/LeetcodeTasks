package org.example.IOstreams;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * Password generator
 * Implement the logic of the getPassword method, which should return a ByteArrayOutputStream containing the password
 * bytes.
 *
 * Password requirements:
 * 1) 8 characters.
 * 2) only numbers and Latin letters of different case.
 * 3) numbers and letters of different case must be present.
 * All generated passwords must be unique.
 *
 * Example of a correct password:
 * wMh7smNu
 */
public class RandomingPasswords {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++) {
            int number;
            char result = 0;
            if (i == 1) {
                number = random.nextInt(10);
                result = (char) (number + '0');
            } else if (i == 0) {
                number = random.nextInt(26);
                result = (char) ('A' + (number % 26));
            } else if (i == 5) {
                do {
                    number = random.nextInt(36);
                } while (number > 25);
                result = (char) ('a' + (number % 26));
            } else {
                number = random.nextInt(36);
                if (number < 10) result = (char) (number + '0');
                else {
                    char base = number < 26 ? 'A' : 'a';
                    result = (char) (base + (number % 26));
                }

            }
            bos.write(result);
        }
        return bos;
    }
}
