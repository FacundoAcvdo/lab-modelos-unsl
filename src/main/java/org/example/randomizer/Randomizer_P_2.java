package org.example.randomizer;

import java.util.ArrayList;
import java.util.Arrays;

public class Randomizer_P_2 implements Randomizer {
    private ArrayList<Double> randoms;

    public Randomizer_P_2() {
        this.randoms = new ArrayList<>(Arrays.asList(0.001,0.5,0.999));
    }
    public double nextDouble() {
        double retorno = randoms.getFirst();
        randoms.removeFirst();
        return retorno;
    }
}
