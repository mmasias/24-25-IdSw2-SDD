package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    private int capacidadMaxima;
    private int pisoActual;
    private List<Persona> personas;
    private int estado; // 0: detenido, 1: subiendo, -1: bajando

    public Ascensor(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.pisoActual = 0;
        this.personas = new ArrayList<Persona>();
        this.estado = 0;
    }

    public boolean puedeRecogerPersona() {
        return personas.size() < capacidadMaxima;
    }

    public void recogerPersona(Persona persona) {
        if(puedeRecogerPersona()) {
            personas.add(persona);
        }
    }

    public int getPlantaActual() {
        return pisoActual;
    }
}
