package org.example;

public class TiempoEntreArribos_1 extends nextRand_A{
    public TiempoEntreArribos_1(Randomizer randomizer) {
        super(randomizer);
    }

    @Override
    public double nextRandA(double clockInminente) {
        Randomizer randomizer = new Randomizer1();
        double random = randomizer.nextDouble();
        double value;

        if(random < 0.35){
            value = 10;
        } else if(random >= 0.35 && random < 0.80) {
            value = 15;
        }else{
            value = 17;
        }
        return value;
    }
}
