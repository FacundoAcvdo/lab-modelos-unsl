package org.example;
public class Engine_2 extends Engine{
    double clock;

    public Engine_2(double tiempoSimulacion, int nroServidores, int nroColas,  nextRand_S tiempoSalidas, nextRand_A tiempoArribos, Asociador asociador) {
        super(tiempoSimulacion, nroServidores, nroColas, tiempoSalidas, tiempoArribos, asociador);
    }


    @Override
    public void run() {
        Evento_A primerArribo = new Evento_A(0, new Entidad(1), new CriterioEtapa1(), tiempoSalidas, tiempoArribos);
        fel.insert(primerArribo);

        while (fel.getInminentClock() <= tiempoSimulacion) {
            Evento evt = fel.getInminent();
            evt.planificate(fel, stats, cola, servers, asociados);
        }

        System.out.println(stats.toString(servers, tiempoSimulacion));
    }
}