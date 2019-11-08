package com.example.DicePoker.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    ArrayList<Player> playersInGame = new ArrayList<>(); //Stores all players in the game.

    int currentPlayer;
    int firstPlayer;

    public Controller(ArrayList<Player> playersInGame) {

        this.playersInGame = playersInGame;

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



//    Player player1 = new Player("Kamil",10, true);
//    Player player2 = new Player("Nusret",10, true );
//    Player player3 = new Player("Heidi",10,true );
//    Player player4 = new Player("Sebastian",10, true);





}
