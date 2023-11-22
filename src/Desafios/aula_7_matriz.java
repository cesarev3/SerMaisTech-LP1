package Desafios;

import java.util.Random;
import java.util.Scanner;

public class aula_7_matriz {
    public static void main(String[] args) {
        System.out.println("Bem-vindo a aplicativo de multiplicação de matrizes!");
        int arraySize = getNumber();

        System.out.println("\nmatriz a");
        int[][] arrayA = fillArray(arraySize);
        printArray(arrayA);

        System.out.println("\nmatriz b");
        int[][] arrayB = fillArray(arraySize);
        printArray(arrayB);

        System.out.printf("\nmultiplicação das matrizes %d x %d \n", arraySize, arraySize);
        printArrayMultiplication(arrayA, arrayB);

        System.out.printf("\nmultiplicação das matrizes verificando Zeros %d x %d \n", arraySize, arraySize);
        printArrayMultiplicationWoZeros(arrayA, arrayB);
    }

    public static int getNumber() {
        Scanner readScanner = new Scanner(System.in);

        System.out.println("Qual o tamanho da matriz quadrada?");
        System.out.print("> ");
        int number = readScanner.nextInt();

        readScanner.close();

        return number;
    }

    public static int[][] fillArray(int arraySize) {

        Random random = new Random();

        int number; // sort number from 0 to 9
        int sign; // change signal if divided by 3 gets iqual zero
        int[][] filledArray = new int[arraySize][arraySize];

        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                number = random.nextInt(10);
                sign = random.nextInt(5);
                if (sign % 3 == 0) {
                    sign = -1;
                }else {
                    sign = 1;
                }
                filledArray[i][j] = number * sign;
            }
        }
        return filledArray;
    }

    public static void printArray(int[][] inputArray) {

        int arraySize = inputArray.length;

        for (int i = 0; i < arraySize; i++) {
            System.out.print("[");
            for (int j = 0; j < arraySize; j++) {
                if (j == arraySize - 1) {
                    System.out.printf("%2d]\n", inputArray[i][j]);
                }
                else {
                    System.out.printf("%2d,", inputArray[i][j]);
                }
            }
        }
    }

    public static void printArrayResult(int[][] inputArray, int inputMultiplication) {

        int arraySize = inputArray.length;

        for (int i = 0; i < arraySize; i++) {
            System.out.print("[");
            for (int j = 0; j < arraySize; j++) {
                if (j == arraySize - 1) {
                    System.out.printf("%5d]\n", inputArray[i][j]);
                }
                else {
                    System.out.printf("%5d,", inputArray[i][j]);
                }
            }
        }
        System.out.printf("\nTotal de multiplicações: %d\n", inputMultiplication);
    }

    public static void printArrayMultiplication(int[][] inputA, int[][] inputB) {

        int arraySize = inputA.length;
        int multiplicationBy = 0;
        int[][] arrayC = new int[arraySize][arraySize];

        for (int i = 0; i < arraySize; i++) { // i-linha
            for (int j = 0; j < arraySize; j++) { // j-coluna
                for (int k = 0; k < arraySize; k++) { // k-fator
                    arrayC[i][j] += inputA[i][k] * inputB[k][j];
                    multiplicationBy ++;
                }
            }
        }
        printArrayResult(arrayC, multiplicationBy);
    }

    public static void printArrayMultiplicationWoZeros(int[][] inputA, int[][] inputB) {

        int arraySize = inputA.length;
        int multiplicationBy = 0;
        int[][] arrayC = new int[arraySize][arraySize];

        for (int i = 0; i < arraySize; i++) { // i-linha
            for (int j = 0; j < arraySize; j++) { // j-coluna
                for (int k = 0; k < arraySize; k++) { // k-fator
                    if (inputA[i][k] == 0 || inputB[k][j] == 0) {
                        arrayC[i][j] += 0;
                    }else {
                        arrayC[i][j] += inputA[i][k] * inputB[k][j];
                        multiplicationBy ++;
                    }
                }
            }
        }
        printArrayResult(arrayC, multiplicationBy);
    }

}
