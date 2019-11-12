
package com.example.DicePoker.Testing;

import com.example.DicePoker.Logic.Controller;
import com.example.DicePoker.Logic.Player;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class ControllerTest {

    @Test
    public void determineHandStrengthTest() {

        //Pair test 1
        int[] inputHand = {1,1,3,4,5};
        int[] expected  = {1,1,5,4,3};
        int[] actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Pair test 2.
        inputHand   = new int[]{2,2,3,4,5};
        expected    = new int[]{1,2,5,4,3};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Pair test 3.
        inputHand   = new int[]{5,5,1,4,6};
        expected    = new int[]{1,5,6,4,1};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Two Pair test 1.
        inputHand   = new int[]{5,5,4,4,6};
        expected    = new int[]{2,5,4,6,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Two Pair test 2.
        inputHand   = new int[]{1,1,2,2,6};
        expected    = new int[]{2,2,1,6,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Two Pair test 3.
        inputHand   = new int[]{1,1,3,3,2};
        expected    = new int[]{2,3,1,2,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Three of a kind test 1.
        inputHand   = new int[]{1,1,1,3,2};
        expected    = new int[]{3,1,3,2,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Three of a kind test 2.
        inputHand   = new int[]{6,6,6,3,1};
        expected    = new int[]{3,6,3,1,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Straight test 1.
        inputHand   = new int[]{1,2,3,4,5};
        expected    = new int[]{4,5,0,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Straight test 2.
        inputHand   = new int[]{2,3,4,5,6};
        expected    = new int[]{4,6,0,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Full House test 1.
        inputHand   = new int[]{2,2,2,5,5};
        expected    = new int[]{5,2,5,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Full House 2.
        inputHand   = new int[]{6,6,6,5,5};
        expected    = new int[]{5,6,5,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Full House 3.
        inputHand   = new int[]{3,3,3,4,4};
        expected    = new int[]{5,3,4,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Four of a Kind Test 1.
        inputHand   = new int[]{6,6,6,6,1};
        expected    = new int[]{6,6,1,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Four of a Kind Test 2.
        inputHand   = new int[]{4,4,3,4,4};
        expected    = new int[]{6,4,3,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Four of a Kind Test 3.
        inputHand   = new int[]{3,3,3,3,4};
        expected    = new int[]{6,3,4,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Five of a kind test 1.
        inputHand   = new int[]{1,1,1,1,1};
        expected    = new int[]{7,1,0,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Five of a kind test 2.
        inputHand   = new int[]{5,5,5,5,5};
        expected    = new int[]{7,5,0,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);

        //Five of a kind test 3.
        inputHand   = new int[]{3,3,3,3,3};
        expected    = new int[]{7,3,0,0,0};
        actual = Controller.determineHandStrength(inputHand);
        System.out.println(handStrengthOutput(expected, actual));
        Assert.assertArrayEquals( expected, actual);
}

    @Test
    public void constructorTesting() {

        ArrayList<Player> lop = new ArrayList<>();
        lop.add(new Player("Sebbe",0,true));
        lop.add(new Player("Nusret",0,true));

        Controller testGame = new Controller(lop);
        Assert.assertArrayEquals(testGame.getPlayerOrder(), new int[]{1,2});

        lop.add(new Player("Kamil",0,true));

        testGame = new Controller(lop);
        Assert.assertArrayEquals(testGame.getPlayerOrder(), new int[]{1,2,3});

        lop.add(new Player("Heidi",0,true));

        testGame = new Controller(lop);
        Assert.assertArrayEquals(testGame.getPlayerOrder(), new int[]{1,2,3,4});


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