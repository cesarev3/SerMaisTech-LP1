package TryCatch;

import Aulas.tryCatch;

public class AulaFinal {
    public static void main(String[] args) {
        try {
            double numero = -9;
            double resultado = calcularRaizQuadrada(numero);
            System.out.println("Raiz quadrada de " + numero + " é: " + resultado);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    //    private static double calcularRaizQuadrada(double numero) {
    private static double calcularRaizQuadrada(double numero) throws NumberFormatException {
        if (numero < 0) {
//            throw new RuntimeException("Raiz Negativa. Valor " + numero + "!");
            throw new NumeroNegativo2Exception();
        }
        return Math.sqrt(numero);
    }

}

