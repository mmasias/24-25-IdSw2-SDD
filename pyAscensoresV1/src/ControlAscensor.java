package pyAscensoresV1.src;

import java.util.*;

public class ControlAscensor {
    private List<Ascensor> ascensores;

    public ControlAscensor(List<Ascensor> ascensores) {
        this.ascensores = ascensores;
    }

    public void procesarLlamada(Persona persona, int origen, int destino) {
        Ascensor mejor = seleccionarAscensor(origen);
        Llamada llamada = new Llamada(origen, destino, persona);
        mejor.atenderLlamada(llamada);

    }

    private Ascensor seleccionarAscensor(int planta) {
        Ascensor ascensorMasCercano = null;
        int distanciaMinima = Integer.MAX_VALUE;

        for (Ascensor ascensor : ascensores) {
            int distancia = Math.abs(ascensor.getPlantaActualAsInt() - planta);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                ascensorMasCercano = ascensor;
            }
        }

        return ascensorMasCercano;
    }

    public void moverAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.mover();
        }
    }

    public void imprimirEstadoAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.imprimirEstado();
        }
    }
}