package Desafios;

//  Crie uma função que receberá um array com três valores que correspondem
//  a possíveis lados de um triângulo. Na função, validar se os dados
//  fornecidos formam um triângulo. Caso afirmativo, retornar true; do contrario,
//  retornar false.
//
//  OBS: para formar um triângulo, o valor de cada lado deve ser menor que a soma
//  dos outros 2 lados.

public class aula_5_hw2 {
    public static void main(String[] args) {
        int input[] = {2, 4, 3};
        boolean resultado = eUmTriangulo(input);
        System.out.println(resultado);
    }

    public static boolean eUmTriangulo(int[] input) {
        if (input[0] < input[1] + input[2] && input[1] < input[0] + input[2] && input[2] < input[1] + input[0]) {
            return true;
        }
        return false;
    }
}
