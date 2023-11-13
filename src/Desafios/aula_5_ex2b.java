package Desafios;

public class aula_5_ex2b {
    public static void main(String[] args) {

        System.out.println("Pressione Enter para contar uma gota de medicação:");

        int contagemGotas = 0;

        while (contagemGotas < 20) {
            contagemGotas++;
            System.out.println("Gota " + contagemGotas + " contada.");
        }

        System.out.println("Todas as 20 gotas foram contadas. Medicamento completo!");
    }
}
