package Desafios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class aula_3_baskara {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        try {
            System.out.println("Calculo das raízes da Equação de 2º Grau");
            System.out.println("Digite o valor de A em ax2 + bx + c");
            System.out.print("> ");
            double a = entrada.nextDouble();

            if (a == 0) {
                throw new RuntimeException("Não será uma equação do" +
                        " segundo grau");
            }

            System.out.println("Digite o valor de B em ax2 + bx + c");
            System.out.print("> ");
            double b = entrada.nextDouble();

            System.out.println("Digite o valor de C em ax2 + bx + c");
            System.out.print("> ");
            double c = entrada.nextDouble();

            entrada.close();

            double delta = Math.pow(b,2) - (4 * a * c);

            if (delta < 0) {
                throw new RuntimeException("Delta negativo: " + delta + "\n" +
                        "A equação não possui raízes reais");
            }

            double x1 = (- b + (Math.pow(delta, 0.5))) / (2 * a);
            double x2 = (- b - (Math.pow(delta, 0.5))) / (2 * a);

            if (delta == 0) { // x2 -6x +9 = 0
                System.out.println("A equação possui apenas uma raíz real");
                System.out.printf("x1: %5.1f\n", x1);
            }
            else {
                System.out.println("A equação possui duas raízes reais");
                System.out.printf("x1: %5.1f\n", x1);
                System.out.printf("x2: %5.1f", x2);
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Utilize apenas números");

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

        }
    }
}
