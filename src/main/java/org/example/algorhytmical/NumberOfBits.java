package org.example.algorhytmical;

public class NumberOfBits {

    public int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') counter++;
        }
        return counter;
    }
}
