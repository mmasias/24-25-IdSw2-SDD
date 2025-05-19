package pyAscensores.v3;

public class MovimientoHaciaDestino implements EstadoAscensor {
    @Override
    public void ejecutar(Ascensor ascensor) {
        ascensor.moverHaciaDestino();
    }
}
