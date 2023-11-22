package Desafios;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class projeto_bingo {
    public static void main(String[] args) {

        imprimirTelaWelcome();

        // receber nome dos jogadores em uma só entrada
        Scanner entrada = new Scanner(System.in);
        String entradaNomes = entrada.nextLine();

        // variaveis de controle Globais;
        int dezenaMaxima = 60;
        int dezenasPorCartela = 5;
        int dezenasPorSorteio = 5;
        int rodadaSorteio = 0;
        int quantidadeJogadores = contarHifens(entradaNomes) + 1;
        int indiceFor = 0;
        int quantidadeVencedores = 0;
        int[] controle = {dezenaMaxima, dezenasPorCartela, dezenasPorSorteio,
                rodadaSorteio, quantidadeJogadores, indiceFor, quantidadeVencedores};

        // separar os nomes recebidos
        String[] nomeJogadores = separarJogadores(entradaNomes, controle);
        //System.out.println("total de jogadores: " + quantidadeJogadores);

        System.out.println("\n------------- Cartelas da Sorte --------------");

        int[][] cartelasGeradas = gerarCartela(controle);
        imprimirCartelas(nomeJogadores, cartelasGeradas, controle);

        System.out.println("\n\n---------------- Sorteando -----------------");

        int[] dezenasSorteadas = sortearDezenas(controle);
        int[] dezenasOrdenadas = ordenarDezenasSorteadas(dezenasSorteadas,
                controle);

        System.out.println("Tecle 'S' para os sorteios ou 'X' para sair");
        System.out.print("> ");

        char continua = Character.toUpperCase(entrada.next().charAt(0));
        int rodadas = 0;
        String[] rankingDosJogadores = new String[quantidadeJogadores];
        int[] tabelaDeAcertos = new int[quantidadeJogadores];

        while (continua != 'X') {

            controle[3] = rodadas;

            int[][] cartelasConferidas = conferirCartelas(cartelasGeradas,
                    dezenasSorteadas, controle);

            tabelaDeAcertos = calcularAcertos(cartelasConferidas,
                    controle);

            rankingDosJogadores = ordenarJogadores(nomeJogadores,
                    tabelaDeAcertos, controle);

            imprimirDezenasSorteadas(dezenasOrdenadas, controle);

            imprimirTop3(rankingDosJogadores);

            boolean vencedor = verificarGanhador(tabelaDeAcertos, controle);

            if (vencedor) {
                controle[6] = calcularNumeroVencedores(rankingDosJogadores, tabelaDeAcertos, controle);
                imprimirVencedor(rankingDosJogadores, tabelaDeAcertos, controle);
                imprimirCartelasConferidas(nomeJogadores, cartelasGeradas,
                        cartelasConferidas, tabelaDeAcertos, controle);
                break;
            }

            imprimirCartelasConferidas(nomeJogadores, cartelasGeradas,
                    cartelasConferidas, tabelaDeAcertos, controle);

            rodadas ++;

            System.out.println("Tecle 'S' para nova rodada ou 'X' para sair");
            System.out.print("> ");
            continua = Character.toUpperCase(entrada.next().charAt(0));

        }

        if (continua == 'X') {
            System.exit(0);
        }

        System.out.println("Tecle R para relatório ou X para sair do jogo");
        System.out.print("> ");
        char relatorio = Character.toUpperCase(entrada.next().charAt(0));

       if (relatorio == 'R') {
            System.out.println("\n\n---------------- Relatório ----------------");
        }

       System.out.println("\nNumero de rodadas: " + (rodadas + 1));

       System.out.println("\nGanhadores:");
       String[] ganhadores = listarVencedor(rankingDosJogadores, controle);

       int[][] cartelasVencedores = listarCartelaVencedora(ganhadores,
               nomeJogadores, cartelasGeradas, controle);

        imprimirGanhadores(ganhadores, dezenasPorCartela, cartelasVencedores);

        imprimirTodasDezenasSorteadas(rodadas, dezenasPorSorteio, dezenasOrdenadas);

        int[] rankingAcertos = new int[quantidadeJogadores];
        ordenarRankingAcertos(quantidadeJogadores, rankingAcertos, tabelaDeAcertos);

        System.out.println("\nRanking geral:");

        imprimirTresColunas(rankingDosJogadores, rankingAcertos, controle);

        System.out.println("\nObrigado por jogar!");

        entrada.close();

    }

    private static void imprimirTodasDezenasSorteadas(int rodadas,
                                                      int dezenasPorSorteio,
                                                      int[] dezenasOrdenadas) {

        int limite = (rodadas + 1) * dezenasPorSorteio;
        int[] totalDezenasSorteadas = new int[limite];

        for (int i = 0; i < (limite); i++) {
            totalDezenasSorteadas[i] = dezenasOrdenadas[i];
        }
        Arrays.sort(totalDezenasSorteadas);

        System.out.print("\nDezenas Sorteadas:\n{");

        for (int i = 0; i < (limite); i++) {
            if (i < (limite - 1)) {
                System.out.print(totalDezenasSorteadas[i] + ", ");
            }else {
                System.out.print(totalDezenasSorteadas[i] + "}\n");
            }
        }
    }

    private static void ordenarRankingAcertos(int quantidadeJogadores,
                                              int[] rankingAcertos,
                                              int[] tabelaDeAcertos) {

        for (int i = 0; i < quantidadeJogadores; i++) {
            rankingAcertos[i] = tabelaDeAcertos[i] * -1;
        }
        Arrays.sort(rankingAcertos);

        for (int i = 0; i < quantidadeJogadores; i++) {
            rankingAcertos[i] = rankingAcertos[i] * -1;
        }
    }

    private static void imprimirGanhadores(String[] ganhadores,
                                           int dezenasPorCartela,
                                           int[][] cartelasVencedores) {

        for (int i = 0; i < ganhadores.length; i++) {
            System.out.print(ganhadores[i] + " = {");
            for (int j = 0; j < dezenasPorCartela; j++) {
                if (j < (dezenasPorCartela -1)) {
                    System.out.print(cartelasVencedores[i][j] + ", ");
                } else {
                    System.out.print(cartelasVencedores[i][j] + "}");
                }
            }
            System.out.print("\n");
        }
    }

    private static void imprimirTelaWelcome() {
        System.out.println("|-----------------------------------------------" +
                "--------------------|");
        System.out.println("|                                               " +
                "                    |");
        System.out.println("|                  Welcome to the 50's Bingo Par" +
                "ty!                 |");
        System.out.println("|                                               " +
                "                    |");
        System.out.println("|-----------------------------------------------" +
                "--------------------|");
        System.out.println("\nHora de chamar seus colegas!");
        System.out.println("\nDigite o primeiro nome de todos na mesma linha e use '-' entre eles");
        System.out.println("Ex. Ana-Carlos-Marcola-Paula");
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

    public static void imprimirTresColunas(String[] inputJogadores,
                                           int[] inputAcertos,
                                           int[] controle) {

        int totalJogadores = controle[4];
        int limite;
        System.out.print("|---------------------------------------|-------" +
                "--------------------------------|--------------------------" +
                "-------------|\n");
        System.out.print("|\tPosição\t\t Jogador\t  Acertos\t|");
        System.out.print("\tPosição\t\t Jogador\t  Acertos\t|");
        System.out.println("\tPosição\t\t Jogador\t  Acertos\t|");
        System.out.print("|---------------------------------------|---------" +
                "------------------------------|----------------------------" +
                "-----------|\n");

        for (int i = 0; i < totalJogadores; i+=9) {
            limite = i + 3;
            if (limite > totalJogadores) {
                limite = totalJogadores;
            }
            for (int j = i; j < limite; j++) {
                System.out.printf("|\t%4d\t%12s\t\t%2d\t\t", j + 1,
                        inputJogadores[j],
                        inputAcertos[j]);

                if ((j + 3) < totalJogadores) {
                    System.out.printf("|\t%4d\t%12s\t\t%2d\t\t", j + 4,
                            inputJogadores[j + 3],
                            inputAcertos[j + 3]);
                }
                if ((j + 6) < totalJogadores) {
                    System.out.printf("|\t%4d\t%12s\t\t%2d\t\t", j + 7,
                            inputJogadores[j + 6],
                            inputAcertos[j + 6]);
                }
                System.out.print("|\n");

                if ((j + 7) % 9 == 0 && j + 6 < totalJogadores) {
                    System.out.print("|-------------------------------------" +
                            "--|---------------------------------------|----" +
                            "-----------------------------------|\n");

                } else if ((j + 4) % 3 == 0 && j + 3 < totalJogadores) {
                    System.out.print("|-------------------------------------" +
                            "--|---------------------------------------|\n");

                } else if ((j + 1) % 3 == 0 && j < totalJogadores) {
                    System.out.print("|-------------------------------------" +
                            "--|\n");
                }
            }
        }
    }

    private static void imprimirCartelas(String[] jogadores,
                                         int[][] cartelas,
                                         int[] controle) {

        int totalJogadores = controle[4];

        for (int i = 0; i < totalJogadores; i++) {
            System.out.printf("%11s = {", jogadores[i]);
            controle[5] = i;
            imprimirQuatroColunas(cartelas, controle);
            if ((i + 1) % 4 == 0) {
                System.out.print("\t\n");
            } else {
                System.out.print("\t");
            }
        }
    }

    private static void imprimirCartelasConferidas(String[] jogadores,
                                                   int[][] cartelas,
                                                   int[][] cartelasConferidas,
                                                   int[] acertos,
                                                   int[] controle) {

        int totalJogadores = controle[4];
        System.out.println("\n");

        for (int k = 0; k < totalJogadores; k+=4) {
            int paginas = k + 4;
            if (paginas >  totalJogadores) {
                paginas = totalJogadores;
            }

            for (int i = k; i < paginas; i++) {
                System.out.printf("%11s = {", jogadores[i]);
                controle[5] = i;
                imprimirQuatroColunas(cartelas, controle);
                System.out.print("\t");
            }
            System.out.println(" ");

            String info = "acertos";
            for (int i = k; i < paginas; i++) {
                System.out.printf("%3d %7s = {", acertos[i], info);
                controle[5] = i;
                imprimirQuatroColunas(cartelasConferidas, controle);
                System.out.print("\t");
            }
            System.out.println("\n ");
        }
    }

    public static void imprimirDezenasSorteadas(int[] sorteioOrdenado,
                                                int[] controle) {

        int dezenasPorSorteio = controle[2];
        int rodadasDoSorteio = controle[3];
        int intervalo = rodadasDoSorteio * dezenasPorSorteio;

        System.out.printf("Rodada %d = {", (rodadasDoSorteio + 1));
        for (int i = intervalo; i < (dezenasPorSorteio + intervalo); i++) {
            System.out.printf("%2d,", sorteioOrdenado[i]);
        }
        System.out.print("} \t");
    }

    public static void imprimirTop3(String[] rankigJogadores) {

        System.out.print("*** Top 3 = ");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s, ", rankigJogadores[i]);
        }
        System.out.print("***");
    }

    public static void imprimirVencedor(String[] rankigJogadores,
                                        int[] acertos,
                                        int[] controle) {

        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        int totalGanhadores = controle[6];

        int ganhadores = 0;

        System.out.print("\t   *** B I N G O ! ***  \t");

        for (int i = 0; i < totalJogadores; i++) {
            if (acertos[i] >= dezenasPorCartela) {
                ganhadores ++;
            }
        }
        controle[6] = ganhadores;

        if (ganhadores > 1) {
            System.out.print("*** Vencedores = ");
        } else {
            System.out.print("*** Vencedor = ");
        }

        for (int i = 0; i < ganhadores; i++) {
            System.out.printf("%s, ", rankigJogadores[i]);
        }
        System.out.print("*** \t");

    }

    public static String[] listarVencedor(String[] rankingJogadores, int[] controle) {
        int numeroVencedores = controle[6];
        String[] vencedores = new String[numeroVencedores];

        for (int i = 0; i < numeroVencedores; i++) {
            vencedores[i] = rankingJogadores[i];
        }
        return vencedores;
    }

    public static int[][] listarCartelaVencedora(String[] vencedores,
                                                 String[] jogadores,
                                                 int[][] cartelas,
                                                 int[] controle) {

        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        int numeroVencedores = controle[6];
        int[][] cartelasVencedoras = new int[numeroVencedores][dezenasPorCartela];
        int[] indiceCartela = new int[numeroVencedores];

        for (int i = 0; i < numeroVencedores; i++) {
            for (int j = 0; j < totalJogadores; j++) {
                if (vencedores[i].equals(jogadores[j])) {
                    indiceCartela[i] = j;
                }
            }
        }
        for (int i = 0; i < numeroVencedores; i++) {
            for (int j = 0; j < dezenasPorCartela; j++) {
                cartelasVencedoras[i][j] = cartelas[indiceCartela[i]][j];
            }
            Arrays.sort(cartelasVencedoras[i]);
        }
        return cartelasVencedoras;
    }

    public static boolean verificarGanhador(int[] acertos, int[] controle) {

        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        for (int i = 0; i < totalJogadores; i++) {
            if (acertos[i] >= dezenasPorCartela) {
                return true;
            }
        }
        return false;
    }

    public static int calcularNumeroVencedores(String[] rankigJogadores,
                                               int[] acertos,
                                               int[] controle) {

        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        int ganhadores = 0;

        for (int i = 0; i < totalJogadores; i++) {
            if (acertos[i] >= dezenasPorCartela) {
                ganhadores ++;
            }
        }
        return ganhadores;
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

    public static String[] ordenarJogadores(String[] jogadores, int[] acertos, int[] controle) {
        int totalJogadores = controle[4];
        int trocaNumero = 0;
        String trocaNome = "0";

        String[] scratchJogadores = new String[totalJogadores];
        int[] scratchAcertos = new int[totalJogadores];
        for (int i = 0; i < totalJogadores; i++) {
            scratchJogadores[i] = jogadores[i];
            scratchAcertos[i] = acertos[i];
        }

        for (int i = 0; i < totalJogadores; i++) {
            for (int j = 0; j < totalJogadores; j++) {
                if (scratchAcertos[i] > scratchAcertos[j]) {
                    trocaNumero = scratchAcertos[i];
                    trocaNome = scratchJogadores[i];
                    scratchAcertos[i] = scratchAcertos[j];
                    scratchJogadores[i] = scratchJogadores[j];
                    scratchAcertos[j] = trocaNumero;
                    scratchJogadores[j] = trocaNome;
                }
            }
        }
        return scratchJogadores;
    }

    public static int[] sortearDezenas(int[] controle) {

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

    public static Integer[] gerarDezenasDistintas(int[] controle) {
        Random random = new Random();
        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        Integer[] saidaDezenas = new Integer[dezenasPorCartela];
        List<Integer> lista = Arrays.asList(saidaDezenas);

        for (int i = 0; i < dezenasPorCartela; i++) {
            while (true) {
                int sorteio = random.nextInt(dezenaMaxima) + 1;
                if (!lista.contains(sorteio)) {
                    saidaDezenas[i] = sorteio;
                    break;
                }
            }
        }
        return saidaDezenas;
    }

    public static int[][] gerarCartela(int[] controle) {

        int dezenaMaxima = controle[0];
        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        int[][] cartelas = new int[totalJogadores][dezenasPorCartela];
        Random random = new Random();

        for (int i = 0; i < totalJogadores; i++) {
            Integer[] dezenasRecebidas = gerarDezenasDistintas(controle);
            for (int j = 0; j < dezenasPorCartela; j++) {
                cartelas[i][j] = dezenasRecebidas[j];
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
        System.out.println("total de jogadores: " + totalJogadores);
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