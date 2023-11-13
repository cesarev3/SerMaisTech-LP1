package Desafios;

public class aula_5_hw4 {
    public static void main(String[] args) {
        int input[] = {10, 8, 13};
        int resultado = idadeEmDias(input);
        System.out.println(resultado);
    }

    public static int idadeEmDias( int[] input) {
        int anos = input[0] * 365;
        int meses = input[1] * 30;
        int dias = anos + meses + input[2];
        return dias;
    }
}
