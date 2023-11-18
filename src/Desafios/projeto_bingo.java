package Desafios;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class projeto_bingo {
    public static void main(String[] args) {

        imprimirTelaWelcome();

        Scanner entrada = new Scanner(System.in);
        String entradaNomes = entrada.nextLine();

        // variaveis de controle Globais;
        int dezenaMaxima = 60;
        int dezenasPorCartela = 6;
        int dezenasPorSorteio = 5;
        int rodadaSorteio = 0;
        int quantidadeJogadores = contarHifens(entradaNomes) + 1;
        int[] controle = {dezenaMaxima, dezenasPorCartela, dezenasPorSorteio, rodadaSorteio, quantidadeJogadores};

        String[] nomeJogadores = separarJogadores(entradaNomes, controle);
        System.out.println("total de jogadores: " + quantidadeJogadores);

        System.out.println("\n------------- Cartelas da Sorte --------------");

        int[][] cartelasGeradas = gerarCartela(controle);
        imprimirCartelas(nomeJogadores, cartelasGeradas, controle);

        System.out.println("\n\n---------------- Sorteando -----------------");

        int[] numerosSorteados = sortearNumeros(controle);

        int[] numerosOrdenados = ordenarNumerosSorteados(numerosSorteados, controle);



        int rodadas = 0;
        char continua = 'S';

        while (continua != 'X') {
//                System.out.println("iniciando mais uma rodada!");
                controle[3] = rodadas;
                imprimirDezenasSorteads(numerosOrdenados, controle);
                int[][] cartelasConferidas = conferirCartelas(cartelasGeradas, numerosSorteados, controle);
                imprimirConferirCartelas4x4(nomeJogadores, cartelasGeradas, cartelasConferidas, controle);
                rodadas ++;

                System.out.println("Tecle qualquer tecla para uma nova rodada ou X para sair do jogo");
                continua = Character.toUpperCase(entrada.next().charAt(0));

        }

//        int maximoSorteios = dezenaMaxima / dezenasPorSorteio;

//        for (int i = 0; i < maximoSorteios; i++) {

//            int i = 0;
//            int indice = dezenasPorSorteio * (i + 1);
//            int inicio = i * 5;
//
//            System.out.print("Rodada " + (i + 1) + " = ");
//
//            for (int j = inicio; j < indice; j++) {
//                System.out.print(numerosOrdenados[j] + ",");
//            }
//
//            if ((i + 1) % 3 == 0) {
//                System.out.print("   \n");
//            } else {
//                System.out.print("   ");
//            }

//        }

        System.out.println("\n\n---------------- Conferindo ----------------");

        //for (int i = 0; i < maximoSorteios; i++) {
//            int[][] cartelasConferidas = conferirCartelas(cartelasGeradas, numerosSorteados, controle);
//
//            imprimirConferirCartelas4x4(nomeJogadores, cartelasGeradas, cartelasConferidas, controle);
//            for (int j = 0; j < quantidadeJogadores; j++) {
//                System.out.print(nomeJogadores[j] + " = {");
//                for (int k = 0; k < dezenasPorCartela; k++) {
//                    if (k != (dezenasPorCartela - 1)) {
//                        System.out.print(cartelasGeradas[j][k] + ", ");
//                    } else {
//                        System.out.print(cartelasGeradas[j][k] + "}\n");
//                    }
//                }
//
//                System.out.print("Conferência = {");
//                for (int l = 0; l < dezenasPorCartela; l++) {
//                    if (l != (dezenasPorCartela - 1)) {
//                        System.out.print(cartelasConferidas[j][l] + ", ");
//                    } else {
//                        System.out.print(cartelasConferidas[j][l] + "}\n");
//                    }
//                }
//
//            }




        entrada.close();

    }

    private static void imprimirTelaWelcome() {
        System.out.println("------------------------------------------------");
        System.out.println("|       Welcome to the 50+'s Bingo Party!      |");
        System.out.println("------------------------------------------------");
        System.out.println("\nHora de chamar seus colegas!");
        System.out.println("digite o primeiro nome de todos na mesma linha e");
        System.out.println("use '-' entre eles. Ex. Ana-Carlos-Marcola-Paula");
        System.out.print("> ");
    }

    private static void imprimirCartelas(String[] jogadores,
                                         int[][] cartelas,
                                         int[] controle) {

        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        for (int i = 0; i < totalJogadores; i++) {
            System.out.printf("%17s = {", jogadores[i]);
            for (int j = 0; j < dezenasPorCartela; j++) {
                if (j != (dezenasPorCartela - 1)) {
                    System.out.printf("%2d, ", cartelas[i][j]);
                } else {
                    System.out.printf("%2d}", cartelas[i][j]);
                }
            }
            if ((i + 1) % 4 == 0) {
                System.out.print("     \n");
            } else {
                System.out.print("     ");
            }
        }
    }

    private static void imprimirConferirCartelas4x4(String[] jogadores,
                                                    int[][] cartelas,
                                                    int[][] cartelasConferidas,
                                                    int[] controle) {

        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int totalJogadores = controle[4];
        int multiplosDeQuatro = totalJogadores / 4;

        for (int k = 0; k < totalJogadores; k+=4) {
            int paginas = k + 4;
            if (paginas >  totalJogadores) {
                paginas = totalJogadores;
            }
            for (int i = k; i < paginas; i++) {
                System.out.printf("%17s = {", jogadores[i]);
                for (int j = 0; j < dezenasPorCartela; j++) {
                    if (j != (dezenasPorCartela - 1)) {
                        System.out.printf("%2d, ", cartelas[i][j]);
                    } else {
                        System.out.printf("%2d}", cartelas[i][j]);
                    }
                }
                System.out.print("     ");
            }
            System.out.println(" ");
            String acertos = "Acertos";
            for (int i = k; i < paginas; i++) {
                System.out.printf("%17s = {", acertos);
                for (int j = 0; j < dezenasPorCartela; j++) {
                    if (j != (dezenasPorCartela - 1)) {
                        System.out.printf("%2d, ", cartelasConferidas[i][j]);
                    } else {
                        System.out.printf("%2d}", cartelasConferidas[i][j]);
                    }

                }
                System.out.print("     ");
            }
            System.out.println("\n ");
        }
    }

    public static int[] carregarMapaSorteio(int[] controle) {

        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int totalJogadores = controle[4];

        int[] mapa = new int[dezenaMaxima];

        for (int i = 0; i < dezenaMaxima; i++) {
            mapa[i] = i + 1;
        }
        return mapa;
    }

    public static void imprimirDezenasSorteads(int[] sorteioOrdenado, int[] controle) {
        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int totalJogadores = controle[4];
        int intervalo = rodadasDoSorteio * dezenasPorSorteio;

        System.out.printf("Rodada %d = {", (rodadasDoSorteio + 1));
        for (int i = (0 + intervalo); i < (dezenasPorSorteio + intervalo); i++) {
            System.out.printf("%2d,", sorteioOrdenado[i]);
        }
        System.out.println("}\n");
    }

    public static int[] ordenarNumerosSorteados(int[] sorteio, int[] controle) {
        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int totalJogadores = controle[4];
        int[] cacheArray = new int[dezenasPorSorteio];
        int[] saida = new int[dezenaMaxima];

        for (int i = 0; i < dezenaMaxima; i += dezenasPorSorteio) {
            for (int j = 0; j < dezenasPorSorteio; j++) {
                cacheArray[j] = sorteio[j + i];
            }
            Arrays.sort(cacheArray);

            for (int k= 0; k < dezenasPorSorteio; k++) {
                saida[k + i] = cacheArray[k];
            }
        }
        return saida;
    }

    public static int[] sortearNumeros(int[] controle) {

        Random random = new Random();
        int dezenaMaxima = controle[0];
        int sorteio;
        int[] inputSorteados = carregarMapaSorteio(controle);
        int[] saidaSorteados = new int[dezenaMaxima];

        for (int i = 0; i < dezenaMaxima; i++) {
            while (true) {
                sorteio = random.nextInt(dezenaMaxima);
                if (inputSorteados[sorteio] != 0) {
                    saidaSorteados[i] = inputSorteados[sorteio];
                    inputSorteados[sorteio] = 0;
                    break;
                }
            }
        }
    return saidaSorteados;
    }

    public static int[][] conferirCartelas(int[][] cartelas,
                                           int[] sorteio,
                                           int[] controle) {

        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int totalJogadores = controle[4];

        int[][] conferidas = new int[totalJogadores][dezenasPorCartela];
        int limitePesquisa= (rodadasDoSorteio + 1) * dezenasPorSorteio;

        for (int i = 0; i < totalJogadores; i++) {
            for (int j = 0; j < dezenasPorCartela; j++) {
                for (int k = 0; k < limitePesquisa; k++) {
                    if (cartelas[i][j] == sorteio[k]) {
                        conferidas[i][j] = 1;
                    }
                }
            }
        }
        return conferidas;
    }

    public static int[][] gerarCartela(int[] controle) {

        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        int[][] cartelas = new int[totalJogadores][dezenasPorCartela];
        Random random = new Random();

        for (int i = 0; i < totalJogadores; i++) {
            for (int j = 0; j < dezenasPorCartela; j++) {
                if (j == 1) {
                    int sorteio = random.nextInt(dezenaMaxima) + 1;
                    cartelas[i][j] = sorteio;
                } else {
                    while (true) {
                        int sorteio = random.nextInt(dezenaMaxima) + 1;
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

    public static String[] separarJogadores(String listaNomes, int[] controle) {

        int totalJogadores = controle[4];

        String[] jogadores = listaNomes.split("-");
        for (int i = 0; i < totalJogadores; i++) {
            jogadores[i].trim();
        }
        Arrays.sort(jogadores);
        return jogadores;
    }

    public static int contarHifens(String listaNomes){

        int contador=0;
        for (int i = 0; i < listaNomes.length(); i++){
            if(listaNomes.charAt(i)=='-'){
                contador++;
            }
        }
        return contador;
    }
}