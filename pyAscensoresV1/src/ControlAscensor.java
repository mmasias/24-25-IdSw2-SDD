package pyAscensoresV1.src;

import java.util.*;

public class ControlAscensor {
    private List<Ascensor> ascensores;

    public ControlAscensor(List<Ascensor> ascensores) {
        this.ascensores = ascensores;
    }

    public void procesarLlamada(Persona persona, int origen, int destino) {
        Ascensor ascensor = seleccionarAscensor(origen);
        ascensor.atenderLlamada(origen, persona);
    }

    private Ascensor seleccionarAscensor(int planta) {
        return ascensores.get(0);
    }

    public void asignarPlantas(List<Planta> plantas) {
        for (Ascensor ascensor : ascensores) {
            ascensor.asignarPlantas(plantas);
        }
    }
}
