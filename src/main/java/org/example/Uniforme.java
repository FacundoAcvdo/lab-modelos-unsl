package org.example;

public class Uniforme {
    public double getVariable(Randomizer randomizer, double a, double b){
        double random = randomizer.nextDouble();

        return a + (b-a)*random;
    }
}
