package com.example.DicePoker.Logic;

import java.util.ArrayList;

public class Player {
    private String name;
    private int marker;
    private int[] dice = new int[5];
    private ArrayList dices = new ArrayList();
    Die die1,die2, die3, die4, die5;
    boolean isHuman;
    boolean folded = false, bet = false;

    public Player(String name, int marker, boolean isHuman) {
        this.name = name;
        this.marker = marker;
        this.isHuman = isHuman;

        die1 = new Die(6);
        die2 = new Die(6);
        die3 = new Die(6);
        die4 = new Die(6);
        die5 = new Die(6);
    }

    //Rolls the dice if the dice value is true
    public void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5){
            if(die_1) {
                die1.roll();
                dice[0] = die1.getCurrentFace();
                dices.add(dice[0]);
            }
            if(die_2) {
                die2.roll();
                dice[1] = die2.getCurrentFace();
                dices.add(dice[1]);
            }
            if(die_3) {
                die3.roll();
                dice[2] = die3.getCurrentFace();
                dices.add(dice[2]);
            }

            if(die_4) {
                die4.roll();
                dice[3] = die4.getCurrentFace();
                dices.add(dice[3]);
            }
            if(die_5) {
                die5.roll();
                dice[4] = die5.getCurrentFace();
                dices.add(dice[4]);
            }

    }

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    public boolean isBet() {
        return bet;
    }

    public void setBet(boolean bet) {
        this.bet = bet;
    }

    public void rollAllDice() {
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();

        dice[0] = die1.getCurrentFace();
        dice[1] = die2.getCurrentFace();
        dice[2] = die3.getCurrentFace();
        dice[3] = die4.getCurrentFace();
        dice[4] = die5.getCurrentFace();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarker() {
        return marker;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    public int[] getDice() {
        return dice;
    }

    public void setDice(int[] dice) {
        this.dice = dice;
    }
}
