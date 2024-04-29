package org.example;

public class Normal {
    public double getVariable(Randomizer randomizer, double u, double d){
        double sumatoria = 0;
        double z = 0;

        for (int i = 0; i < 36; i++) {
            sumatoria = sumatoria + randomizer.nextDouble();
        }

        z = (sumatoria-18)/Math.sqrt(3.0);

        return (z * d + u);
    }
}
