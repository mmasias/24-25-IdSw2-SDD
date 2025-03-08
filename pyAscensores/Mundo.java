package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Mundo {
     private Universidad universidad;
    private List<Persona> personas;

    public Mundo() {
        this.universidad = new Universidad();
        this.personas = new ArrayList<>();
    }

    public void generarPersona(String nombre, int plantaDestino, int tiempoEnPlanta) {
        Persona p = new Persona(nombre, plantaDestino, tiempoEnPlanta);
        personas.add(p);
        universidad.acogerPersona(p);
    }

    public void imprimirEstado() {
        universidad.imprimirEstado();
    }
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.generarPersona("Paco", 2, 5);
        mundo.generarPersona("Laura", 1, 3);
        mundo.generarPersona("Carlos", 3, 4);
        mundo.imprimirEstado();
    }
}
