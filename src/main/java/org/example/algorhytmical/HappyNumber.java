package org.example.algorhytmical;

public class HappyNumber {

    /**
     "Счастливое число" - это число, в котором сумма квадратов его цифр равна единице.

     то есть берем цифры числа, ищем их квадрат, плюсем, от полученного числа опять ищем квадрат - и так,
     пока квадраты цифр какого-либо числа в нашей последовательности не будут равны 1
     */


    public boolean isHappy(int n) {
        boolean isHappy = false;
        int sum = n;
        int count = 0;

        do {
            String numberString = Integer.toString(sum);
            sum = 0;
            for (int i = 0; i < numberString.length(); i++) {
                int digit = Character.getNumericValue(numberString.charAt(i));
                sum = sum + (digit * digit);
            }
            count++;
            if (count > 1000) { // Прерываем цикл после 1000 итераций
                return false;
            }
        } while (sum != 1);

        return true;
    }
}
