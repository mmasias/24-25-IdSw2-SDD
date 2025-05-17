package v001;
public interface UnidadConMovimiento {
    void calcularMovimiento(Habitacion habitacion);
    void mover(int nuevaX, int nuevaY);
    int getPosicionX();
    int getPosicionY();
}
