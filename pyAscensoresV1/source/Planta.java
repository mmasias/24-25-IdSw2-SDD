package source;

import java.util.ArrayList;
import java.util.List;

public class Planta {
    private int numero;
    private List<Persona> personasEnPlanta;
    private List<Persona> personasEsperando;

    public Planta(int numero) {
        this.numero = numero;
        this.personasEnPlanta = new ArrayList<>();
        this.personasEsperando = new ArrayList<>();
    }

    public int getNumero() { return numero; }

    public void personaLlega(Persona persona) { personasEsperando.add(persona); }

    public void personaEsperaAscensor(Persona persona) { personasEsperando.add(persona); }

    public void personaSubeAlAscensor(Persona persona) {
        personasEsperando.remove(persona);
        personasEnPlanta.remove(persona);
    }

    public void personaLlegaADestino(Persona persona) { personasEnPlanta.add(persona); }

    public int getCantidadEsperando() { return personasEsperando.size(); }

    public int getCantidadEnPlanta() { return personasEnPlanta.size(); }

    public List<Persona> getPersonasEsperando() { return personasEsperando; }
}
