package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class RandomizerPruebas implements Randomizer{
    private ArrayList<Double> randoms;

    public RandomizerPruebas() {
        this.randoms = new ArrayList<Double>(Arrays.asList(0.2,0.98,0.5,0.5,0.2,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.1,0.1,0.1,0.1));
    }
    public double nextDouble() {
        double retorno = randoms.getFirst();
        randoms.removeFirst();
        return retorno;
    }
}
