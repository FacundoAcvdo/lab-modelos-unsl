package org.example;

public class Exponencial {
    public double getVariable(Randomizer randomizer, double u){
        return (-u)*Math.log(1-randomizer.nextDouble());
    }
}
