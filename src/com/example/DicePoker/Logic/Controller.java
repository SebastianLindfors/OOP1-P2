package com.example.DicePoker.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    final int STARTING_CHIPS = 100;



    Map<Integer, Player> playerByNumber = new HashMap<>();

    ArrayList<Player> playersAtStart = new ArrayList<>(); //Stores all players in the game at the start.
    ArrayList<Player> playersInGame = new ArrayList<>();
    ArrayList<Player> playersInRound = new ArrayList<>();

    int currentAnte;
    int currentPot;
    int currentRound;

    int currentPlayer = 0;
    int firstPlayer = 0;

    int[] playerOrder;

    public Controller(ArrayList<Player> listOfPlayers) {
        int playerNumbers = 1;
        playerOrder = new int[listOfPlayers.size()];
        for (Player player : listOfPlayers) {
            playerByNumber.put(playerNumbers++, player);
            playerOrder[playerNumbers - 2] = playerNumbers - 1;
        }

    }

    public void startGame() {

        for (Player player : playersAtStart) {
            player.setMarker(STARTING_CHIPS);
            playersInGame.add(player);
        }

        firstPlayer = 0;
        currentRound = 0;
        mainGameLoop();
    }


    private void mainGameLoop() {

        while (playersInGame.size() > 1) {

            currentRound++; //Increase the round count TODO More round relared code here.

            currentPot = 0;
            anteUp();

            for (Player player : playersInGame) {
                player.rollAllDice(); //TODO Update graphics here
            }

                startBetting();

            for (Player player : playersInRound) {
                boolean[] rerollDie = new boolean[5];
                //Todo Get choice of players die
                player.rollSomeDie(rerollDie[0],rerollDie[1],rerollDie[2],rerollDie[3], rerollDie[4]);
            }



        }

    }

    private void anteUp() {

        currentPlayer = firstPlayer;

        do  {
            playerByNumber.get(currentPlayer).payMarker(currentAnte);
            currentPot += currentAnte;
            currentPlayer = nextPlayer();
        } while (currentPlayer != firstPlayer);


    }

    private void startBetting() {

        int highestBet = 0;
        int[] playerBets = new int[playersInGame.size()];

        currentPlayer = firstPlayer;
        while (playerBets[currentPlayer] != highestBet) {

            int playerChoice = -1;

            if (playerBets[currentPlayer] == -1) {
                currentPlayer = nextPlayer();
                continue;
            }
            if (playerByNumber.get(currentPlayer).isHuman) {
                playerChoice = 1;//TODO Get choice from GUI-Class.  0: Bet, 1: Call, 2: Fold
            }
            else {
                playerChoice = 2;
                //TODO Code AI behaviour
            }
            if (playerChoice == 1) { //Player is betting
                int betAmount = 1; //Todo Get amount from GUI
            }
            else if (playerChoice == 2) { //Player is calling
                int callingCost = highestBet - playerBets[currentPlayer];
                playerByNumber.get(currentPlayer).payMarker(callingCost);
                playerBets[currentPlayer] += callingCost;
                currentPot += callingCost;
            }
            else { //Player is folding
                playerBets[currentPlayer] = -1;
            }
        }
        for (Player player : playersInGame) {
            if (!player.isFolded()) {
                playersInRound.add(player);
            }
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


    private int nextPlayer() {
        if (currentPlayer++ == playersInGame.size()) return 0;
        else return currentPlayer++;
    }

    public int[] getPlayerOrder() {
        return this.playerOrder;
    }

}
