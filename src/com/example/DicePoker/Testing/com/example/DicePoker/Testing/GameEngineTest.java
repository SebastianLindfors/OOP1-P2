
 package com.example.DicePoker.Testing;

import com.example.DicePoker.Logic.GameEngine;
import com.example.DicePoker.Logic.Player;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class GameEngineTest {

    @Test
    public void determineHandStrengthTest() {

        //Pair test 1
        int[] inputHand = {1,1,3,4,5};
        int[] expected  = {1,1,5,4,3};
        int[] actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Pair test 2.
        inputHand   = new int[]{2,2,3,4,5};
        expected    = new int[]{1,2,5,4,3};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Pair test 3.
        inputHand   = new int[]{5,5,1,4,6};
        expected    = new int[]{1,5,6,4,1};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Two Pair test 1.
        inputHand   = new int[]{5,5,4,4,6};
        expected    = new int[]{2,5,4,6,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Two Pair test 2.
        inputHand   = new int[]{1,1,2,2,6};
        expected    = new int[]{2,2,1,6,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Two Pair test 3.
        inputHand   = new int[]{1,1,3,3,2};
        expected    = new int[]{2,3,1,2,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Three of a kind test 1.
        inputHand   = new int[]{1,1,1,3,2};
        expected    = new int[]{3,1,3,2,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Three of a kind test 2.
        inputHand   = new int[]{6,6,6,3,1};
        expected    = new int[]{3,6,3,1,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Straight test 1.
        inputHand   = new int[]{1,2,3,4,5};
        expected    = new int[]{4,5,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Straight test 2.
        inputHand   = new int[]{2,3,4,5,6};
        expected    = new int[]{4,6,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Full House test 1.
        inputHand   = new int[]{2,2,2,5,5};
        expected    = new int[]{5,2,5,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Full House 2.
        inputHand   = new int[]{6,6,6,5,5};
        expected    = new int[]{5,6,5,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Full House 3.
        inputHand   = new int[]{3,3,3,4,4};
        expected    = new int[]{5,3,4,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Four of a Kind Test 1.
        inputHand   = new int[]{6,6,6,6,1};
        expected    = new int[]{6,6,1,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Four of a Kind Test 2.
        inputHand   = new int[]{4,4,3,4,4};
        expected    = new int[]{6,4,3,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Four of a Kind Test 3.
        inputHand   = new int[]{3,3,3,3,4};
        expected    = new int[]{6,3,4,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Five of a kind test 1.
        inputHand   = new int[]{1,1,1,1,1};
        expected    = new int[]{7,1,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Five of a kind test 2.
        inputHand   = new int[]{5,5,5,5,5};
        expected    = new int[]{7,5,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Five of a kind test 3.
        inputHand   = new int[]{3,3,3,3,3};
        expected    = new int[]{7,3,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Nothing test 1.
        inputHand   = new int[]{1,2,3,5,6};
        expected    = new int[]{0,4,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Nothing test 2.
        inputHand   = new int[]{1,2,3,4,6};
        expected    = new int[]{0,5,0,0,0};
        actual = GameEngine.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);
}

    @Test
    public void compareHandStrengthTest() {

        int[] hand1 = {7,6,0,0,0};
        int[] hand2 = {0,3,0,0,0};
        Boolean result = GameEngine.compareHandStrength(hand1,hand2);
        Boolean expected = true;
        System.out.println("Excpeted: " + expected + "\t| Actual: " + result);
        Assert.assertEquals(result, expected);

        hand2 = new int[]{7,6,0,0,0};
        hand1= new int[]{0,3,0,0,0};
        result = GameEngine.compareHandStrength(hand1,hand2);
        expected = false;
        System.out.println("Excpeted: " + expected + "\t| Actual: " + result);
        Assert.assertEquals(result, expected);

        hand1 = new int[]{0,4,0,0,0};
        hand2= new int[]{0,3,0,0,0};
        result = GameEngine.compareHandStrength(hand1,hand2);
        expected = false;
        System.out.println("Excpeted: " + expected + "\t| Actual: " + result);
        Assert.assertEquals(result, expected);

        hand1 = new int[]{7,6,0,0,0};
        hand2= new int[]{7,6,0,0,0};
        result = GameEngine.compareHandStrength(hand1,hand2);
        expected = false;
        System.out.println("Excpeted: " + expected + "\t| Actual: " + result);
        Assert.assertEquals(result, expected);

        hand1 = new int[]{6,6,4,0,0};
        hand2= new int[]{6,6,2,0,0};
        result = GameEngine.compareHandStrength(hand1,hand2);
        expected = true;
        System.out.println("Excpeted: " + expected + "\t| Actual: " + result);
        Assert.assertEquals(result, expected);

        hand1 = new int[]{7,6,0,0,0};
        hand2= new int[]{7,6,0,0,0};
        result = GameEngine.compareHandStrength(hand1,hand2);
        expected = false;
        System.out.println("Excpeted: " + expected + "\t| Actual: " + result);
        Assert.assertEquals(result, expected);


    }

    @Test
    public void sortHandStrengthTest() {

        int[] hand1 = {0,5,0,0,0}; // Rolled nothing, missing a 5.
        int[] hand2 = {1,2,6,5,4}; // Rolled a pair of ones, 6,5,4 kickers.
        int[] hand3 = {2,4,5,2,0}; // Rolled two pairs of 5s and 4s, with a 2 kicker.
        int[] hand4 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.

        int[][] testHand = {hand1,hand2,hand3,hand4};
        int[][] expectedHand = {hand4, hand3,hand2, hand1};
        int[][] sortedHand = GameEngine.sortHandStrength(testHand);
        Assert.assertArrayEquals(expectedHand, sortedHand);
    }

    @Test
    public void constructorTesting() {

        ArrayList<Player> lop = new ArrayList<>();
        lop.add(new Player("Sebbe",0,true));
        lop.add(new Player("Nusret",0,true));

        GameEngine testGame = new GameEngine(lop);
        //Assert.assertArrayEquals(testGame.getPlayerOrder(), new int[]{1,2});

        lop.add(new Player("Kamil",0,true));

        testGame = new GameEngine(lop);
        //Assert.assertArrayEquals(testGame.getPlayerOrder(), new int[]{1,2,3});

        lop.add(new Player("Heidi",0,true));

        testGame = new GameEngine(lop);
        //Assert.assertArrayEquals(testGame.getPlayerOrder(), new int[]{1,2,3,4});


    }



private String handStrengthOutput(int[] expected, int [] actual) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            string1.append(expected[i]);
            string1.append(" ");
            string2.append(actual[i]);
            string2.append(" ");
        }

        return ("Expected: " + string1.toString() + " Actual: " +string2.toString());
    }

}