package org.example;

public class Uniforme implements Distribucion{
    private double a;
    private double b;

    public Uniforme(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getVariable(Randomizer randomizer){
        double random = randomizer.nextDouble();

        return this.a + (this.b-this.a)*random;
    }
}
