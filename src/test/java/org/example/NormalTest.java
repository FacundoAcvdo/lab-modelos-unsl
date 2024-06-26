package org.example;

import org.example.distribuciones.Distribucion;
import org.example.distribuciones.Normal;
import org.example.randomizer.Randomizer;
import org.example.randomizer.Randomizer_P_1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalTest {
    private static final double DELTA = 0.5d;

    @Test
    public void test_1(){
        Distribucion normal = new Normal(5, 1);
        Randomizer randomizer = new Randomizer_P_1();

        assertAll(
                ()-> assertEquals(4.54117, normal.getVariable(randomizer), DELTA)
        );

    }

    @Test
    public void test_2(){
        Distribucion normal = new Normal(16, 5);

        assertAll(
                ()-> assertEquals(13.70586, normal.getVariable(new Randomizer_P_1()), DELTA)
        );

    }

}