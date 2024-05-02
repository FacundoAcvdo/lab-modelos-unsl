package org.example;
public abstract class NextRand_A {
    Randomizer randomizer;

    public NextRand_A(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    public abstract double nextRandA(double clockInminente);
}
