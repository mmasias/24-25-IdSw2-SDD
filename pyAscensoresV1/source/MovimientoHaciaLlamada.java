package source;

public class MovimientoHaciaLlamada implements EstadoAscensor {
    @Override
    public void ejecutar(Ascensor ascensor) {
        ascensor.moverHaciaLlamadaMasCercana();
    }
}
