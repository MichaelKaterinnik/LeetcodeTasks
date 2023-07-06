package org.example.arraysTasks;

public class MatrixDiagonalSum {

    // сумма элементов , находящихся в двумерном массиве "по-диагонали"

    public int diagonalSum(int[][] mat) {
        int result = 0;

        if (mat.length == 1) return mat[0][0];

        if (mat.length % 2 == 0) {
            for (int i = 0; i < mat.length; i++) {
                result += mat[i][i];
                result += mat[i][mat.length - 1 - i];
            }
        } else {
            int median = mat.length / 2 + 1;
            for (int i = 0; i < mat.length; i++) {
                result += mat[i][i];
                if (i == median) continue;
                result += mat[i][mat.length - 1 - i];
            }
        }

        return result;
    }
}
