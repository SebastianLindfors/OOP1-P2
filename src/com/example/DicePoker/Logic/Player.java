package com.example.DicePoker.Logic;

import java.util.ArrayList;

public class Player {
    private String name;
    private int marker;
    private ArrayList<Die> dice = new ArrayList();
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

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);

    }

    //Rolls the dice if the dice value is true
    public void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5){
            if(die_1) {
                die1.roll();
            }
            if(die_2) {
                die2.roll();
            }
            if(die_3) {
                die3.roll();
            }
            if(die_4) {
                die4.roll();
            }
            if(die_5) {
                die5.roll();
            }

    }

    public boolean isHuman() { return isHuman; }

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

    public boolean payMarkerToPot(int payment) {

        if (this.marker - payment > 0) {
            marker -= payment;
            return true;
        }
        else {
            return false;
        }

    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    public int[] getDieValues() {

        int[] output = new int[5];
        int i = 0;
        for (Die die : dice) {
            output[i++] = die.getCurrentFace();
        }

        return output;
    }

    public void payMarkerFromPot(int pot) {

        this.marker += pot;

    }
}
