package org.example;
public abstract class nextRand_A {
    Randomizer randomizer;

    public nextRand_A(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    public abstract double nextRandA(double clockInminente);
}
