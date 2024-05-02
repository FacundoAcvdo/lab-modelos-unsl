package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalTest {
    private static final double DELTA = 0.5d;

    @Test
    public void test_1(){
        Distribucion normal = new Normal(5, 1);
        Randomizer randomizer = new Randomizer_P();

        assertAll(
                ()-> assertEquals(4.54117, normal.getVariable(randomizer), DELTA)
        );

    }

    @Test
    public void test_2(){
        Distribucion normal = new Normal(16, 5);

        assertAll(
                ()-> assertEquals(13.70586, normal.getVariable(new Randomizer_P()), DELTA)
        );

    }

}