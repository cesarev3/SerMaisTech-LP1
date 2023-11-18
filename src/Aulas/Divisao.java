package Aulas;

import java.util.Scanner;

public class Divisao {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        double dividendo = obterNumero("dividendo");
        double divisor = obterNumero("divisor");
        System.out.printf("%.2f \n", dividir(dividendo, divisor));

        entrada.close();
}
    public static double dividir(double dividendo, double divisor) {
        return dividendo / divisor;
    }
    public static double obterNumero(String informaNumero) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(("Digite o " + informaNumero));
        System.out.print("> ");
        double numero = entrada.nextDouble();
        return numero;
    }

}
