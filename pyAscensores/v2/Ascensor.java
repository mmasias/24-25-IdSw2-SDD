package pyAscensores.v2;

import java.util.*;

public class Ascensor {
    private static final int CAPACIDAD = 6;
    private static final int PLANTA_INICIAL = 0;

    private String id;
    private int plantaActual;
    private List<Persona> personas;
    private Queue<Llamada> llamadas;
    private List<Planta> plantas;

    public Ascensor(String id) {
        this.id = id;
        this.plantaActual = PLANTA_INICIAL;
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
        ejecutarAccion(this::bajarPersonasEnPlantaActual);
        ejecutarAccion(this::recogerPersonasEnPlantaActual);
        if (!personas.isEmpty()) {
            ejecutarAccion(this::moverHaciaDestinoPersona);
            return;
        }
        if (!llamadas.isEmpty()) {
            ejecutarAccion(this::atenderLlamadaPendiente);
        }
    }

    private void ejecutarAccion(Runnable accion) {
        accion.run();
    }

    private void moverHaciaDestinoPersona() {
        Persona destinoPersona = personas.get(0);
        int destino = destinoPersona.getPlantaDestino();
        if (plantaActual != destino) {
            plantaActual += Integer.compare(destino, plantaActual);
        }
    }

    private void atenderLlamadaPendiente() {
        Llamada llamada = llamadas.peek();
        if (plantaActual != llamada.getPlantaOrigen()) {
            plantaActual += Integer.compare(llamada.getPlantaOrigen(), plantaActual);
            return;
        }
        recogerPersonasEnPlantaActual();
    }

    private Planta buscarPlanta(int numero) {
        return plantas.stream().filter(p -> p.getNumero() == numero).findFirst().orElse(null);
    }

    private void bajarPersonasEnPlantaActual() {
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

    private void recogerPersonasEnPlantaActual() {
        Planta planta = buscarPlanta(plantaActual);
        if (planta == null) return;
        List<Persona> esperando = new ArrayList<>(planta.getPersonasEsperando());
        for (Persona persona : esperando) {
            if (personas.size() >= CAPACIDAD) break;
            if (!persona.estaAtendido()) {
                personas.add(persona);
                persona.marcarAtendido();
                planta.personaSubeAlAscensor(persona);
                llamadas.removeIf(l -> l.getPersona() == persona);
            }
        }
    }

    public String getId() {
        return id;
    }

    public int personasEnElAscensor() {
        return personas.size();
    }

    public int getPlantaActualAsInt() {
        return plantaActual;
    }

    public void imprimirEstado() {
        System.out.println("Ascensor " + id + " en planta " + plantaActual + ", personas: " + personas.size());
    }
}
