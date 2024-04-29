package org.example;

public class TiempoEntreArribos_2 extends nextRand_A{
    public TiempoEntreArribos_2(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandA(double clockInminente) {
        Exponencial exponencial = new Exponencial();
        double clock = (clockInminente % 1440) / 60;


        if (clock >= 9 && clock <= 13.0 || clock >= 20 && clock <= 23) {
            return exponencial.getVariable(randomizer, 9);
        } else {
            return exponencial.getVariable(randomizer, 15);
        }
    }
}
