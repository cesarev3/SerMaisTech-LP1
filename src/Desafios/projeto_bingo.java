package Desafios;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class projeto_bingo {
    public static void main(String[] args) {

//        int[]acretos = {0,1,1,0,1,0};
//        int soma = 0;
//        for (Integer n : teste){
//            soma += n.intValue();
//        }
//        System.out.println(soma);


        imprimirTelaWelcome();

        Scanner entrada = new Scanner(System.in);
        String entradaNomes = entrada.nextLine();

        // variaveis de controle Globais;
        int dezenaMaxima = 60;
        int dezenasPorCartela = 6;
        int dezenasPorSorteio = 5;
        int rodadaSorteio = 0;
        int quantidadeJogadores = contarHifens(entradaNomes) + 1;
        int indiceFor = 0;
        int[] controle = {dezenaMaxima, dezenasPorCartela, dezenasPorSorteio,
                rodadaSorteio, quantidadeJogadores, indiceFor};

        String[] nomeJogadores = separarJogadores(entradaNomes, controle);
        System.out.println("total de jogadores: " + quantidadeJogadores);

        System.out.println("\n------------- Cartelas da Sorte --------------");

        int[][] cartelasGeradas = gerarCartela(controle);
        imprimirCartelas(nomeJogadores, cartelasGeradas, controle);

        System.out.println("\n\n---------------- Sorteando -----------------");

        int[] numerosSorteados = sortearNumeros(controle);

        int[] numerosOrdenados = ordenarDezenasSorteadas(numerosSorteados, controle);

        int rodadas = 0;
        char continua = ' ';

        while (continua != 'X') {

                controle[3] = rodadas;
                imprimirDezenasSorteadas(numerosOrdenados, controle);
                int[][] cartelasConferidas = conferirCartelas(cartelasGeradas,
                        numerosSorteados, controle);
                int[] tabelaDeAcertos = calcularAcertos(cartelasConferidas, controle);
                imprimirCartelasConferidas(nomeJogadores, cartelasGeradas,
                        cartelasConferidas, tabelaDeAcertos, controle);
                rodadas ++;

                System.out.println("Tecle qualquer tecla para uma nova rodada " +
                        "ou X para sair do jogo");
                continua = Character.toUpperCase(entrada.next().charAt(0));

        }

//        System.out.println("\n\n---------------- Conferindo ----------------");





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

    public static void imprimirQuatroColunas(int[][] inputCartela,
                                             int[] controle) {

        int dezenasPorCartela = controle[1];
        int valorI = controle[5];

            for (int j = 0; j < dezenasPorCartela; j++) {
                if (j != (dezenasPorCartela - 1)) {
                    System.out.printf("%2d, ", inputCartela[valorI][j]);
                } else {
                    System.out.printf("%2d}", inputCartela[valorI][j]);
                }
            }
    }

    private static void imprimirCartelas(String[] jogadores,
                                         int[][] cartelas,
                                         int[] controle) {

        int totalJogadores = controle[4];

        for (int i = 0; i < totalJogadores; i++) {
            System.out.printf("%17s = {", jogadores[i]);
            controle[5] = i;
            imprimirQuatroColunas(cartelas, controle);
            if ((i + 1) % 4 == 0) {
                System.out.print("     \n");
            } else {
                System.out.print("     ");
            }
        }
    }

    private static void imprimirCartelasConferidas(String[] jogadores,
                                                   int[][] cartelas,
                                                   int[][] cartelasConferidas,
                                                   int[] acertos,
                                                   int[] controle) {

        int totalJogadores = controle[4];

        for (int k = 0; k < totalJogadores; k+=4) {
            int paginas = k + 4;
            if (paginas >  totalJogadores) {
                paginas = totalJogadores;
            }
            for (int i = k; i < paginas; i++) {
                System.out.printf("%17s = {", jogadores[i]);
                controle[5] = i;
                imprimirQuatroColunas(cartelas, controle);
                System.out.print("     ");
            }
            System.out.println(" ");
            String info = "acerto(s)";
            for (int i = k; i < paginas; i++) {
                System.out.printf("%7d %9s = {", acertos[i], info);
                controle[5] = i;
                imprimirQuatroColunas(cartelasConferidas, controle);
                System.out.print("     ");
            }
            System.out.println("\n ");
        }
    }

    public static void imprimirDezenasSorteadas(int[] sorteioOrdenado, int[] controle) {

        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int intervalo = rodadasDoSorteio * dezenasPorSorteio;

        System.out.printf("Rodada %d = {", (rodadasDoSorteio + 1));
        for (int i = intervalo; i < (dezenasPorSorteio + intervalo); i++) {
            System.out.printf("%2d,", sorteioOrdenado[i]);
        }
        System.out.println("}\n");
    }

    public static int[] calcularAcertos(int[][] conferidas, int[] controle) {

        int totalJogadores = controle[4];
        int[] acertos = new int[totalJogadores];

        for (int i = 0; i < totalJogadores; i++) {
            int soma = 0;
            for (Integer n : conferidas[i]) {
                soma += n.intValue();
                acertos[i] = soma;
            }
        }
        return acertos;
    }
    public static int[] carregarMapaSorteio(int[] controle) {

        int dezenaMaxima = controle[0];
        int[] mapa = new int[dezenaMaxima];

        for (int i = 0; i < dezenaMaxima; i++) {
            mapa[i] = i + 1;
        }
        return mapa;
    }

    public static int[] ordenarDezenasSorteadas(int[] sorteio, int[] controle) {
        int dezenaMaxima = controle[0];
        int dezenasPorSorteio = controle[2];
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
                        break;
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