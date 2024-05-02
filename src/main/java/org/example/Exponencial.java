package org.example;

public class Exponencial implements Distribucion {
    private double u;
    public Exponencial (double u){
        this.u = u;
    }
    public double getVariable(Randomizer randomizer){
        return (-(this.u)*Math.log(1-randomizer.nextDouble()));
    }
}
