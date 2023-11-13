package Aulas;

import java.util.Scanner;

public class input_scanner_while {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um valor decimal valido");
        while (!scanner.hasNextDouble()) {
            System.out.println("entrada invalida .. ");
            System.out.print("Digite um valor decimal valido");
            scanner.next();
        }
        System.out.println("saiu do while");
        double valor = scanner.nextDouble();
        System.out.println("passou pelo scanner");
    }
}
