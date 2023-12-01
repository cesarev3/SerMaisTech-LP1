package Aulas;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bingo3 {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final int QTDE_NUM_SORTEADOS_POR_VEZ =2;
    static final int QTDE_NUMEROS = 60;

    enum modo {MANUAL, AUTO}

    public static void main(String[] args) {
        // montando estrutura na mao de dados pronta
        String[] jogadores = {"eva", "john", "rui"};
        int[][] cartelas = {{1,2,3,4,5},{5,6,7,4,9},{9,8,7,4,5}};
        int[][] marcador = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        modo game = modo.AUTO;
        int[] sorteados = new int[QTDE_NUMEROS];

        // 5 numeros
        // 1,2,3,4,5
        // 2,5,3,4,1

        // gerando numeros misturados para sortear em ordem
        int[] numerosEmbaralhados = new int[QTDE_NUMEROS];
        gerarEembaralharArray(numerosEmbaralhados);

        // variavel generica para escolha do metodo a receber
        int[] globo = numerosEmbaralhados; // contrario chama gerarNumeros

        // mecanica game
        boolean bingo = false;
        System.out.println( "comecando bingo" );
        while( !bingo ) {

            // sorteando numeros
            switch (game) {
                case MANUAL -> {
                    String input = scanner.nextLine();
                    String[] strings = input.split(" ");
                    sorteados = converterNumeros(strings);
                    break;
                }
                default -> {
                    sorteados = sorteiaAuto(globo);
                }
            }

            // verificando cartela jogadores
            for (int i = 0; i < jogadores.length; i++) {
                int[] cartela = cartelas[i];

            }
        }
        System.out.println( "bingo acabou" );
    }

    // algoritmo fisher-yates
    private static void gerarEembaralharArray(int[] array) {
        for (int i = 0; i < 60; i++) {
            array[i] = i + 1;
        }
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = array[i]; // swap - troca
            array[i] = array[j];
            array[j] = temp;
        }
    }

    static int[] converterNumeros(String[] strings) {
        int[] numeros = new int[3];
        System.out.println( Arrays.toString( strings ) );
        for (int k = 0; k < strings.length; k++) { // preenche tipo int
            numeros[k] = Integer.parseInt(strings[k]);
        }
        return numeros;
    }

    static int[] sorteiaAutoX() {
        int[] cartela = new int[QTDE_NUM_SORTEADOS_POR_VEZ];
        for (int i = 0; i < cartela.length; i++) {
            cartela[i] = random.nextInt(10);
        }
        return cartela;
    }

    // todolist .. alem de redimensionar globo, ele retorna os sorteados
    static int[] sorteiaAuto(int[] globo) {
        int[] sorteados = new int[QTDE_NUM_SORTEADOS_POR_VEZ];
        int[] indicesSorteados = new int[QTDE_NUM_SORTEADOS_POR_VEZ];

        for (int i = 0; i < sorteados.length; i++) {
            boolean foiSorteado = false;

            while (!foiSorteado) {
                indicesSorteados[i] = (int) (Math.random() * globo.length);

                foiSorteado = true;
                for (int j = 0; j < i; j++) {
                    if (indicesSorteados[j] == indicesSorteados[i]) {
                        foiSorteado = false;
                        break;
                    }
                }
            }

            int numeroSorteado = globo[indicesSorteados[i]];
            sorteados[i] = numeroSorteado;
        }

        return sorteados;
    }

    static int[] sorteiaManual(int size) {
        int[] cartela = new int[size];
        for (int i = 0; i < cartela.length; i++) {
            cartela[i] = random.nextInt(10);
        }
        return cartela;
    }

}
