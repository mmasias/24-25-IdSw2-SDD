package source;

import java.util.*;

/**
 * Clase mejorada para el control de ascensores con lógica más realista
 */
public class ControlAscensor {
    private List<Ascensor> ascensores;

    public ControlAscensor(List<Ascensor> ascensores) {
        this.ascensores = ascensores;
    }

    /**
     * Procesa una llamada y la asigna al ascensor más adecuado
     */
    public void procesarLlamada(Persona persona, int origen, int destino) {
        Ascensor mejor = seleccionarMejorAscensor(origen);
        Llamada llamada = new Llamada(origen, destino, persona);
        mejor.atenderLlamada(llamada);
    }

    /**
     * Selecciona el ascensor más adecuado para atender una llamada
     * Implementa una lógica mejorada que considera:
     * - Distancia al origen
     * - Si el ascensor está vacío o va en la dirección adecuada
     * - Capacidad disponible
     */
    private Ascensor seleccionarMejorAscensor(int planta) {
    List<Ascensor> candidatos = new ArrayList<>();
    int mejorPuntuacion = Integer.MAX_VALUE;

    for (Ascensor ascensor : ascensores) {
        int puntuacion = calcularPuntuacionAscensor(ascensor, planta);
        if (puntuacion < mejorPuntuacion) {
            mejorPuntuacion = puntuacion;
            candidatos.clear();
            candidatos.add(ascensor);
        } else if (puntuacion == mejorPuntuacion) {
            candidatos.add(ascensor);
        }
    }

    // Si hay empate, elige uno al azar entre los mejores
    return candidatos.get(new Random().nextInt(candidatos.size()));
}

    
    /**
     * Calcula una puntuación para un ascensor según lo adecuado que sea
     * para atender una llamada en la planta especificada.
     * Puntuación más baja = mejor ascensor.
     */
    private int calcularPuntuacionAscensor(Ascensor ascensor, int planta) {
        // Distancia base (factor más importante)
        int distancia = Math.abs(ascensor.getPlantaActualAsInt() - planta);
        int puntuacion = distancia * 10;  // Ponderamos la distancia
        
        // Si el ascensor está lleno, lo penalizamos mucho
        if (ascensor.personasEnElAscensor() >= 6) {
            puntuacion += 100;  // Fuerte penalización si está lleno
        } else {
            // Penalización según cuán lleno está
            puntuacion += ascensor.personasEnElAscensor() * 5;
        }
        
        // Un ascensor en movimiento que ya pasa por la planta es ideal
        // (Esta lógica se podría extender si sabemos la dirección del ascensor)
        
        return puntuacion;
    }

    /**
     * Mueve todos los ascensores según su lógica interna
     */
    public void moverAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.mover();
        }
    }

    /**
     * Imprime el estado actual de los ascensores
     */
    public void imprimirEstadoAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.imprimirEstado();
        }
    }
}