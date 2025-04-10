package pyAscensoresV1.src;

import java.util.*;

public class ControlAscensor {
    private List<Ascensor> ascensores;

    public ControlAscensor(List<Ascensor> ascensores) {
        this.ascensores = ascensores;
    }

    public void procesarLlamada(Persona persona, int origen, int destino) {
        Ascensor mejor = seleccionarAscensor(origen);
        mejor.atenderLlamada(origen, persona);
    }

    private Ascensor seleccionarAscensor(int planta) {
        return ascensores.get(0);
    }
}
