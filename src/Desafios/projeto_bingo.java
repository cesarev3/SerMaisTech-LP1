package Desafios;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;

public class projeto_bingo {
    public static void main(String[] args) {

        int[] testao = {1,2,3,4,5,8,7,8,9,10};

        int[] vitima = {4};

        System.out.println("4? " + IntStream.of(testao).anyMatch(x -> x == vitima[0]));





        Scanner entrada = new Scanner(System.in);

        imprimirTelaWelcome();
        String entradaNomes = entrada.nextLine();

        int quantidadeJogadores = contarHifens(entradaNomes) + 1;
        System.out.println("total de jogadores: " + quantidadeJogadores);

        String [] jogadores = separarJogadores(entradaNomes, quantidadeJogadores);

        int[][] cartelasGeradas = gerarCartela(quantidadeJogadores);


        System.out.println("\n----- Cartelas da Sorte -----");
        imprimirCartelas(quantidadeJogadores, jogadores, cartelasGeradas);

//        int[] listaSorteados = preencherSorteados();

        System.out.println("\n\n----- Sorteando -----");
        int numerosPorSorteio = 5;
        int[] numerosSorteados = sortearNumeros();

        for (int i = 0; i < 12; i++) {

            int indice = numerosPorSorteio * (i + 1);
            int inicio = i * 5;

            System.out.print("Rodada " + (i + 1) + " = ");

            for (int j = inicio; j < indice; j++) {
                System.out.print(numerosSorteados[j] + ",");
            }

            if ((i + 1) % 3 == 0) {
                System.out.print("   \n");
            } else {
                System.out.print("   ");
            }

        }


        entrada.close();

    }

    private static void imprimirTelaWelcome() {
        System.out.println("---------------------------------");
        System.out.println("| Welcome to 50+'s Bingo Party! |");
        System.out.println("---------------------------------");
        System.out.println("Hora de chamar seus colegas");
        System.out.println("digite o primeiro nome de todos");
        System.out.println("na mesma linha e use '-' para separar");
        System.out.println("exemplo: Ana-Carlos-Marcos-Paula");
        System.out.print("> ");
    }

    private static void imprimirCartelas(int inputTotal,
                                         String[] inputJogadores,
                                         int[][] inputCartelas) {
        for (int i = 0; i < inputTotal; i++) {
            System.out.printf("%17s = {", inputJogadores[i]);
            for (int j = 0; j < 5; j++) {
                System.out.printf("%2d, ", inputCartelas[i][j]);
            }
            System.out.printf("%2d}", inputCartelas[i][5]);
            if ((i + 1) % 3 == 0) {
                System.out.print("     \n");
            } else {
                System.out.print("     ");
            }
        }
    }
    public static int[] carregarMapaSorteio() {
        int[] mapa = new int[60];
        for (int i = 0; i < 60; i++) {
            mapa[i] = i + 1;
        }
        return mapa;
    }

    public static int[] sortearNumeros() {
        Random random = new Random();
        int sorteio;
        int[] inputSorteados = carregarMapaSorteio();
        int[] saidaSorteados = new int[60];

        for (int i = 0; i < 60; i++) {
            while (true) {
                sorteio = random.nextInt(60);
                if (inputSorteados[sorteio] != 0) {
                    saidaSorteados[i] = inputSorteados[sorteio];
                    inputSorteados[sorteio] = 0;
                    break;
                }
            }
        }
    return saidaSorteados;
    }

//    public static int[] sortearNumeros(int[] inputSorteados, int inputNumerosPorSorteio) {
//        Random random = new Random();
//        int[] saidaSorteados = inputSorteados;
//        int sorteio;
//        for (int i = 0; i < inputNumerosPorSorteio; i++) {
//            while (true) {
//                sorteio = random.nextInt(60) + 1;
//                int finalSorteio = sorteio;
//                if (IntStream.of(inputSorteados).anyMatch(x -> x != finalSorteio)) {
//                    System.out.print(sorteio + ",");
//                    break;
//                }
//            }
//            for (int j = 0; j < 60; j++) {
//                if (inputSorteados[j] == 99) {
//                    inputSorteados[j] = sorteio;
//                    Arrays.sort(inputSorteados);
//                    //System.out.println(inputSorteados[j]);
//                    break;
//                }
//            }
//        }
////        for (int i = 0; i < 60; i++) {
////            saidaSorteados[i] = inputSorteados[i];
////            //System.out.println(saidaSorteados[i]);
////        }
//        return saidaSorteados;
//    }

    public static int[][] conferirCartela(int[][] inputConferidas,
                                          int[][] inputCartelas,
                                          int[] inputSorteio,
                                          int inputTotal) {
        for (int i = 0; i < inputTotal; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (inputSorteio[j] == inputCartelas[i][j]) {
                        inputConferidas[i][j] = 1;
                    }
                }
            }
        }
        return inputConferidas;
    }

    public static int[][] preencherCartelaConferencia(int[][] inputConferidas,
                                                      int inputTotal) {
        for (int i = 0; i < inputTotal; i++) {
            for (int j = 0; j < 6; j++) {
                inputConferidas[i][j] = 0;
            }
        }
        return inputConferidas;
    }
    public static int[][] gerarCartela(int inputTotal) {
        int[][] cartelas = new int[inputTotal][6];
        Random random = new Random();
        for (int i = 0; i < inputTotal; i++) {
            for (int j = 0; j < 6; j++) {
                if (j == 1) {
                    int sorteio = random.nextInt(60) + 1;
                    cartelas[i][j] = sorteio;
                } else {
                    while (true) {
                        int sorteio = random.nextInt(60) + 1;
                        if (sorteio != cartelas[i][0]
                                && sorteio != cartelas[i][1]
                                && sorteio != cartelas[i][2]
                                && sorteio != cartelas[i][3]
                                && sorteio != cartelas[i][4]) {
                            cartelas[i][j] = sorteio;
                            break;
                        }
                    }
                }
            }
        Arrays.sort(cartelas[i]);
        }
        return cartelas;
    }

    public static String[] separarJogadores(String inputLista, int inputTotal) {
        String[] jogadores = inputLista.split("-");
        for (int i = 0; i < inputTotal; i++) {
            jogadores[i].trim();
        }
        Arrays.sort(jogadores);
        return jogadores;
    }

    public static int contarHifens(String inputLista){
        int contador=0;
        for (int i=0;i<inputLista.length();i++){
            if(inputLista.charAt(i)=='-'){
                contador++;
            }
        }
        return contador;
    }
}