package source;

public class MovimientoHaciaDestino implements EstadoAscensor {
    @Override
    public void ejecutar(Ascensor ascensor) {
        ascensor.moverHaciaDestino();
    }
}
