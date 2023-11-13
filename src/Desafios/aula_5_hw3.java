package Desafios;

public class aula_5_hw3 {
    public static void main(String[] args) {
        double input[] = {20, 80, 60, 40};
        String resultado = geraRecibo(input);
        System.out.println(resultado);
    }

    public static String geraRecibo(double[] input) {
        double valorTotal = 0;

        for (double v : input) {
            valorTotal += v;
        }
//
        double desconto = input[0] * 0.5;
        double valorAPagar = valorTotal - desconto;

        String saida = "Valor total: " + valorTotal + " | Valor de descontos: " + desconto + " | Valor a pagar: " + valorAPagar;
        return saida;
    }
}
