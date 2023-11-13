package Aulas;

import java.util.Scanner;

public class basic_com_input {
    public static void main(String[] args) {
        Scanner leitorScanner = new Scanner(System.in);

        System.out.println("Qual o seu nome? ");
        System.out.print("> ");
        String leitorNome = leitorScanner.nextLine();

        System.out.println("Hello " + leitorNome + "!");

        leitorScanner.close();
    }
}
