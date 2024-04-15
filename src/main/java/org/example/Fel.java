package org.example;

import java.util.List;

public class Fel {
    private List<Evento> fel;
    public Fel(List<Evento> fel) {
        this.fel = fel;
    }

    public void insert(Evento evt){
        int i=0;

        while (i < fel.size() && ((evt.getClock() > fel.get(i).getClock()) || (evt.getClock() == fel.get(i).getClock() && fel.get(i).getPrioridad() < evt.getPrioridad()))) {
            i++;
        }

        fel.add(i, evt);
    }

    public double getInminentClock(){
        return fel.getFirst().getClock();
    }

    public Evento getInminent(){
        Evento evt = fel.getFirst();
        fel.removeFirst();

        return evt;
    }

    @Override
    public String toString() {
        return ""+fel;
    }
}
