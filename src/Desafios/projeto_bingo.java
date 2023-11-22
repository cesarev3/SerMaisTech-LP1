package Desafios;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class projeto_bingo {
    public static void main(String[] args) {

        printWelcomeScreen();

        // receber nome dos jogadores em uma só entrada e separar
        Scanner readScanner = new Scanner(System.in);
        String getNames = readScanner.nextLine();
        String[] playerNames = getPlayerNames(getNames);

        // variaveis de controle Globais;
        int tensUpperLimit = 60;
        int tensPerCard = 5;
        int tensPerDrawn = 5;
        int roundsOfDrawn = 0;
        int numberOfPlayers = playerNames.length;
        int autoManualCardGeneratorMode = 0; // '0' para Automático e '1' para manual
        int autoManualDrawnMode = 0; // '0' para Automático e '1' para manual
        int numberOfWinners = 0;
        int[] control = {tensUpperLimit, tensPerCard, tensPerDrawn,
                roundsOfDrawn, numberOfPlayers, autoManualCardGeneratorMode,
                autoManualDrawnMode, numberOfWinners};

        System.out.println("\n------------- Cartelas da Sorte --------------");
        int[][] bingoCards = generateAutoBingoCard(control);
        printBingoCards(playerNames, bingoCards, control);

        System.out.println("\n\n---------------- Sorteando -----------------");

        int[] tensFromDrawn = generateTensFromAutoDrawn(control);
        int[] tensFromDrawInAscendingOrder = sortTensInAscendingOrder(tensFromDrawn,
                control);

        System.out.println("Tecle 'S' para os sorteios ou 'X' para sair");
        System.out.print("> ");
        char toContinue = Character.toUpperCase(readScanner.next().charAt(0));

        // Arrays fora do While para poderem ser utilizadas no relatório final
        String[] rankingOfPlayers = new String[numberOfPlayers];
        int[] totalNumberOfMatchesForPlayer = new int[numberOfPlayers];

        int numberOfRounds = 0;

        while (toContinue != 'X') {

            control[3] = numberOfRounds;

            int[][] bingoCardsChecked = checkTensDrawnOnBingoCards(bingoCards,
                    tensFromDrawn, control);

            totalNumberOfMatchesForPlayer = calculateNumberOfMatchesPerPlayer(bingoCardsChecked,
                    control);

            rankingOfPlayers = sortPlayersInAscendingOrder(playerNames,
                    totalNumberOfMatchesForPlayer, control);

            printTensFromDrawn(tensFromDrawInAscendingOrder, control);

            printTop3Players(rankingOfPlayers);

            boolean haveAWinner = checkForWinner(totalNumberOfMatchesForPlayer, control);

            if (haveAWinner) {
                control[7] = calculateNumberOfWinners(rankingOfPlayers,
                        totalNumberOfMatchesForPlayer, control);

                imprimirVencedor(rankingOfPlayers, totalNumberOfMatchesForPlayer, control);

                printPlayerBingoCardAndCheckTable(playerNames, bingoCards,
                        bingoCardsChecked, totalNumberOfMatchesForPlayer, control);
                break;
            }

            printPlayerBingoCardAndCheckTable(playerNames, bingoCards,
                    bingoCardsChecked, totalNumberOfMatchesForPlayer, control);

            numberOfRounds ++;

            System.out.println("Tecle 'S' para nova rodada ou 'X' para sair");
            System.out.print("> ");
            toContinue = Character.toUpperCase(readScanner.next().charAt(0));

        }

        if (toContinue == 'X') {
            System.exit(0);
        }

        System.out.println("Tecle R para relatório ou X para sair do jogo");
        System.out.print("> ");
        char toReport = Character.toUpperCase(readScanner.next().charAt(0));

       if (toReport == 'R') {
            System.out.println("\n\n---------------- Relatório ----------------");
        }

       System.out.println("\nNumero de rodadas: " + (numberOfRounds + 1));

       System.out.println("\nGanhadores:");
       String[] ganhadores = listarVencedor(rankingOfPlayers, control);

       int[][] cartelasVencedores = listarCartelaVencedora(ganhadores,
               playerNames, bingoCards, control);

        imprimirGanhadores(ganhadores, tensPerCard, cartelasVencedores);

        printAllTensDrawnOnFinalReport(numberOfRounds, tensPerDrawn, tensFromDrawInAscendingOrder);

        int[] rankingAcertos = new int[numberOfPlayers];
        ordenarRankingAcertos(numberOfPlayers, rankingAcertos, totalNumberOfMatchesForPlayer);

        System.out.println("\nRanking geral:");

        imprimirTresColunas(rankingOfPlayers, rankingAcertos, control);

        System.out.println("\nObrigado por jogar!");

        readScanner.close();

    }

    private static void printWelcomeScreen() {
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

    public static String[] getPlayerNames(String getNames) {

        String[] playerNames = getNames.split("-");
        Arrays.sort(playerNames);
        System.out.println("total de jogadores: " + playerNames.length);

        return playerNames;
    }

    public static int[][] generateAutoBingoCard(int[] control) {

        Random random = new Random();

        int tensPerCard = control[1];
        int numberOfPlayers = control[4];
        int[][] bingoCards = new int[numberOfPlayers][tensPerCard];


        for (int i = 0; i < numberOfPlayers; i++) {
            Integer[] inputTens = autoGenerateSingleTens(control);

            for (int j = 0; j < tensPerCard; j++) {
                bingoCards[i][j] = inputTens[j];
            }
            Arrays.sort(bingoCards[i]);
        }
        return bingoCards;
    }

    private static void printBingoCards(String[] playerNames,
                                        int[][] bingoCards,
                                        int[] control) {

        int forIndex;
        for (int i = 0; i < playerNames.length; i++) {
            System.out.printf("%11s = {", playerNames[i]);
            forIndex = i;

            printTo4Columns(bingoCards, forIndex, control);

            if ((i + 1) % 4 == 0) {
                System.out.print("\t\n");
            } else {
                System.out.print("\t");
            }
        }
    }

    public static int[] generateTensFromAutoDrawn(int[] control) {

        Random random = new Random();

        int tensUpperLimit = control[0];
        int toDrawn;
        int[] inputTensFromDrawn = loadDrawnMap(control);
        int[] outputTensFromDrawn = new int[tensUpperLimit];

        for (int i = 0; i < tensUpperLimit; i++) {
            while (true) {
                toDrawn = random.nextInt(tensUpperLimit);
                if (inputTensFromDrawn[toDrawn] != 0) {
                    outputTensFromDrawn[i] = inputTensFromDrawn[toDrawn];
                    inputTensFromDrawn[toDrawn] = 0;
                    break;
                }
            }
        }
        return outputTensFromDrawn;
    }

    public static int[] sortTensInAscendingOrder(int[] tensFromDrawn, int[] control) {

        int tensUpperLimit = control[0];
        int tensPerDrawn = control[2];
        int[] cacheArray = new int[tensPerDrawn];
        int[] outputTensSorted = new int[tensUpperLimit];

        for (int i = 0; i < tensUpperLimit; i += tensPerDrawn) {
            for (int j = 0; j < tensPerDrawn; j++) {
                cacheArray[j] = tensFromDrawn[j + i];
            }
            Arrays.sort(cacheArray);

            for (int k= 0; k < tensPerDrawn; k++) {
                outputTensSorted[k + i] = cacheArray[k];
            }
        }
        return outputTensSorted;
    }

    public static int[][] checkTensDrawnOnBingoCards(int[][] bingoCards,
                                                     int[] tensFromDrawn,
                                                     int[] control) {

        int tensPerCard = control[1];
        int tensPerDrawn = control[2];
        int roundsFromDrawn = control[3];
        int numberOfPlayers = control[4];

        int[][] outputCheckedTensFromDrawn = new int[numberOfPlayers][tensPerCard];
        int searchLimit= (roundsFromDrawn + 1) * tensPerDrawn;

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < tensPerCard; j++) {
                for (int k = 0; k < searchLimit; k++) {
                    if (bingoCards[i][j] == tensFromDrawn[k]) {
                        outputCheckedTensFromDrawn[i][j] = 1;
                        break;
                    }
                }
            }
        }
        return outputCheckedTensFromDrawn;
    }

    public static int[] calculateNumberOfMatchesPerPlayer(int[][] bingoCardsChecked, int[] control) {

        int numberOfPlayers = control[4];
        int[] outputNumberOfMatchesPerPlayer = new int[numberOfPlayers];


        for (int i = 0; i < numberOfPlayers; i++) {
            int sumMatches = 0;
            for (Integer n : bingoCardsChecked[i]) {
                sumMatches += n.intValue();
                outputNumberOfMatchesPerPlayer[i] = sumMatches;
            }
        }
        return outputNumberOfMatchesPerPlayer;
    }

    public static String[] sortPlayersInAscendingOrder(String[] playerNames, int[] totalMatchesPerPlayer, int[] control) {
        int numberOfPlayers = control[4];
        int swapNumber = 0;
        String swapName = "0";

        String[] scratchPlayers = new String[numberOfPlayers];
        int[] scratchMatches = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            scratchPlayers[i] = playerNames[i];
            scratchMatches[i] = totalMatchesPerPlayer[i];
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                if (scratchMatches[i] > scratchMatches[j]) {
                    swapNumber = scratchMatches[i];
                    swapName = scratchPlayers[i];
                    scratchMatches[i] = scratchMatches[j];
                    scratchPlayers[i] = scratchPlayers[j];
                    scratchMatches[j] = swapNumber;
                    scratchPlayers[j] = swapName;
                }
            }
        }
        return scratchPlayers;
    }

    public static void printTensFromDrawn(int[] tensFromDrawnInAscendingOrder,
                                          int[] control) {

        int tensPerDrawn = control[2];
        int roundsOfDrawn = control[3];
        int interval = roundsOfDrawn * tensPerDrawn;

        System.out.printf("Rodada %d = {", (roundsOfDrawn + 1));
        for (int i = interval; i < (tensPerDrawn + interval); i++) {
            System.out.printf("%2d,", tensFromDrawnInAscendingOrder[i]);
        }
        System.out.print("} \t");
    }

    public static void printTop3Players(String[] rankingOfPlayers) {

        System.out.print("*** Top 3 = ");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s, ", rankingOfPlayers[i]);
        }
        System.out.print("***");
    }

    public static boolean checkForWinner(int[] totalNumberOfMatches, int[] control) {

        int tensPerCard = control[1];
        int NumberOfPlayers = control[4];

        for (int i = 0; i < NumberOfPlayers; i++) {
            if (totalNumberOfMatches[i] >= tensPerCard) {
                return true;
            }
        }
        return false;
    }

    private static void printAllTensDrawnOnFinalReport(int rodadas,
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



    public static void printTo4Columns(int[][] inputCartela,
                                       int forIndex,
                                       int[] controle) {

        int dezenasPorCartela = controle[1];
        int valorI = forIndex;

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

    private static void printPlayerBingoCardAndCheckTable(String[] playerNames,
                                                          int[][] bingoCards,
                                                          int[][] bingoCardsChecked,
                                                          int[] cardsMatchWithDrawn,
                                                          int[] control) {

        int numberOfPlayers = control[4];
        int forIndex;

        System.out.println("\n");

        for (int k = 0; k < numberOfPlayers; k+=4) {
            int blocksOf4 = k + 4;
            if (blocksOf4 >  numberOfPlayers) {
                blocksOf4 = numberOfPlayers;
            }

            for (int i = k; i < blocksOf4; i++) {
                System.out.printf("%11s = {", playerNames[i]);
                forIndex = i;
                printTo4Columns(bingoCards, forIndex, control);
                System.out.print("\t");
            }
            System.out.println(" ");

            String info = "acertos";
            for (int i = k; i < blocksOf4; i++) {
                System.out.printf("%3d %7s = {", cardsMatchWithDrawn[i], info);
                forIndex = i;
                printTo4Columns(bingoCardsChecked, forIndex, control);
                System.out.print("\t");
            }
            System.out.println("\n ");
        }
    }





    public static void imprimirVencedor(String[] rankigJogadores,
                                        int[] acertos,
                                        int[] controle) {

        int dezenasPorCartela = controle[1];
        int totalJogadores = controle[4];
        int totalGanhadores = controle[7];

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
        int numeroVencedores = controle[7];
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
        int numeroVencedores = controle[7];
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



    public static int calculateNumberOfWinners(String[] rankigJogadores,
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


    public static int[] loadDrawnMap(int[] controle) {

        int dezenaMaxima = controle[0];
        int[] mapa = new int[dezenaMaxima];

        for (int i = 0; i < dezenaMaxima; i++) {
            mapa[i] = i + 1;
        }
        return mapa;
    }









    public static Integer[] autoGenerateSingleTens(int[] control) {
        Random random = new Random();
        
        int tensUpperLimit = control[0];
        
        int tensPerCard = control[1];
        
        Integer[] outputTens = new Integer[tensPerCard];
        List<Integer> drawnList = Arrays.asList(outputTens);

        for (int i = 0; i < tensPerCard; i++) {
            while (true) {
                int drawn = random.nextInt(tensUpperLimit) + 1;
                if (!drawnList.contains(drawn)) {
                    outputTens[i] = drawn;
                    break;
                }
            }
        }
        return outputTens;
    }

    

    

//    public static int hyphenCount(String getNames){
//
//        int counter=0;
//        for (int i = 0; i < getNames.length(); i++){
//            if(getNames.charAt(i)=='-'){
//                counter++;
//            }
//
//        }
//        return counter;
//    }
}