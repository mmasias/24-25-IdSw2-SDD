package pyAscensoresV1.src;

import java.util.*;

public class Ascensor {
    private String id;
    private int plantaActual;
    private List<Persona> personas;
    private Queue<Llamada> llamadas;
    private static final int CAPACIDAD = 6;
    private List<Planta> plantas;

    public Ascensor(String id) {
        this.id = id;
        this.plantaActual = 0;
        this.personas = new ArrayList<>();
        this.llamadas = new LinkedList<>();
    }

    public void asignarPlantas(List<Planta> plantas) {
        this.plantas = plantas;
    }

    public void atenderLlamada(int plantaOrigen, Persona persona) {
        llamadas.add(new Llamada(plantaOrigen, persona));
    }

    public void mover() {
        if (!personas.isEmpty()) {
            Persona destinoPersona = personas.get(0);
            int destino = destinoPersona.getPlantaDestino();
            if (plantaActual != destino) {
                plantaActual += Integer.compare(destino, plantaActual);
                return;
            }
            personas.remove(destinoPersona);
            Planta planta = buscarPlanta(plantaActual);
            if (planta != null) {
                planta.personaLlega(destinoPersona);
            }
            return;
        }

        if (!llamadas.isEmpty()) {
            Llamada llamada = llamadas.peek();
            if (plantaActual != llamada.plantaOrigen) {
                plantaActual += Integer.compare(llamada.plantaOrigen, plantaActual);
                return;
            }
            if (personas.size() < CAPACIDAD) {
                personas.add(llamada.persona);
                llamada.persona.marcarAtendido();
                llamadas.poll();
            }
        }
    }

    private Planta buscarPlanta(int numero) {
        for (Planta p : plantas) {
            if (p.getNumero() == numero) return p;
        }
        return null;
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

    public String getId() {
        return id;
    }

    public String getPlantaActual() {
        return String.valueOf(plantaActual);
    }
}
