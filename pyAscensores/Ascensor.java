package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    private int capacidadMaxima;
    private int plantaActual;
    private List<Persona> personas;
    private int estado; // 0: detenido, 1: subiendo, -1: bajando

    public Ascensor(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.plantaActual = 0;
        this.personas = new ArrayList<Persona>();
        this.estado = 0;
    }

    public boolean puedeRecogerPersona(Persona p) {
        return personas.size() < capacidadMaxima;
    }

    public void recogerPersona(Persona persona) {
        if(puedeRecogerPersona(persona)) {
            personas.add(persona);
        }
    }

    public int getPlantaActual() {
        return plantaActual;
    }

    public String representacion(int planta) {
        if (planta == plantaActual) {
            return estado == 1 ? "[^" + personas.size() + "^]" : "[v" + personas.size() + "v]";
        } else {
            return "| |";
        }
    }
    
}
