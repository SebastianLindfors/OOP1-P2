package com.example.DicePoker.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    final int STARTING_CHIPS = 100;
    final int STARTING_ANTE  = 10;



    Map<Integer, Player> playerByNumber = new HashMap<>();

    ArrayList<Player> playersAtStart = new ArrayList<>(); //Stores all players in the game at the start.
    ArrayList<Player> playersInGame = new ArrayList<>(); //Stores all players still in game.
    ArrayList<Player> playersInRound = new ArrayList<>(); //Stores all players that are still in a given round.

    ArrayList<Integer> masterPlayerOrder = new ArrayList<>();
    ArrayList<Integer> gamePlayerOrder = new ArrayList<>();
    ArrayList<Integer> RoundPlayerOrder = new ArrayList<>();


    int currentAnte;
    int currentPot;
    int currentRound;

    int currentPlayerPointer = 0;
    int firstPlayerPointer = 0;

    int[] playerOrder;

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
        if (cp.payMarker(currentAnte)) {
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

        int[][] output = handStrength;

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


    private int nextPlayer() {
        if (currentPlayerPointer++ == playersInGame.size()) return 0;
        else return currentPlayerPointer++;
    }

    public ArrayList<Integer> getPlayerOrder() {
        return this.masterPlayerOrder;
    }

   public Player getCurrentPlayer() {
        return playerByNumber.get(gamePlayerOrder.get(currentPlayerPointer));
   }

    /**
     * This function rolls all the dice held by the current player, and returns the new values.
     *
     * @return Returns an array of integers corresponding to the values of the rolled dice.
     */
   public int[] rollCurrentPlayer() {
       playerByNumber.get(gamePlayerOrder.get(currentPlayerPointer)).rollAllDice();
       int dieNumber = 0;
       int[] output = new int[5];
       for (Die die : playerByNumber.get(gamePlayerOrder.get(currentPlayerPointer)).getDice()) {
           output[dieNumber++] = die.getCurrentFace();
       }
       return output;
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
       nextFirstPlayer();

       currentPot = 0;


    }

    public void nextFirstPlayer() {

       if (firstPlayerPointer + 1 >= gamePlayerOrder.size()) {
           firstPlayerPointer = 0;
       }
       else {
           firstPlayerPointer++;
       }
    }





}
