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
        int[] output = new int[4];
        switch (mostRolled) {
            case 5: //This means person has a Five of a Kind hand
                    output[0] = 7;
                    output [1] = mostRolledValue;
                    break;
            case 4: //This means the player has a Four of a kind.
                    output[0] = 6;
                    output[1] = mostRolledValue;

                    break;
            case 3: // This means the player has either Three of a Kind of a Full House.
                break;
            case 2:  //This means the player has a pair or two pairs.
                break;
            case 1: //This means the player has nothing or a straight.
                break;

        }







    }



//    Player player1 = new Player("Kamil",10, true);
//    Player player2 = new Player("Nusret",10, true );
//    Player player3 = new Player("Heidi",10,true );
//    Player player4 = new Player("Sebastian",10, true);





}
