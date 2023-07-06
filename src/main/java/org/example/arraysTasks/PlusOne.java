package org.example.arraysTasks;

import java.util.Arrays;

public class PlusOne {

    /**
     Данный метод plusOne принимает массив digits, представляющий целое число, и возвращает новый массив, в котором
     к числу, представленному в массиве digits, добавлена единица.
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{1};
        }

        int n = digits.length;
        int[] result = Arrays.copyOf(digits, n);

        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] += carry; // добавляем "перенос" к текущей цифре

            if (result[i] <= 9) {
                // если текущая цифра не превысила 9, прекращаем обработку
                carry = 0;
                break;
            }

            // если текущая цифра превысила 9, устанавливаем ее в 0 и продолжаем обработку
            result[i] = 0;
        }

        // если "перенос" остался после обработки всех цифр, значит все цифры были 9
        if (carry == 1) {
            int[] newResult = new int[n + 1];
            newResult[0] = 1;
            return newResult;
        }

        return result;
    }
}
