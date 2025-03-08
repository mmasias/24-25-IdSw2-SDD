package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Planta {
    private int numero;
    private List<Persona> esperando;
    private List<Persona> enPlanta;

    public Planta(int numero) {
        this.numero = numero;
        this.esperando = new ArrayList<>();
        this.enPlanta = new ArrayList<>();
    }

    public String getPersonasEsperando() {
        return String.valueOf(esperando.size());  // Muestra el número de personas esperando
    }

    public String getPersonasEnPlanta() {
        return String.valueOf(enPlanta.size());  // Muestra el número de personas en la planta
    }

    public List<Persona> getEsperando() {
        return esperando;
    }

    public List<Persona> getEnPlanta() {
        return enPlanta;
    }
}
