package org.example;

public class TiempoDeSalidas_2 extends nextRand_S{
    public TiempoDeSalidas_2(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandS(double clockInminente) {
        Uniforme uniforme = new Uniforme(10,25);

        return uniforme.getVariable(randomizer);
    }
}
