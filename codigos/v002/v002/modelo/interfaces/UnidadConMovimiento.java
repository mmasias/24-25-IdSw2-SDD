package v002.modelo.interfaces;



import v002.modelo.mapa.Habitacion;

public interface UnidadConMovimiento {
    void calcularMovimiento(Habitacion habitacion);
    void mover(int nuevaX, int nuevaY);
    int getPosicionX();
    int getPosicionY();
}
