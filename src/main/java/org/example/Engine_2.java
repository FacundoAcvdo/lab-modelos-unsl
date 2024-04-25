package org.example;
public class Engine_2 extends Engine implements nextRand_A, nextRand_S{
    private double nextRandA;
    private double nextRandS;

    public Engine_2(int nroServidores, double tiempoSimulacion, int nroColas, Randomizer randomizer) {
        super(nroServidores, tiempoSimulacion, nroColas, randomizer);

        this.nextRandA = 0.0;
        this.nextRandS = 0.0;
    }


    @Override
    public void run() {
        Evento_A primerArribo = new Evento_A(0, new Entidad(1), new CriterioEtapa1());
        fel.insert(primerArribo);

        while (fel.getInminentClock() <= tiempoSimulacion) {
            nextRandA = nextRandA();
            nextRandS = nextRandS();

            Evento evt = fel.getInminent();
            evt.planificate(fel, stats, cola, servers, nextRandA, nextRandS);
        }

        System.out.println(stats.toString(servers, tiempoSimulacion));
    }

    @Override
    public double nextRandS() {
        double random = this.randomizer.nextDouble();

        return 10 + (25-10)*random;
    }

    @Override
    public double nextRandA() {
        double clock = (fel.getInminentClock() % 1440) / 60;
        double random = this.randomizer.nextDouble();

        if (clock >= 9 && clock <= 13.0 || clock >= 20 && clock <= 23) {
            return (-15) * Math.log(1 - random);
        } else {
            return (-9) * Math.log(1 - random);
        }
    }
}