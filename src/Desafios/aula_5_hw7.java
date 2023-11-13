package Desafios;

public class aula_5_hw7 {
    public static void main(String[] args) {
        double input[] = {1, 72544, 1820, 124};
        double resultado = salarioComComissao(input);
        System.out.println(resultado);
    }

    public static double salarioComComissao( double[] input) {
        double valorFixo = input[0] * input[3];
        double valorVariavel = (input[1] * 5 ) / 100;
        double salarioMes = input[2] + valorFixo + valorVariavel;
        return salarioMes;
    }
}
