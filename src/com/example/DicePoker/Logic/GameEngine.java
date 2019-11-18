package com.example.DicePoker.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class GameEngine {

    final int STARTING_CHIPS = 100;
    final int STARTING_ANTE  = 10;



    Map<Integer, Player> playerByNumber = new HashMap<>();

    ArrayList<Player> playersAtStart = new ArrayList<>(); //Stores all players in the game at the start.
    ArrayList<Player> playersInGame = new ArrayList<>(); //Stores all players still in game.
    ArrayList<Player> playersInRound = new ArrayList<>(); //Stores all players that are still in a given round.

    ArrayList<Integer> masterPlayerOrder = new ArrayList<>();
    ArrayList<Integer> gamePlayerOrder = new ArrayList<>();
    ArrayList<Integer> roundPlayerOrder = new ArrayList<>();


    int currentAnte;
    int currentPot;
    int currentRound;

    int currentPlayerPointer = 0;
    int firstPlayerPointer = 0;

    int[] playerOrder;

    int[] playerBets = {0, 0, 0, 0};  //This array stores the total bet for all players in a round.
    int largestBetPlayerNumber = 1; //This variable keeps track of which player currently has made the largest bet.

    public GameEngine(ArrayList<Player> listOfPlayers) {

        int playerNumbers = 1;
        playerOrder = new int[listOfPlayers.size()];
        for (Player player : listOfPlayers) {
            if (player == null) {
                playerNumbers++;
                continue;
            }
            masterPlayerOrder.add(playerNumbers);
            gamePlayerOrder.add(playerNumbers);
            roundPlayerOrder.add(playerNumbers);
            playerByNumber.put(playerNumbers++, player);
            playerOrder[playerNumbers - 2] = playerNumbers - 1;
            playersAtStart.add(player);
            playersInGame.add(player);
        }

        firstPlayerPointer = 0;
        currentPlayerPointer = 0;

        currentPot = 0;




        currentAnte = STARTING_ANTE;
    }

    public boolean currentPlayerPayAnte() {

        Player cp = playerByNumber.get(gamePlayerOrder.get(currentPlayerPointer));
        if (cp.payMarkerToPot(currentAnte)) {
            currentPot += currentAnte;
            return true;
        }
        else {
            return false;
        }
    }

    public static int[] determineHandStrength (int[] diceHand) {
        /**
         * 0 = Nothing
         * 1 = Pair
         * 2 = Two Pairs
         * 3 = Three of a Kind
         * 4 = Straight
         * 5 = Full House
         * 6 = Four of a Kind
         * 7 = Five of a Kind
         *
         */

        Map<Integer, Integer>  dieValues= new HashMap<Integer, Integer>();

        dieValues.put(1, 0);
        dieValues.put(2, 0);
        dieValues.put(3, 0);
        dieValues.put(4, 0);
        dieValues.put(5, 0);
        dieValues.put(6, 0);

        int mostRolled = 0;
        int mostRolledValue = -1;

        for (int value : diceHand) {
            dieValues.put(value, (dieValues.get(value) + 1));
            if (dieValues.get(value) > mostRolled) {
                mostRolled++;
                mostRolledValue = value;
            }
        }

        int[] output = new int[5];
        switch (mostRolled) {
            case 5: //This means person has a Five of a Kind hand
                output[0] = 7;
                output [1] = mostRolledValue;
                break;
            case 4: //This means the player has a Four of a kind.
                output[0] = 6;
                output[1] = mostRolledValue;
                for (int value : dieValues.keySet()) {
                    if (dieValues.get(value) == 1) {
                        output[2] = value;
                        break;
                    }
                }
                break;
            case 3: // This means the player has either Three of a Kind of a Full House.
                int tiebreaker1 = -1;
                for (int value : dieValues.keySet()) {
                    if (dieValues.get(value) == 2) { // This means the player has a full house.
                        output[0] = 5;
                        output[1] = mostRolledValue;
                        output[2] = value;
                        break;
                    }
                    else if (dieValues.get(value) == 1){
                        if (tiebreaker1 == -1) {
                            tiebreaker1 = value;
                        }
                        else {
                            if (value > tiebreaker1) { // This means the player has three of a kind
                                output[0] = 3;
                                output[1] = mostRolledValue;
                                output[2] = value;
                                output[3] = tiebreaker1;
                                break;
                            }
                            else {
                                output[0] = 3;
                                output[1] = mostRolledValue;
                                output[2] = tiebreaker1;
                                output[3] = value;
                                break;
                            }
                        }
                    }
                }


                break;
            case 2:  //This means the player has a pair or two pairs.
                int secondPair = -1;

                int kicker1 = -1;
                int kicker2 = -1;

                for (int value : dieValues.keySet()) {
                    if (dieValues.get(value) == 2 && value != mostRolledValue) { // This means the player has 2 pairs
                        secondPair = value;
                    }
                    else if (dieValues.get(value) == 1) {
                        if (kicker1 == -1) {
                            kicker1 = value;
                        }
                        else if (kicker2 == -1) {
                            kicker2 = value;
                        }
                        else {
                            if (kicker1 > kicker2) {
                                if (kicker2 > value) {
                                    output[0] = 1;
                                    output[1] = mostRolledValue;
                                    output[2] = kicker1;
                                    output[3] = kicker2;
                                    output[4] = value;
                                    break;
                                }
                                else {
                                    if (kicker1 > value) {
                                        output[0] = 1;
                                        output[1] = mostRolledValue;
                                        output[2] = kicker1;
                                        output[3] = value;
                                        output[4] = kicker2;
                                        break;
                                    }
                                    else {
                                        output[0] = 1;
                                        output[1] = mostRolledValue;
                                        output[2] = value;
                                        output[3] = kicker1;
                                        output[4] = kicker2;
                                        break;
                                    }
                                }
                            }
                            else if (kicker2 > value) {
                                if (kicker1 > value) {
                                    output[0] = 1;
                                    output[1] = mostRolledValue;
                                    output[2] = kicker2;
                                    output[3] = kicker1;
                                    output[4] = value;
                                    break;
                                }
                                else {
                                    output[0] = 1;
                                    output[1] = mostRolledValue;
                                    output[2] = kicker2;
                                    output[3] = value;
                                    output[4] = kicker1;
                                    break;
                                }
                            }
                            else {
                                output[0] = 1;
                                output[1] = mostRolledValue;
                                output[2] = value;
                                output[3] = kicker2;
                                output[4] = kicker1;
                                break;
                            }
                        }
                    }
                }
                if (output[0] == 0) {
                    if (secondPair > mostRolledValue) {
                        output[0] = 2;
                        output[1] = secondPair;
                        output[2] = mostRolledValue;
                        output[3] = kicker1;
                    }
                    else {
                        output[0] = 2;
                        output[1] = mostRolledValue;
                        output[2] = secondPair;
                        output[3] = kicker1;
                    }
                }
                break;
            case 1: //This means the player has nothing or a straight.
                if (dieValues.get(1) == 0) {
                    output[0] = 4;
                    output[1] = 6;
                }
                else if (dieValues.get(6) == 0) {
                    output[0] = 4;
                    output[1] = 5;
                }
                else if (dieValues.get(2) == 0) {
                    output[0] = 0;
                    output[1] = 2;
                }
                else if (dieValues.get(3) == 0) {
                    output[0] = 0;
                    output[1] = 3;
                }
                else if (dieValues.get(4) == 0) {
                    output[0] = 0;
                    output[1] = 4;
                }
                else if (dieValues.get(5) == 0) {
                    output[0] = 0;
                    output[1] = 5;
                }
                break;
        }
        return output;
    }

    public static int[][] sortHandStrength(int[][] handStrength) {

        int[][] output = handStrength.clone();

        for (int i = 1; i < output.length; i++ ) {
            for (int j = i; j > 0; j-- ) {
                if (compareHandStrength(output[j], output[j-1])) {
                    int[] temp = output[j-1];
                    output[j-1] = output[j];
                    output[j] = temp;
                }
                else {
                    break;
                }
            }

        }
        return output;

    }

    public static boolean compareHandStrength(int[] arg1, int[] arg2) { //Tests whether arg1 has a greater hand strength then hand 2.

        if (arg1[0] > arg2[0]) { return true; }
        else if(arg1[0] < arg2[0]){ return false; }
        else {
            if (arg1[0] == 0) {
                if (arg1[1] < arg2[1]) { return true; }
                else { return false; }
            }
            else {
                for (int i = 1; i < 5; i++ ) {
                   if (arg1[i] > arg2[i]) { return true; }
                   else if(arg1[i] < arg2[i]){ return false; }
                }
                return false; //This case indicates a tie.
            }
        }
    }

    public ArrayList<Integer> getPlayerOrder() {
        return this.masterPlayerOrder;
    }

   public Player getCurrentPlayer() {
        return playerByNumber.get(roundPlayerOrder.get(currentPlayerPointer));
   }

    /**
     * This function rolls all the dice held by the current player, and returns the new values.
     *
     * @return Returns an array of integers corresponding to the values of the rolled dice.
     */
   public int[] rollCurrentPlayer() {
       playerByNumber.get(roundPlayerOrder.get(currentPlayerPointer)).rollAllDice();
       int dieNumber = 0;
       int[] output = new int[5];
       for (Die die : playerByNumber.get(gamePlayerOrder.get(currentPlayerPointer)).getDice()) {
           output[dieNumber++] = die.getCurrentFace();
       }
       return output;
   }

   public int[] rerollCurrentPlayer(boolean[] choice) {
       playerByNumber.get(roundPlayerOrder.get(currentPlayerPointer)).rollSomeDie(choice[0], choice[1], choice[2], choice[3], choice[4]);
       return playerByNumber.get(roundPlayerOrder.get(currentPlayerPointer)).getDieValues();
   }

   public Player getPlayer(int playerNumber) {
        return playerByNumber.get(playerNumber);
   }

   public int getCurrentPot() {return currentPot;}

   public ArrayList<Player> getListOfAllPlayers() {
       return playersInGame;
    }

    /**
     * This method updates the state of the game to start a new round.
     *
     */
    public void newRound() {

       currentRound++;
       if (currentRound % 5 == 0) {
           currentAnte += 10;
       }
       largestBetPlayerNumber = gamePlayerOrder.get(firstPlayerPointer);
       nextFirstPlayer();
       currentPlayerPointer = firstPlayerPointer;
       roundPlayerOrder = new ArrayList<>(gamePlayerOrder);

       currentPot = 0;
       playerBets = new int[] {0, 0, 0, 0};


    }

    public void nextFirstPlayer() {

       if (firstPlayerPointer + 1 >= roundPlayerOrder.size()) {
           firstPlayerPointer = 0;
       }
       else {
           firstPlayerPointer++;
       }
    }

    public int getCurrentPlayerNumber() {return roundPlayerOrder.get(currentPlayerPointer);}

    public void nextPlayer() {

        if (currentPlayerPointer + 1 >= roundPlayerOrder.size()) { currentPlayerPointer = 0; }
        else { currentPlayerPointer++; }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isCurrentPlayerFirstPlayer() {

        System.out.println("CPP: " + currentPlayerPointer + " FPP: " + firstPlayerPointer);
        return (roundPlayerOrder.get(currentPlayerPointer) == roundPlayerOrder.get(firstPlayerPointer));
    }

    public boolean currentPlayerBet(int bet) {

        if (playerByNumber.get(roundPlayerOrder.get(currentPlayerPointer)).payMarkerToPot(bet)) {
            if (playerBets[getCurrentPlayerNumber() - 1] + bet > playerBets[largestBetPlayerNumber - 1]) {
                currentPot += bet;
                largestBetPlayerNumber = getCurrentPlayerNumber();
                playerBets[getCurrentPlayerNumber() - 1] += bet;
                return true;
            }
            else {
                System.out.println("The player must bet more than the previous max bet!");
                return false;
            }
        }
        else{
            System.out.println("The player cannot afford this bet!");
            return false;
        }

    }

    public void currentPlayerCall() {

        int callingCost = playerBets[largestBetPlayerNumber - 1] - playerBets[roundPlayerOrder.get(currentPlayerPointer) - 1];
        System.out.println("Calling cost for this player is " + callingCost);
        if (playerByNumber.get(roundPlayerOrder.get(currentPlayerPointer)).payMarkerToPot(callingCost)) {
            currentPot += callingCost;
            playerBets[getCurrentPlayerNumber() - 1] += callingCost;
        }

    }

    public void currentPlayerFold() {
        roundPlayerOrder.remove(currentPlayerPointer);
        playerBets[currentPlayerPointer] = -1;
        if (currentPlayerPointer == roundPlayerOrder.size()) {
            currentPlayerPointer = 0;
        }

    }

    public boolean isCurrentPlayerLastPlayer() {
        if (roundPlayerOrder.size() == 1) return true;
        else return false;
    } //Tests if there is only one player left in the round

    public int getCurrentPlayerBet() {

        return playerBets[getCurrentPlayerNumber() - 1];
    }

    public int getHihgestBet() {
        System.out.println("LBPN: " + largestBetPlayerNumber);
        if (largestBetPlayerNumber == -1) {
            return 0;
        }

        return playerBets[largestBetPlayerNumber - 1];
    }

    public int[] getCurrentPlayerHandStrength() {
        return determineHandStrength(getCurrentPlayer().getDieValues());
    }

    public int getCurrentPlayerCallingCost() {
        return playerBets[largestBetPlayerNumber - 1] - playerBets[roundPlayerOrder.get(currentPlayerPointer) - 1];
    }

    public boolean isBettingDone() {

        return (getCurrentPlayerNumber() == largestBetPlayerNumber);
    } //tests if the current player is the one who leads the betting (which means the betting phase is done)

    public ArrayList<Integer> roundWinner() {

        int[][] playerDice = new int[roundPlayerOrder.size()][5];
        int[][] playerHandStrength = new int[roundPlayerOrder.size()][5];
        int[][] sortedPlayerHandStrength;

        for(int i = 0; i < roundPlayerOrder.size(); i++) {
            playerDice[i] = playerByNumber.get(roundPlayerOrder.get(i)).getDieValues();
            playerHandStrength[i] = determineHandStrength(playerDice[i]);
        }
        sortedPlayerHandStrength = sortHandStrength(playerHandStrength);

        ArrayList<Integer> output = new ArrayList<>();
        for (int i = 0; i < roundPlayerOrder.size(); i++) {
            if (Arrays.equals(sortedPlayerHandStrength[0], playerHandStrength[i])) {
                output.add(roundPlayerOrder.get(i));
            }

        }
        return output;
    }

    public void eliminateCurrentPlayer() {
        System.out.println("Player " + getCurrentPlayerNumber() + " has been defeated");

            if (firstPlayerPointer >= roundPlayerOrder.size() - 1) {
                System.out.println("Boop");
                firstPlayerPointer = 0;
            }

        currentPot += getCurrentPlayer().getMarker();
        getCurrentPlayer().setMarker(0);
        gamePlayerOrder.remove(currentPlayerPointer);
        roundPlayerOrder.remove(currentPlayerPointer);


        System.out.println("CPP: " + currentPlayerPointer + " RPOs: " + roundPlayerOrder.size());
        if (currentPlayerPointer == roundPlayerOrder.size()) {
            System.out.println("Beep");
            currentPlayerPointer = 0;
        }
        System.out.println("It is now player " + getCurrentPlayerNumber() + "s turn");
    }

    public boolean isPlayerWinner() {
        if (gamePlayerOrder.size() == 1) return true;
        else return false;
    }






}
