package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    private static final int PLANTA_MIN = -3;
    private static final int PLANTA_MAX = 3;

    private int capacidadMaxima;
    private int plantaActual;
    private List<Persona> personas;
    private int estado; // 0: detenido, 1: subiendo, -1: bajando
    private List<Planta> plantas;

    public Ascensor(int capacidadMaxima, List<Planta> plantas) {
        this.capacidadMaxima = capacidadMaxima;
        this.plantaActual = 0;
        this.personas = new ArrayList<>();
        this.estado = 0;
        this.plantas = plantas;
    }

    public boolean puedeRecogerPersona() {
        return personas.size() < capacidadMaxima;
    }
    public boolean puedeRecogerPersona(Persona persona) {
        return personas.size() < capacidadMaxima;
    }
    public void recogerPersona(Persona persona) {
        if (puedeRecogerPersona()) {
            personas.add(persona);
            obtenerPlanta(persona.getPlantaActual()).removerPersonaEsperando(persona);
            actualizarEstado();
        }
    }

    private Planta obtenerPlanta(int numero) {
        return plantas.get(numero + 3); // Ajuste para índices
    }

    public void actualizarEstado() {
        if (personas.isEmpty()) {
            estado = 0;
        } else {
            int destinoMasCercano = personas.stream()
                    .mapToInt(Persona::getPlantaDestino)
                    .min().orElse(plantaActual);

            estado = Integer.compare(destinoMasCercano, plantaActual);
        }
    }

    public void mover() {
        if ((estado == 1 && plantaActual < PLANTA_MAX) || (estado == -1 && plantaActual > PLANTA_MIN)) {
            plantaActual += estado;
        }
        dejarPersonas();
    }

    private void dejarPersonas() {
        List<Persona> aBajar = new ArrayList<>();
        Planta plantaDestino = obtenerPlanta(plantaActual);

        for (Persona persona : personas) {
            if (persona.getPlantaDestino() == plantaActual) {
                plantaDestino.agregarPersonaEnPlanta(persona);
                aBajar.add(persona);
            }
        }
        personas.removeAll(aBajar);
    }

    public String representacion(int planta) {
        if (planta == plantaActual) {
            return estado == 1 ? "[^" + personas.size() + "^]" : estado == -1 ? "[v" + personas.size() + "v]" : "[o]";
        }
        return "| |";
    }

    public int getPlantaActual() {
        return plantaActual;
    }
}
