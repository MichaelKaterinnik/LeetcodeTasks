package org.example.arraysTasks;

public class TwoSum {

    /**
     Данный метод returnSumOfTwo принимает на вход массив чисел nums и целевое значение target. Он ищет два
     числа в массиве nums, сумма которых равна target, и возвращает индексы этих чисел в новом массиве result.
     */

    public static int[] returnSumOfTwo(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }
}
