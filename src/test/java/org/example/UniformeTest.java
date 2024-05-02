package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniformeTest {

    @Test
    public void test_1(){
        Distribucion uniforme = new Uniforme(10, 25);
        Randomizer randomizer = new Randomizer_Pruebas();

        assertAll(
                ()-> assertEquals(10.015,uniforme.getVariable(randomizer)),
                ()-> assertEquals(17.5,uniforme.getVariable(randomizer)),
                ()-> assertEquals(24.985,uniforme.getVariable(randomizer))
        );
    }

    @Test
    public void test_2(){
        Distribucion uniforme = new Uniforme(10, 10);
        Randomizer randomizer = new Randomizer_Pruebas();

        assertAll(
                ()-> assertEquals(10,uniforme.getVariable(randomizer)),
                ()-> assertEquals(10,uniforme.getVariable(randomizer)),
                ()-> assertEquals(10,uniforme.getVariable(randomizer))
        );
    }

    @Test
    public void test_3(){
        Distribucion uniforme = new Uniforme(0, 10000);
        Randomizer randomizer = new Randomizer_Pruebas();

        assertAll(
                ()-> assertEquals(10,uniforme.getVariable(randomizer)),
                ()-> assertEquals(5000,uniforme.getVariable(randomizer)),
                ()-> assertEquals(9990,uniforme.getVariable(randomizer))
        );
    }

}