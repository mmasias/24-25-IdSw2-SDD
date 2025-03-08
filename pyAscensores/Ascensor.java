package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    private int capacidadMaxima;
    private int plantaActual;
    private List<Persona> personas;
    private int estado; // 0: detenido, 1: subiendo, -1: bajando

    public Ascensor(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.plantaActual = 0;
        this.personas = new ArrayList<Persona>();
        this.estado = 0; // El ascensor comienza detenido
    }

    public boolean puedeRecogerPersona(Persona p) {
        return personas.size() < capacidadMaxima;
    }

    public void recogerPersona(Persona persona) {
        if (puedeRecogerPersona(persona)) {
            personas.add(persona);
            cambiarEstado(); // Cambiar el estado del ascensor al recoger a una persona
        }
    }

    // Método que cambia el estado del ascensor basado en su dirección y las personas a bordo
    public void cambiarEstado() {
        if (personas.isEmpty()) {
            estado = 0; // El ascensor se detiene si no hay personas
        } else {
            // Si el ascensor tiene personas, se dirige hacia la planta destino más cercana
            int plantaDestinoMasCercana = obtenerPlantaDestinoMasCercana();
            if (plantaDestinoMasCercana > plantaActual) {
                estado = 1; // Subir
            } else if (plantaDestinoMasCercana < plantaActual) {
                estado = -1; // Bajar
            } else {
                estado = 0; // El ascensor está en la planta destino de la persona
            }
        }
    }

    // Obtener la planta destino más cercana de las personas en el ascensor
    private int obtenerPlantaDestinoMasCercana() {
        int plantaDestinoMasCercana = Integer.MAX_VALUE;
        for (Persona persona : personas) {
            if (persona.getPlantaDestino() != plantaActual) {
                plantaDestinoMasCercana = Math.min(plantaDestinoMasCercana, persona.getPlantaDestino());
            }
        }
        return plantaDestinoMasCercana;
    }

    // Mover el ascensor hacia arriba o hacia abajo
    public void mover() {
        // Validar si el ascensor puede moverse sin exceder los límites de plantas
        if (estado == 1 && plantaActual < 3) {
            plantaActual++; // Subir
        } else if (estado == -1 && plantaActual > -3) {
            plantaActual--; // Bajar
        }
        // Llamar a llevar personas para dejar a las personas en la planta correcta
        llevarPersonas();
    }
    

    // Dejar a las personas en la planta destino
    public void llevarPersonas() {
        List<Persona> personasABajar = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getPlantaDestino() == plantaActual) {
                personasABajar.add(persona);
            }
        }
        // Eliminar personas que deben bajarse y agregarlas a la planta
        personas.removeAll(personasABajar);
    }

    // Representar el ascensor en la planta
    public String representacion(int planta) {
        if (planta == plantaActual) {
            return estado == 1 ? "[^" + personas.size() + "^]" : estado == -1 ? "[v" + personas.size() + "v]" : "[o]";
        } else {
            return "| |";
        }
    }

    public int getPlantaActual() {
        return plantaActual;
    }
}
