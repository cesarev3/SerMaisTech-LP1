package Desafios;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class projeto_bingo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        gerarWelcome();
        String listaNomes = entrada.nextLine();

        int quantidadeJogadores = contarHifens(listaNomes) + 1;
        System.out.println("total de jogadores: " + quantidadeJogadores);

        String [] jogadores = new String[quantidadeJogadores];
        jogadores = separarJogadores(listaNomes, quantidadeJogadores);

        int[][] cartelasJogos = gerarCartelas(quantidadeJogadores);
        int[][] cartelasOrdenadas = ordenarCartelas(cartelasJogos,
                quantidadeJogadores);

        System.out.println("----- Cartelas da Sorte -----");
        imprimirCartelas(quantidadeJogadores, jogadores, cartelasOrdenadas);

        System.out.println("----- Sorteando -----");
        int[] numerosSorteados = sortearNumeros();
        Arrays.sort(numerosSorteados);

        System.out.print("Rodada 1 = {");
        for (int i = 0; i < 5; i++) {
            System.out.print(numerosSorteados[i] + ", ");
        }
        System.out.println(numerosSorteados[5] + "}");



        entrada.close();

    }

    private static void gerarWelcome() {
        System.out.println("---------------------------------");
        System.out.println("| Welcome to 50+'s Bingo Party! |");
        System.out.println("---------------------------------");
        System.out.println("Hora de chamar seus colegas");
        System.out.println("digite o primeiro nome de todos");
        System.out.println("na mesma linha e use '-' para separar");
        System.out.println("exemplo: Ana-Carlos-Marcos-Paula");
        System.out.printf("> ");
    }

    private static void imprimirCartelas(int quantidadeJogadores,
                                         String[] jogadores,
                                         int[][] cartelasOrdenadas) {
        for (int i = 0; i < quantidadeJogadores; i++) {
            System.out.print(jogadores[i] + " = {");
            for (int j = 0; j < 5; j++) {
                System.out.print(cartelasOrdenadas[i][j] + ", ");
            }
            System.out.println(cartelasOrdenadas[i][5] + "}");
        }
    }

    public static int[][] ordenarCartelas(int[][] input, int total) {
        for (int i = 0; i < total; i++) {
                Arrays.sort(input[i]);
        }
        return input;
    }

    public static int[] sortearNumeros() {
        Random random = new Random();
        int[] numeros = new int[6];
        for (int i = 0; i < 6; i++) {
            if (i == 1) {
                int sorteio = random.nextInt(60) + 1;
                numeros[i] = sorteio;
            } else {
                while (true) {
                    int sorteio = random.nextInt(60) + 1;
                    if (sorteio != numeros[0]
                            && sorteio != numeros[1] && sorteio != numeros[2]
                            && sorteio != numeros[3] && sorteio != numeros[4])
                    {
                        numeros[i] = sorteio;
                        break;
                    }
                }
            }
        }
        return numeros;
    }

    public static int[][] gerarCartelas(int total) {
        int[][] cartelas = new int[total][6];
        Random random = new Random();
        for (int i = 0; i < total; i++) {
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
        }
        return cartelas;
    }

    public static String[] separarJogadores(String lista, int total) {
        String[] input = lista.split("-");
        for (int i = 0; i < total; i++) {
            input[i].trim();
        }
        return input;
    }

    public static int contarHifens(String str){
        int count=0;
        for (int i=0;i<str.length();i++){
            if(str.charAt(i)=='-'){
                count++;
            }
        }
        return count;
    }
}
