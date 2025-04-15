package pyAscensoresV1.src;

import java.util.*;

public class Planta {
    private int numero;
    private List<Persona> personasPresentes;

    public Planta(int numero) {
        this.numero = numero;
        this.personasPresentes = new ArrayList<>();
    }

    public void personaLlega(Persona p) {
        this.numero++;
        personasPresentes.add(p);
    }

    public void personaSale(Persona p) {
        this.numero--;
        personasPresentes.remove(p);
    }

    public void imprimirEstado() {
        System.out.println("Planta " + numero + " con " + personasPresentes.size() + " personas.");
    }

    public int getNumero() {
        return numero;
    }
}
