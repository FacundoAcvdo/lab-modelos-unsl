package org.example.generadores;

import org.example.distribuciones.Distribucion;
import org.example.distribuciones.Empirica_Discreta;
import org.example.randomizer.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;

public class TiempoDeSalidas_1 extends NextRand_S {
    public TiempoDeSalidas_1(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandS(double clockInminente) {
        ArrayList<Double> probabilidadAcumulada = new ArrayList<>(Arrays.asList(0.38,0.7,0.8,1.0));
        ArrayList<Double> values = new ArrayList<>(Arrays.asList(8.0,10.0,13.0,15.0));

        Distribucion empirica_discreta = new Empirica_Discreta(probabilidadAcumulada, values);

        return empirica_discreta.getVariable(randomizer);
    }
}
