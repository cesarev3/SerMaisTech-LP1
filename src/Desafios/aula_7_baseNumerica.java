package Desafios;

import java.util.Scanner;

public class BaseNumerica {
    public static void main(String[] args) {
        Scanner readScanner = new Scanner(System.in);
        System.out.println("Conversor Binário");

        System.out.println("Qual inteiro quer converter");
        System.out.print("> ");
        int origin = readScanner.nextInt();

        // Etapa 1
        // Decimal =>  Binário, Octal, Hexadecimal
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 10, 2, conversorGenererico(origin, 10, 2));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 10, 8, conversorGenererico(origin, 10, 8));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 10, 16, conversorGenererico(origin, 10, 16));

        // Etapa 2
        // Binário => Decimal, Octal, Hexadecimal
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 2, 10, conversorGenererico(origin, 2, 10));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 2, 8, conversorGenererico(origin, 2, 8));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 2, 16, conversorGenererico(origin, 2, 16));

        // Etapa 3
        // Octal => Decimal, Binário, Hexadecimal
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 8, 10, conversorGenererico(origin, 8, 10));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 8, 2, conversorGenererico(origin, 8, 2));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 8, 16, conversorGenererico(origin, 8, 16));

        // Etapa
        //Hexadecimal => Decimal, Binário, Octal
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 16, 10, conversorGenererico(origin, 16, 10));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 16, 2, conversorGenererico(origin, 16, 2));
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 16, 8, conversorGenererico(origin, 16, 8));


        // , Quinária, Duodecimal, Vigesimal, Sexagesimal
        // Decimal, Binário, Octal, Hexadecimal, Quinária, Duodecimal, Vigesimal, Sexagesimal




        // De Decimal para Binário:




        // De Decimal para Quinario:
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 10, 5, conversorGenererico(origin, 10, 5));
        // De Quinario para Decimal:
        System.out.printf("Valor: %s, da BASE (%s) para BASE (%s) => %s \n", origin, 5, 10, conversorGenererico(origin, 5, 10));




    }


    /**
     * Conversor genérico, utilizando os recursos da classe Integer!
     * @param valor
     * @param daBase
     * @param paraBase
     * @return
     */
    private static String conversorGenererico(int valor, int daBase, int paraBase) {
        // https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#parseInt-java.lang.String-int-
        // https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#toString-int-int-

        String st = Integer.t
        String stringDaBase = Integer.toString(valor, daBase);
        String convertido =  Integer.toString(Integer.parseInt(stringDaBase, daBase), paraBase);


        return convertido;


    }
}
