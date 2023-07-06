package org.example.algorhytmical;

public class Fibonacci {

    public int climbStairs(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть положительным числом");
        }

        if (n <= 2) {
            return n;
        }

        int prev1 = 1;
        int prev2 = 2;
        int current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }

        return current;
    }
}
