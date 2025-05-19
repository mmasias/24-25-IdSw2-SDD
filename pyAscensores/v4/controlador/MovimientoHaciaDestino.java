package pyAscensores.v4.controlador;

import pyAscensores.v4.modelo.Ascensor;
import pyAscensores.v4.modelo.EstadoAscensor;

public class MovimientoHaciaDestino implements EstadoAscensor {
    @Override
    public void ejecutar(Ascensor ascensor) {
        ascensor.moverHaciaDestino();
    }
}
