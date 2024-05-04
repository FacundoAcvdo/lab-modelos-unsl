package org.example.generadores;

import org.example.distribuciones.Distribucion;
import org.example.distribuciones.Empirica_Discreta;
import org.example.randomizer.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;

public class TiempoEntreArribos_1 extends NextRand_A {
    public TiempoEntreArribos_1(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandA(double clockInminente) {
        ArrayList<Double> probabilidadAcumulada = new ArrayList<>(Arrays.asList(0.35,0.8,1.0));
        ArrayList<Double> values = new ArrayList<>(Arrays.asList(10.0,15.0,17.0));

        Distribucion empirica_discreta = new Empirica_Discreta(probabilidadAcumulada, values);

        return empirica_discreta.getVariable(randomizer);
    }
}
