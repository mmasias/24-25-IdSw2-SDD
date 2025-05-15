package pyAscensores.v4.controlador;

import pyAscensores.v4.modelo.EstadoAscensor;
import pyAscensores.v4.modelo.Ascensor;

public class MovimientoHaciaLlamada implements EstadoAscensor {
    @Override
    public void ejecutar(Ascensor ascensor) {
        ascensor.moverHaciaLlamadaMasCercana();
    }
}
