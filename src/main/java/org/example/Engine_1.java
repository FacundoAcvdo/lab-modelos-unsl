package org.example;

public class Engine_1 extends Engine implements nextRand_A, nextRand_S{
    private double nextRandA;
    private double nextRandS;
    public Engine_1(int nroServidores, double tiempoSimulacion, int nroColas, Randomizer randomizer) {
        super(nroServidores, tiempoSimulacion, nroColas, randomizer);

        this.nextRandA = 0.0;
        this.nextRandS = 0.0;
    }



    @Override
    public void run() {
        Evento_A primerArribo = new Evento_A(0, new Entidad(1), new CriterioEtapa1());
        fel.insert(primerArribo);

        while(fel.getInminentClock()<= tiempoSimulacion ){
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
        double value;

        if(random < 0.38){
            value = 8.0;
        }
        else if (random > 0.38 && random <= 0.70) {
            value = 10;
        } else if (random > 0.70 && random <= 0.80) {
            value = 13;
        }else{
            value = 15;
        }

        return value;
    }

    @Override
    public double nextRandA() {
        double random = randomizer.nextDouble();
        double value;

        if(random < 0.35){
            value = 10;
        } else if(random >= 0.35 && random < 0.80) {
            value = 15;
        }else{
            value = 17;
        }
        return value;
    }
}
