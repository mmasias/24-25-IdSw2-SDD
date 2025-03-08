package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Ascensor {
    private int capacidadMaxima;
    private int plantaActual;
    private List<Persona> personas;
    private int estado; // 0: detenido, 1: subiendo, -1: bajando
    private List<Planta> plantas;  // Lista de todas las plantas en el edificio

    public Ascensor(int capacidadMaxima, List<Planta> plantas) {
        this.capacidadMaxima = capacidadMaxima;
        this.plantaActual = 0;
        this.personas = new ArrayList<Persona>();
        this.estado = 0; // El ascensor comienza detenido
        this.plantas = plantas; // Inicializar la lista de plantas
    }

    public boolean puedeRecogerPersona(Persona p) {
        return personas.size() < capacidadMaxima;
    }

    public void recogerPersona(Persona persona) {
        if (puedeRecogerPersona(persona)) {
            // Agregar la persona al ascensor
            personas.add(persona);
            
            // Quitar la persona de la lista de esperando de la planta correspondiente
            Planta plantaOrigen = obtenerPlantaOrigen(persona);
            plantaOrigen.getEsperando().remove(persona);
            
            cambiarEstado(); // Cambiar el estado del ascensor al recoger a una persona
        }
    }

    // Método auxiliar para obtener la planta de origen de una persona
    private Planta obtenerPlantaOrigen(Persona persona) {
        return plantas.get(persona.getPlantaActual() + 3);  // +3 porque el índice de las plantas va de -3 a 3
    }

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

    private int obtenerPlantaDestinoMasCercana() {
        int plantaDestinoMasCercana = Integer.MAX_VALUE;
        for (Persona persona : personas) {
            if (persona.getPlantaDestino() != plantaActual) {
                plantaDestinoMasCercana = Math.min(plantaDestinoMasCercana, persona.getPlantaDestino());
            }
        }
        return plantaDestinoMasCercana;
    }

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

    public void llevarPersonas() {
        List<Persona> personasABajar = new ArrayList<>();
        
        for (Persona persona : personas) {
            if (persona.getPlantaDestino() == plantaActual) {
                // Si la persona ha llegado a su planta destino, agregarla a la planta
                Planta plantaDestino = plantas.get(persona.getPlantaDestino() + 3); // +3 para obtener el índice correcto
                plantaDestino.getEnPlanta().add(persona);
                personasABajar.add(persona);
            }
        }
        
        // Eliminar personas que deben bajarse del ascensor
        personas.removeAll(personasABajar);
    }

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
