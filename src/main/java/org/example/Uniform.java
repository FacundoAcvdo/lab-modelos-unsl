package org.example;

public class Uniform implements Distribution{
    @Override
    public double probability(double event) {
        return 0;
    }

    @Override
    public double event(double cumulativeProbability) {
        return 0;
    }
}
