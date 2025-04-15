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

    public void atenderLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    public void mover() {
        if (!personas.isEmpty()) {
            moverHaciaDestinoPersona();
            bajarPersonasEnPlantaActual();
            return;
        }

        if (!llamadas.isEmpty()) {
            atenderLlamadaPendiente();
        }
    }

    private void moverHaciaDestinoPersona() {
        Persona destinoPersona = personas.get(0);
        int destino = destinoPersona.getPlantaDestino();
        if (plantaActual != destino) {
            plantaActual += Integer.compare(destino, plantaActual);
            return;
        }
        personas.remove(destinoPersona);
        Planta planta = buscarPlanta(plantaActual);
        if (planta != null) {
            planta.personaLlegaADestino(destinoPersona);
        }
    }

    private void atenderLlamadaPendiente() {
        Llamada llamada = llamadas.peek();
        if (plantaActual != llamada.getPlantaOrigen()) {
            plantaActual += Integer.compare(llamada.getPlantaOrigen(), plantaActual);
            return;
        }

        if (personas.size() < CAPACIDAD) {
            Persona persona = llamada.getPersona();
            personas.add(persona);
            llamada.getPersona().marcarAtendido();
            llamadas.poll();

            Planta planta = buscarPlanta(plantaActual);
            if (planta != null) {
                planta.personaSubeAlAscensor(persona);
            }
        }
    }

    private Planta buscarPlanta(int numero) {
        for (Planta p : plantas) {
            if (p.getNumero() == numero)
                return p;
        }
        return null;
    }

    public void bajarPersonasEnPlantaActual() {
        List<Persona> bajan = new ArrayList<>();
        for (Persona p : personas) {
            if (p.getPlantaDestino() == plantaActual) {
                bajan.add(p);
            }
        }

        for (Persona p : bajan) {
            personas.remove(p);
            p.marcarAtendido();

            Planta planta = buscarPlanta(plantaActual);
            if (planta != null) {
                planta.personaLlegaADestino(p);
            }
        }
    }

    public String getId() {
        return id;
    }

    public int getPlantaActualAsInt() {
        return plantaActual;
    }

    public void imprimirEstado() {
        System.out.println("Ascensor " + id + " en planta " + plantaActual + ", personas: " + personas.size());
    }
}
