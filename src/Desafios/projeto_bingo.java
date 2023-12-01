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

        // variaveis de configuração e controle Globais;
        int tensUpperLimit = 60;
        int tensPerCard = 5;
        int tensPerDrawing = 5;
        int roundsOfDrawing = 0;
        int numberOfPlayers = playerNames.length;
        int autoManualCardGeneratorMode = 0; // '0' Automático e '1' Manual
        int autoManualDrawingMode = 0; // '0' Automático e '1' Manual
        int numberOfWinners = 0;
        int forIndex = 0;
        int[] control = {tensUpperLimit, tensPerCard, tensPerDrawing,
                roundsOfDrawing, numberOfPlayers, autoManualCardGeneratorMode,
                autoManualDrawingMode, numberOfWinners, forIndex};

        int[][] bingoCards = new int[numberOfPlayers][tensPerCard];

        // seleciona o tipo de sorteio ou sair do jogo
        String cardString = "Cartela Automática";
        AutoManualMessage(cardString);

        control[5] = autoManualSelect();
        if(control[5] == 0) {
             bingoCards = generateAutoBingoCard(control);
             printBingoCards(playerNames, bingoCards, control);
        } else {
             bingoCards = generateManualBingoCard(control);
             printBingoCards(playerNames, bingoCards, control);
        }

        // gera as cartelas e imprime ao lado dos jogadores
        System.out.println("\n------------- Cartelas da Sorte --------------");

        // realiza sorteio prévio e ordena por grupos de "dezenas por cartela"
        int[] tensFromDrawing = generateAutoTensFromDrawing(control);
        int[] tensFromDrawInAscendingOrder = sortTensInAscendingOrder(
                tensFromDrawing, control);

        // seleciona o tipo de sorteio ou sair do jogo
        String drawingString = "Sorteio Automático";
        AutoManualMessage(drawingString);

        control[6] = autoManualSelect();


        // Arrays fora do While para poderem ser utilizadas no relatório final
        String[] playersRank = new String[numberOfPlayers];
        int[] numberOfMatchesFromPlayer = new int[numberOfPlayers];

//        System.out.println("\n\n---------------- Sorteando -----------------");

        // inicia o módulo de sorteio de dezenas a cada rodada
        int toContinue = 'S';
        int numberOfRounds = 0;
        while (toContinue != 'X') {
            System.out.println("--------------- Sorteando ----------------\n");

            control[3] = numberOfRounds;

            // Confere as cartelas, calcula o número de acertos e faz ranking
            int[][] bingoCardsChecked = matchTensFromDrawingOnBingoCards(
                    bingoCards, tensFromDrawing, control);
            numberOfMatchesFromPlayer = calculateNumberOfMatchesPerPlayer(
                    bingoCardsChecked, control);
            playersRank = sortPlayersInAscendingOrder(playerNames,
                    numberOfMatchesFromPlayer, control);

            // imprime as dezenas sorteadas e os jogadores no Top 3
            printTensFromDrawing(tensFromDrawInAscendingOrder, control);
            printTop3Players(playersRank);

            // testa e imprime ganhadores completando a página do sorteio
            boolean haveAWinner = checkNumberOfWinners(numberOfMatchesFromPlayer,
                    control);
            if (haveAWinner) {
                printWinnersOnDrawingScreen(playersRank, control);
                printPlayersAndCardsChecked(playerNames, bingoCards,
                        bingoCardsChecked, numberOfMatchesFromPlayer, control);
                break;
            }

            // imprime a página do sorteio a cada rodada
            printPlayersAndCardsChecked(playerNames, bingoCards,
                    bingoCardsChecked, numberOfMatchesFromPlayer, control);

            numberOfRounds ++;

            // usuário decide se vai para a próxima rodada ou sai do jogo
            System.out.println("Tecle 'S' para nova rodada ou 'X' para sair");
            System.out.print("> ");
            toContinue = Character.toUpperCase(readScanner.next().charAt(0));
        }

        // garante que o jogo seja abortado após sair do while na opção X
        if (toContinue == 'X') {
            System.exit(0);
        }

        // mantem a tela de sorteio parada até que o usuário decida continuar
        System.out.println("Tecle R para relatório ou X para sair do jogo");
        System.out.print("> ");
        char toReport = Character.toUpperCase(readScanner.next().charAt(0));

        if (toReport == 'X') {
            System.exit(0);
        } else {
            System.out.println("\n\n--------------- Relatório --------------");
        }

        // associa os Vencedores ranqueados a suas cartelas (ajuste de indice)
        int[][] bingoCardsFromWinners = associateWinnersToTheirCards(playersRank,
                playerNames, bingoCards, control);

        // imprime rodadas, vencedores com cartelas e todas dezenas sorteadas
        System.out.println("\nNumero de rodadas: " + (numberOfRounds + 1));
        System.out.println("\nGanhadores:");
        printWinnersAndCardsOnReportScreen(playersRank, bingoCardsFromWinners,
                control);
        printAllTensDrawingOnFinalReport(numberOfRounds, tensPerDrawing,
                tensFromDrawInAscendingOrder);

        // imprime o ranking geral em 3 colunas
        System.out.println("\nRanking geral:");
        printRankingReportIn3Columns(playersRank, numberOfMatchesFromPlayer, control);

        // mensagem de agradecimento ao usuário
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
        System.out.println("\nDigite o primeiro nome de todos na mesma linha" +
                " e use '-' entre eles");
        System.out.println("Ex. Ana-Carlos-Marcola-Paula");
        System.out.print("> ");
    }

    public static String[] getPlayerNames(String getNames) {
        String[] playerNames = getNames.split("-");
        Arrays.sort(playerNames);
        System.out.println("total de jogadores: " + playerNames.length);
        System.out.println("Jogadores ordenados por ordem alfabética");
        return playerNames;
    }

    private static void AutoManualMessage(String module) {
        System.out.println("\nTecle 'A' para  " + module +
                ", 'M' para Manual ou 'X' para sair");
        System.out.print("> ");
    }

    public static int autoManualSelect() {
        Scanner readScanner = new Scanner(System.in);

        int control = 0;
        while (true) {
            char inputChar = Character.toUpperCase(readScanner.next().charAt(0));
            if (inputChar == 'A') {
                control = 0;
                break;
            } else if (inputChar == 'M') {
                control = 1;
                break;
            } else if (inputChar == 'X') {
                System.exit(0);
            } else {
                System.out.print("> ");
            }
        }
        return control;
    }

    public static int[][] generateAutoBingoCard(int[] control) {
        int tensPerCard = control[1];
        int numberOfPlayers = control[4];
        int[][] outputBingoCards = new int[numberOfPlayers][tensPerCard];

        for (int i = 0; i < numberOfPlayers; i++) {
            Integer[] inputTens = autoGenerateSingleTens(control);
            for (int j = 0; j < tensPerCard; j++) {
                outputBingoCards[i][j] = inputTens[j];
            }
            Arrays.sort(outputBingoCards[i]);
        }
        return outputBingoCards;
    }

    public static Integer[] autoGenerateSingleTens(int[] control) {
        Random random = new Random();

        int tensUpperLimit = control[0];
        int tensPerCard = control[1];
        Integer[] outputTens = new Integer[tensPerCard];
        List<Integer> drawingList = Arrays.asList(outputTens);

        for (int i = 0; i < tensPerCard; i++) {
            while (true) {
                int drawing = random.nextInt(tensUpperLimit) + 1;
                if (!drawingList.contains(drawing)) {
                    outputTens[i] = drawing;
                    break;
                }
            }
        }
        return outputTens;
    }

    private static void printBingoCards(String[] playerNames,
                                        int[][] bingoCards,
                                        int[] control) {
        for (int i = 0; i < playerNames.length; i++) {
            System.out.printf("%11s = {", playerNames[i]);
            control[8] = i;
            printTo4Columns(bingoCards, control);
            if ((i + 1) % 4 == 0) {
                System.out.print("\t\n");
            } else {
                System.out.print("\t");
            }
        }
    }

    public static void printTo4Columns(int[][] bingoCards,
                                       int[] control) {
        int tensPerCard = control[1];
        int iValue = control[8];

        for (int j = 0; j < tensPerCard; j++) {
            if (j != (tensPerCard - 1)) {
                System.out.printf("%2d, ", bingoCards[iValue][j]);
            } else {
                System.out.printf("%2d}", bingoCards[iValue][j]);
            }
        }
    }

    public static int[] generateAutoTensFromDrawing(int[] control) {
        Random random = new Random();

        int tensUpperLimit = control[0];
        int[] inputTensToDrawing = loadContainerOfTensToDrawing(control);
        int[] outputTensFromDrawing = new int[tensUpperLimit];

        for (int i = 0; i < tensUpperLimit; i++) {
            while (true) {
                int toDrawing = random.nextInt(tensUpperLimit);
                if (inputTensToDrawing[toDrawing] != 0) {
                    outputTensFromDrawing[i] = inputTensToDrawing[toDrawing];
                    inputTensToDrawing[toDrawing] = 0;
                    break;
                }
            }
        }
        return outputTensFromDrawing;
    }

    public static int[] loadContainerOfTensToDrawing(int[] control) {
        int tensUpperLimit = control[0];
        int[] outputContainerOfTens = new int[tensUpperLimit];

        for (int i = 0; i < tensUpperLimit; i++) {
            outputContainerOfTens[i] = i + 1;
        }
        return outputContainerOfTens;
    }

    public static int[] sortTensInAscendingOrder(int[] tensFromDrawing,
                                                 int[] control) {
        int tensUpperLimit = control[0];
        int tensPerDrawing = control[2];
        int[] cacheArray = new int[tensPerDrawing];
        int[] outputTensSorted = new int[tensUpperLimit];

        for (int i = 0; i < tensUpperLimit; i += tensPerDrawing) {
            for (int j = 0; j < tensPerDrawing; j++) {
                cacheArray[j] = tensFromDrawing[j + i];
            }
            Arrays.sort(cacheArray);
            for (int k= 0; k < tensPerDrawing; k++) {
                outputTensSorted[k + i] = cacheArray[k];
            }
        }
        return outputTensSorted;
    }

    public static int[][] matchTensFromDrawingOnBingoCards(int[][] bingoCards,
                                                         int[] tensFromDrawing,
                                                         int[] control) {
        int tensPerCard = control[1];
        int tensPerDrawing = control[2];
        int roundsFromDrawing = control[3];
        int numberOfPlayers = control[4];
        int[][] outputCheckedTensFromDrawing = new int[numberOfPlayers][tensPerCard];
        int searchLimit = (roundsFromDrawing + 1) * tensPerDrawing;

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < tensPerCard; j++) {
                for (int k = 0; k < searchLimit; k++) {
                    if (bingoCards[i][j] == tensFromDrawing[k]) {
                        outputCheckedTensFromDrawing[i][j] = 1;
                        break;
                    }
                }
            }
        }
        return outputCheckedTensFromDrawing;
    }

    public static int[] calculateNumberOfMatchesPerPlayer(int[][] bingoCardsChecked,
                                                          int[] control) {
        int numberOfPlayers = control[4];
        int[] outputNumberOfMatchesPerPlayer = new int[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            int matches = 0;
            for (Integer n : bingoCardsChecked[i]) {
                matches += n.intValue();
                outputNumberOfMatchesPerPlayer[i] = matches;
            }
        }
        return outputNumberOfMatchesPerPlayer;
    }

    public static String[] sortPlayersInAscendingOrder(String[] playerNames,
                                                       int[] totalMatchesPerPlayer,
                                                       int[] control) {
        int numberOfPlayers = control[4];
        String[] scratchPlayers = new String[numberOfPlayers];
        int[] scratchMatches = new int[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            scratchPlayers[i] = playerNames[i];
            scratchMatches[i] = totalMatchesPerPlayer[i];
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                if (scratchMatches[i] > scratchMatches[j]) {
                    int swapNumber = scratchMatches[i];
                    String swapName = scratchPlayers[i];
                    scratchMatches[i] = scratchMatches[j];
                    scratchPlayers[i] = scratchPlayers[j];
                    scratchMatches[j] = swapNumber;
                    scratchPlayers[j] = swapName;
                }
            }
        }
        return scratchPlayers;
    }

    public static void printTensFromDrawing(int[] tensFromDrawingInAscendingOrder,
                                          int[] control) {
        int tensPerDrawing = control[2];
        int roundsOfDrawing = control[3];
        int interval = roundsOfDrawing * tensPerDrawing;

        System.out.printf("Rodada %d = {", (roundsOfDrawing + 1));
        for (int i = interval; i < (tensPerDrawing + interval); i++) {
            System.out.printf("%2d,", tensFromDrawingInAscendingOrder[i]);
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

    public static boolean checkNumberOfWinners(int[] totalNumberOfMatches,
                                               int[] control) {
        int tensPerCard = control[1];
        int NumberOfPlayers = control[4];
        int winners = 0;

        for (int i = 0; i < NumberOfPlayers; i++) {
            if (totalNumberOfMatches[i] >= tensPerCard) {
                winners++;
            }
        }
        control[7] = winners;
        if (winners > 0) return true;
        else return false;
    }

    public static void printWinnersOnDrawingScreen(String[] playersRank,
                                                 int[] control) {
        int numberOfWinners = control[7];

        System.out.print("\t   *** B I N G O ! ***  \t");
        if (numberOfWinners > 1) {
            System.out.print("*** Vencedores = ");
        } else {
            System.out.print("*** Vencedor = ");
        }
        for (int i = 0; i < numberOfWinners; i++) {
            System.out.printf("%s, ", playersRank[i]);
        }
        System.out.print("*** \t");

    }

    private static void printPlayersAndCardsChecked(String[] playerNames,
                                                    int[][] bingoCards,
                                                    int[][] bingoCardsChecked,
                                                    int[] cardsMatchWithDrawing,
                                                    int[] control) {
        int numberOfPlayers = control[4];
        System.out.println("\n");

        for (int k = 0; k < numberOfPlayers; k+=4) {
            int blocksOf4 = k + 4;
            if (blocksOf4 >  numberOfPlayers) {
                blocksOf4 = numberOfPlayers;
            }

            for (int i = k; i < blocksOf4; i++) {
                System.out.printf("%11s = {", playerNames[i]);
                control[8] = i;
                printTo4Columns(bingoCards, control);
                System.out.print("\t");
            }
            System.out.println(" ");

            String info = "acertos";
            for (int i = k; i < blocksOf4; i++) {
                System.out.printf("%3d %7s = {", cardsMatchWithDrawing[i], info);
                control[8] = i;
                printTo4Columns(bingoCardsChecked, control);
                System.out.print("\t");
            }
            System.out.println("\n ");
        }
    }

//    public static String[] REVISARprintWinnersOnReportScreen(String[] playersRank, int[] control) {
//        int numberOfWinners = control[7];
//        String[] winners = new String[numberOfWinners];
//
//        for (int i = 0; i < numberOfWinners; i++) {
//            winners[i] = playersRank[i];
//        }
//        return winners;
//    }

    public static int[][] associateWinnersToTheirCards(String[] playersRank,
                                                       String[] playersName,
                                                       int[][] bingoCards,
                                                       int[] control) {
        int tensPerCard = control[1];
        int numberOfPLayers = control[4];
        int numberOfWinners = control[7];
        int[][] winnersCard = new int[numberOfWinners][tensPerCard];
        int[] cardIndex = new int[numberOfWinners];

        for (int i = 0; i < numberOfWinners; i++) {
            for (int j = 0; j < numberOfPLayers; j++) {
                if (playersRank[i].equals(playersName[j])) {
                    cardIndex[i] = j;
                }
            }
        }

        for (int i = 0; i < numberOfWinners; i++) {
            for (int j = 0; j < tensPerCard; j++) {
                winnersCard[i][j] = bingoCards[cardIndex[i]][j];
            }
            Arrays.sort(winnersCard[i]);
        }
        return winnersCard;
    }

    private static void printWinnersAndCardsOnReportScreen(String[] playersRank,
                                                           int[][] cartelasVencedores,
                                                           int[] control) {
        int tensPerCard = control[1];
        int numberOfWinners = control[7];

        for (int i = 0; i < numberOfWinners; i++) {
            System.out.print(playersRank[i] + " = {");
            for (int j = 0; j < tensPerCard; j++) {
                if (j < (tensPerCard -1)) {
                    System.out.print(cartelasVencedores[i][j] + ", ");
                } else {
                    System.out.print(cartelasVencedores[i][j] + "}");
                }
            }
            System.out.print("\n");
        }
    }

    private static int[] sortMatchTableInDescendingOrder(
            int[] numberOfMatchesFromPlayer) {
        int[] matchesRank = new int[numberOfMatchesFromPlayer.length];

        for (int i = 0; i < numberOfMatchesFromPlayer.length; i++) {
            matchesRank[i] = numberOfMatchesFromPlayer[i] * -1;
        }
        Arrays.sort(matchesRank);

        for (int i = 0; i < numberOfMatchesFromPlayer.length; i++) {
            matchesRank[i] = matchesRank[i] * -1;
        }
        return matchesRank;
    }

    private static void printAllTensDrawingOnFinalReport(int rodadas,
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
            if (i == 32) {
                System.out.print("\n");
            }
            if (i < (limite - 1)) {
                System.out.print(totalDezenasSorteadas[i] + ", ");
            }else {
                System.out.print(totalDezenasSorteadas[i] + "}\n");
            }
        }
    }

    public static void printRankingReportIn3Columns(String[] playersRank,
                                                    int[] numberOfMatchesFromPlayer,
                                                    int[] control) {
        int[] inputMatchTable = sortMatchTableInDescendingOrder(numberOfMatchesFromPlayer);
        System.out.print("|---------------------------------------|-------" +
                "--------------------------------|--------------------------" +
                "-------------|\n");
        System.out.print("|\tPosição\t\t Jogador\t  Acertos\t|");
        System.out.print("\tPosição\t\t Jogador\t  Acertos\t|");
        System.out.println("\tPosição\t\t Jogador\t  Acertos\t|");
        System.out.print("|---------------------------------------|---------" +
                "------------------------------|----------------------------" +
                "-----------|\n");

        int numberOfPlayers = control[4];
        //int limite;

        for (int i = 0; i < numberOfPlayers; i+=9) {
            int columnControl = i + 3;
            if (columnControl > numberOfPlayers) {
                columnControl = numberOfPlayers;
            }
            for (int j = i; j < columnControl; j++) {
                System.out.printf("|\t%4d\t%12s\t\t%2d\t\t", j + 1,
                        playersRank[j],
                        inputMatchTable[j]);
                if ((j + 3) < numberOfPlayers) {
                    System.out.printf("|\t%4d\t%12s\t\t%2d\t\t", j + 4,
                            playersRank[j + 3],
                            inputMatchTable[j + 3]);
                }
                if ((j + 6) < numberOfPlayers) {
                    System.out.printf("|\t%4d\t%12s\t\t%2d\t\t", j + 7,
                            playersRank[j + 6],
                            inputMatchTable[j + 6]);
                }
                System.out.print("|\n");

                if ((j + 7) % 9 == 0 && j + 6 < numberOfPlayers) {
                    System.out.print("|-------------------------------------" +
                            "--|---------------------------------------|----" +
                            "-----------------------------------|\n");

                } else if ((j + 4) % 3 == 0 && j + 3 < numberOfPlayers) {
                    System.out.print("|-------------------------------------" +
                            "--|---------------------------------------|\n");

                } else if ((j + 1) % 3 == 0 && j < numberOfPlayers) {
                    System.out.print("|-------------------------------------" +
                            "--|\n");
                }
            }
        }
    }
    // área de métodos manuais
    public static String getCards() {
        Scanner readScanner = new Scanner(System.in);

        System.out.println("\nDigite cada dezena separada por ',' e use '-' " +
                "para separar as cartelas");
        System.out.println("Ex. 1,2,3,4,5-2,3,4,5,6-3,4,5,6,7");
        System.out.print("> ");
        return readScanner.nextLine();
    }
    public static String[] getCardTens(String getCards) {
        String[] cards = getCards.split("-");
        //Arrays.sort(cards);
        System.out.println("total de cartelas: " + cards.length);
        return cards;
    }

    public static int[][] generateManualBingoCard(int[] control) {

        int tensPerCard = control[1];
        int numberOfPlayers = control[4];
        String[] cardTens = getCardTens(getCards());
        String[] scratchArea = new String[tensPerCard];
        int[][] outputBingoCards = new int[numberOfPlayers][tensPerCard];

        for (int i = 0; i < numberOfPlayers; i++) {
            scratchArea = cardTens[i].split(",");
            for (int j = 0; j < tensPerCard; j++) {
                outputBingoCards[i][j] = Integer.parseInt(scratchArea[j]);
            }
            Arrays.sort(outputBingoCards[i]);
        }
        return outputBingoCards;
    }
}