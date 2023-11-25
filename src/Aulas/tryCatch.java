package Aulas;

public class tryCatch {
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
            throw new NumeroNegativoException();
        }
        return Math.sqrt(numero);
    }


    static class NumeroNegativoException extends RuntimeException {
        public NumeroNegativoException(){
            super("Raiz negativa. Entre com valores não negativos!");
        }

    }
}

//    public class Teste {
//
//        public static void main(String[] args) {
//            double numero = -9;
//            try {
//                double resultado = calcularRaizQuadrada(numero);
//                System.out.println("Raiz quadrada de " + numero + " é: " + resultado);
//            } catch (NumeroNegativoException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        private static double calcularRaizQuadrada(double numero) {
//            if (numero < 0) {
//                throw new NumeroNegativoException();
//            }
//            return Math.sqrt(numero);

