package org.example;

import java.util.ArrayList;

public class RandomizerPruebas implements Randomizer{
    private ArrayList<Double> randoms;

    public RandomizerPruebas() {
        this.randoms = new ArrayList<Double>();
        this.randoms.add(0.2);
        this.randoms.add(0.98);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.2);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.5);
        this.randoms.add(0.1);
        this.randoms.add(0.1);
        this.randoms.add(0.1);
        this.randoms.add(0.1);
    }
    public double nextDouble() {
        double retorno = randoms.getFirst();
        randoms.removeFirst();
        return retorno;
    }
}
