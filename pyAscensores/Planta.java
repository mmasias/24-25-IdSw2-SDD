package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Planta {
    private List<Persona> esperando;
    private List<Persona> enPlanta;

    public Planta(int numero) {
        this.esperando = new ArrayList<>();
        this.enPlanta = new ArrayList<>();
    }

    public String getPersonasEsperando() {
        return String.valueOf(esperando.size());
    }

    public String getPersonasEnPlanta() {
        return String.valueOf(enPlanta.size());
    }

    public List<Persona> getEsperando() {
        return new ArrayList<>(esperando);
    }

    public void agregarPersonaEsperando(Persona persona) {
        esperando.add(persona);
    }

    public void removerPersonaEsperando(Persona persona) {
        esperando.remove(persona);
    }

    public void agregarPersonaEnPlanta(Persona persona) {
        enPlanta.add(persona);
    }

    public void removerPersonaEnPlanta(Persona persona) {
        enPlanta.remove(persona);
    }

    public void imprimirPersonasEnPlanta() {
        for (Persona persona : enPlanta) {
            System.out.println(persona);
        }
    }

    public int contarPersonasEnPlanta() {
        return enPlanta.size();
    }
}