package org.example.stringTasks;

public class Palindrome {

    public static boolean isPalindrome (String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) { // Используем Character.isLetterOrDigit()
                sb.append(Character.toLowerCase(c)); // Приводим к нижнему регистру
            }
        }

        int length = sb.length();

        for (int i = 0; i < length / 2; i++) {
            if (sb.charAt(i) != sb.charAt(length - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
