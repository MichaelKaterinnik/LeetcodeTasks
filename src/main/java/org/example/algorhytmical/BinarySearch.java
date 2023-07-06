package org.example.algorhytmical;

public class BinarySearch {

    // типичный (?) бинарный поиск.
    // ищем корень квадратный без Math класса; подходящее число должно быть "серединкой", от которой
    // отталкиваемся и продолжаем цикл до результата

    public int mySqrt(int x) {
        if (x == 0) return 0;

        int first = 1;
        int last = x;

        while (first <= last) {
            int mid = first + (last - first) / 2;
            if (mid  == x / mid) return mid;
            else if (mid > x / mid) last = mid - 1;
            else first = mid + 1;
        }

        return last;
    }
}
