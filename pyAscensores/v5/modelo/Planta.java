package modelo;

import java.util.LinkedList;
import java.util.Queue;

public class Planta implements IPlanta {
    private final int nivel;
    private final Queue<IPersona> personasEsperando = new LinkedList<>();

    public Planta(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getNivel() {
        return nivel;
    }

    @Override
    public void agregarPersonaEsperando(IPersona persona) {
        personasEsperando.add(persona);
    }
    
    @Override
    public IPersona sacarPersonaEsperando() {
        return personasEsperando.poll();
    }
    
    @Override
    public boolean tienePersonasEsperando() {
        return !personasEsperando.isEmpty();
    }

    @Override
    public int getCantidadEsperando() {
        return personasEsperando.size();
    }

    @Override
    public Queue<IPersona> getPersonasEsperando() {
        return personasEsperando;
    }
}