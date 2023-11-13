package Desafios;

import java.util.Locale;
import java.util.Scanner;

public class aula_4_imc {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.useLocale(Locale.US);

        System.out.println("Cálculo do seu Índice de Massa Corporal - IMC");

        double pesoAtual;
        while (true) {
            try {
                System.out.println("Qual o seu peso em kg?");
                System.out.print("> ");
                pesoAtual = Double.parseDouble(entrada.nextLine());
                validarPesoAtual(pesoAtual);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        double alturaAtual;
        while (true) {
            try {
                System.out.println("Qual a sua altura em metros?");
                System.out.print("> ");
                alturaAtual = Double.parseDouble(entrada.nextLine());
                validaralturaAtual(alturaAtual);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        entrada.close();

        double valorIMC = calcularIMC(pesoAtual, alturaAtual);
        String classificacaoIMC = classificarIMC(valorIMC);
        double pesoIdealMinimo = calcularPesoIdeal(alturaAtual, 18.5);
        double pesoIdealMaximo = calcularPesoIdeal(alturaAtual, 24.9);

        System.out.printf("\nSeu IMC: %.1f\n", valorIMC);
        System.out.printf("Classificação: %s\n", classificacaoIMC);
        System.out.printf("Peso ideal: %.1f Kg a %.1f Kg\n", pesoIdealMinimo, pesoIdealMaximo);
    }
    public static double calcularIMC(double valorPeso, double valorAltura) {
        return valorPeso / Math.pow(valorAltura,2);
    }
    public static String classificarIMC(double valorIMC) {
        String classificacao;
        if (valorIMC < 18.5) {
            classificacao = "Magreza";
        } else if (valorIMC >= 18.5 && valorIMC < 25) {
            classificacao = "Normal";
        } else if (valorIMC >= 25 && valorIMC < 30) {
            classificacao = "Sobrepeso";
        } else if (valorIMC >= 30 && valorIMC < 35) {
            classificacao = "Obesidade grau I";
        } else if (valorIMC >= 35 && valorIMC < 40) {
            classificacao = "Obesidade grau II";
        } else {
            classificacao = "Obesidade grau III";
        }
        return classificacao;
    }
    public static double calcularPesoIdeal(double valorAltura, double valorIMC) {
        return Math.pow(valorAltura,2) * valorIMC;
    }
    public static void validarPesoAtual(double pesoAtual){
        if (pesoAtual > 40 && pesoAtual <= 130) {
            System.out.println("Peso armazenado.");
        } else {
            throw new RuntimeException("Peso entre 40kg e 130kg");
        }
    }
    public static void validaralturaAtual(double alturaAtual){
        if (alturaAtual > 1.1 && alturaAtual <= 2.5) {
            System.out.println("Altura armazenada.");
        } else {
            throw new RuntimeException("Altura entre 1.1 metros e 2.50 metros");
        }
    }
}
