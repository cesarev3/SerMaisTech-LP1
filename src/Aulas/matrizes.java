package Aulas;

import java.util.Arrays;

public class matrizes {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2}, {3, 4}};
        int[][] b = new int[][]{{-1, 3}, {4, 2}};
        int[][] c = new int[2][2];

        System.out.println(b.length); // linhas
        System.out.println(b[0].length); // qtde cols linha 0

        for (int i = 0; i < a.length; i++) { // i-linha
            for (int j = 0; j < b.length; j++) { // j-coluna
                for (int k = 0; k < b.length; k++) { // k-fator
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
            System.out.println(Arrays.toString( c[i] ));
        }

    }
}
