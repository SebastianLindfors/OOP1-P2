package com.example.DicePoker.Testing;

import com.example.DicePoker.Logic.Controller;
import org.junit.Assert;
import org.junit.Test;



public class ControllerTest {

    @Test
    public void determineHandStrengthTest() {

        int[] inputHand = {1,2,3,4,5};
        int[] expected = {1,1,1,1,1,0};
        Assert.assertArrayEquals( expected, Controller.determineHandStrength(inputHand));

        inputHand = new int[]{1,1,1,1,1};
        expected = new int[]{5,0,0,0,0,0};
        Assert.assertArrayEquals( expected, Controller.determineHandStrength(inputHand));
}

}