package org.example;

import java.util.ArrayList;

public class Empirica_Discreta implements Distribucion{
    private ArrayList<Double> probabilidadAcumulada;
    private ArrayList<Double> valores;

    public Empirica_Discreta(ArrayList<Double> probabilidadAcumulada, ArrayList<Double> valores){
        this.probabilidadAcumulada = probabilidadAcumulada;
        this.valores = valores;
    }
    @Override
    public double getVariable(Randomizer randomizer) {
        double random = randomizer.nextDouble();
        int i = 0;

        while (random >= probabilidadAcumulada.get(i)){
            i++;
        }

        return valores.get(i);
    }
}
