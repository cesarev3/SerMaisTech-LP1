package Aulas;

import java.util.Scanner;

public class input_scanner_while {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um valor decimal valido");
        System.out.print("> ");
        validarScanner(scanner);
        double valor = scanner.nextDouble();
    }

    private static void validarScanner(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Digite novamente");
            System.out.print("> ");
            scanner.next();
        }
    }
}
