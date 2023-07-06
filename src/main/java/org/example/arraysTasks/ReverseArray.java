package org.example.arraysTasks;

public class ReverseArray {

    public static void reverseArray(int[] array) {
        int length = array.length;
        int halfLength = length / 2;

        for (int i = 0; i < halfLength; i++) {
            int temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }
}
