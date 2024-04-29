package org.example;
public abstract class nextRand_S {
    Randomizer randomizer;
    public nextRand_S(Randomizer randomizer) {
        this.randomizer = randomizer;
    }
    public abstract double nextRandS(double clockInminente);
}
