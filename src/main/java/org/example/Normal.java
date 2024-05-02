package org.example;

public class Normal implements Distribucion{
    private double u;
    private double d;

    public Normal(double u, double d){
        this.u=u;
        this.d=d;
    }
    public double getVariable(Randomizer randomizer){
        double sumatoria = 0;
        double z = 0;

        for (int i = 0; i < 36; i++) {
            sumatoria = sumatoria + randomizer.nextDouble();
        }

        z = (sumatoria-18)/Math.sqrt(3.0);

        return (z * this.d + this.u);
    }
}
