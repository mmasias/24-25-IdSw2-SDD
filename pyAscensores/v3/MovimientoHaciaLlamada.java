package pyAscensores.v3;

public class MovimientoHaciaLlamada implements EstadoAscensor {
    @Override
    public void ejecutar(Ascensor ascensor) {
        ascensor.moverHaciaLlamadaMasCercana();
    }
}
