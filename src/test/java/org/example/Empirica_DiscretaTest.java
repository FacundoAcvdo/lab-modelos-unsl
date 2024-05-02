package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Empirica_DiscretaTest {

    @Test
    public void test_1(){
        ArrayList<Double> probabilidadAcumulada = new ArrayList<>(Arrays.asList(0.35,0.8,1.0));
        ArrayList<Double> values = new ArrayList<>(Arrays.asList(10.0,15.0,17.0));

        Distribucion empirica = new Empirica_Discreta(probabilidadAcumulada, values);

        Randomizer randomizer = new Randomizer_Pruebas();

        assertAll(
                ()-> assertEquals(10,empirica.getVariable(randomizer)),
                ()-> assertEquals(15,empirica.getVariable(randomizer)),
                ()-> assertEquals(17,empirica.getVariable(randomizer))
        );
    }

}