package pyAscensoresV1.src;

import java.util.*;

public class Ascensor {
    private String id;
    private int plantaActual;
    private List<Persona> personas;
    private Queue<Llamada> llamadas;
    private static final int CAPACIDAD = 6;

    public Ascensor(String id) {
        this.id = id;
        this.plantaActual = 0;
        this.personas = new ArrayList<>();
        this.llamadas = new LinkedList<>();
    }

    public void atenderLlamada(int plantaOrigen, Persona persona) {
        llamadas.add(new Llamada(plantaOrigen, persona));
    }

    public void mover() {
        if (!llamadas.isEmpty()) {
            Llamada llamada = llamadas.peek();
            if (plantaActual != llamada.plantaOrigen) {
                plantaActual += Integer.compare(llamada.plantaOrigen, plantaActual);
            } else {
                if (personas.size() < CAPACIDAD) {
                    personas.add(llamada.persona);
                    llamada.persona.marcarAtendido();
                    llamadas.poll();
                }
            }
        }

        if (!personas.isEmpty()) {
            List<Persona> personasADejar = new ArrayList<>();
            for (Persona persona : personas) {
                if (persona.getPlantaDestino() == plantaActual) {
                    personasADejar.add(persona);
                }
            }
            personas.removeAll(personasADejar);
        }
    }

    public void imprimirEstado() {
        System.out.println("Ascensor " + id + " en planta " + plantaActual + ", personas: " + personas.size());
    }

    private class Llamada {
        int plantaOrigen;
        Persona persona;

        Llamada(int planta, Persona persona) {
            this.plantaOrigen = planta;
            this.persona = persona;
        }
    }
}
