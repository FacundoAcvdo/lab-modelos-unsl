package org.example;

public class TiempoEntreArribos_2 extends NextRand_A {
    public TiempoEntreArribos_2(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandA(double clockInminente) {
        double clock = (clockInminente % 1440) / 60;
        Exponencial exponencial;

        if (clock >= 9 && clock <= 13.0 || clock >= 20 && clock <= 23) {
            exponencial = new Exponencial(9);
        } else {
            exponencial = new Exponencial(15);
        }
        return exponencial.getVariable(randomizer);
    }
}
