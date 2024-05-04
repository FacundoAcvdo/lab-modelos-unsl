package org.example;

import org.example.distribuciones.Distribucion;
import org.example.distribuciones.Exponencial;
import org.example.randomizer.Randomizer;
import org.example.randomizer.Randomizer_P_2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExponencialTest {
    private static final double DELTA = 0.5d;

    @Test
    public void test_1(){
        Distribucion exponencial = new Exponencial(15);
        Randomizer randomizer = new Randomizer_P_2();

        assertAll(
                ()-> assertEquals(0.015, exponencial.getVariable(randomizer), DELTA),
                ()-> assertEquals(10.39720, exponencial.getVariable(randomizer), DELTA),
                ()-> assertEquals(103.61632, exponencial.getVariable(randomizer), DELTA)
        );
    }

    @Test
    public void test_2(){
        Distribucion exponencial = new Exponencial(0);
        Randomizer randomizer = new Randomizer_P_2();

        assertAll(
                ()-> assertEquals(0, exponencial.getVariable(randomizer)),
                ()-> assertEquals(0, exponencial.getVariable(randomizer)),
                ()-> assertEquals(0, exponencial.getVariable(randomizer))
        );
    }
}