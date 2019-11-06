package com.example.DicePoker.Logic;

import java.util.ArrayList;

public class Player {
    private String name;
    private int marker;
    private int[] dice = new int[5];
    private ArrayList dices = new ArrayList();
    Die die1,die2, die3, die4, die5;

    //Rolls the dice if the dice value is true
    public void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5){
            if(die_1){
                die1.roll();
                dice[0] = die1.getCurrentFace();
                dices.add(dice[0]);
            } else if(die_2){
                die2.roll();
                dice[1] = die1.getCurrentFace();
                dices.add(dice[1]);
            } else if(die_3){
                die3.roll();
                dice[2] = die1.getCurrentFace();
                dices.add(dice[2]);
            } else if(die_4){
                die4.roll();
                dice[3] = die1.getCurrentFace();
                dices.add(dice[3]);
            } else if(die_5){
                die5.roll();
                dice[4] = die1.getCurrentFace();
                dices.add(dice[4]);
            }

    }

    public void rollAllDice(Die die){
        dices.add(die1.getCurrentFace());
        dices.add(die2.getCurrentFace());
        dices.add(die3.getCurrentFace());
        dices.add(die4.getCurrentFace());
        dices.add(die5.getCurrentFace());

        dice[0] = die1.getCurrentFace();
        dice[1] = die2.getCurrentFace();
        dice[2] = die3.getCurrentFace();
        dice[3] = die4.getCurrentFace();
        dice[4] = die5.getCurrentFace();
    }

    public Player(String name, int marker) {
        this.name = name;
        this.marker = marker;

        die1 = new Die(6);
        die2 = new Die(6);
        die3 = new Die(6);
        die4 = new Die(6);
        die5 = new Die(6);

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
