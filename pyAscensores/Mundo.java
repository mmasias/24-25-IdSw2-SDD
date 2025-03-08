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
}
