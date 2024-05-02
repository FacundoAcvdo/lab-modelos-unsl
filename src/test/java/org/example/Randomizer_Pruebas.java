package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Randomizer_Pruebas implements Randomizer{
    private ArrayList<Double> randoms;

    public Randomizer_Pruebas() {
        this.randoms = new ArrayList<>(Arrays.asList(0.001,0.5,0.999));
    }
    public double nextDouble() {
        double retorno = randoms.getFirst();
        randoms.removeFirst();
        return retorno;
    }
}
