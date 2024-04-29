package org.example;

public class TiempoDeSalidas_1 extends nextRand_S{
    public TiempoDeSalidas_1(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandS(double clockInminente) {
        Randomizer randomizer = new Randomizer1();
        double random = randomizer.nextDouble();
        double value;

        if(random < 0.38){
            value = 8.0;
        }
        else if (random > 0.38 && random <= 0.70) {
            value = 10;
        } else if (random > 0.70 && random <= 0.80) {
            value = 13;
        }else{
            value = 15;
        }

        return value;
    }
}
