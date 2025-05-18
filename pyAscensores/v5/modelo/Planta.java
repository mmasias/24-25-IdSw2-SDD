package modelo;

import java.util.LinkedList;
import java.util.Queue;

public class Planta {
    private final int nivel;
    private final Queue<Persona> personasEsperando = new LinkedList<>();

    public Planta(int nivel) {
        this.nivel = nivel;
    }

    public void agregarPersona(Persona persona) {
        personasEsperando.add(persona);
    }

    public int cantidadEsperando() {
        return personasEsperando.size();
    }

    public Queue<Persona> getEsperando() {
        return personasEsperando;
    }

    public int getNivel() {
        return nivel;
    }

    public void añadirPersonaEsperando(Persona persona) {
      personasEsperando.add(persona);
    }
}