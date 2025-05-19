package modelo;

import java.util.List;

public interface IEdificio {
    void solicitarTransporte(int origen, int destino);
    void moverPersonaEntrePlantas(int origen, int destino);
    void sacarPersonasDePlanta(int planta, double porcentaje);
    void evolucionar();
    int obtenerCantidadEsperando(int planta);
    int obtenerCantidadEnPlanta(int planta);
    IPlanta obtenerPlanta(int nivel);
    ITiempo getTiempo();
    List<ITransporte> getTransportes();
    boolean estaAbierto();
    boolean estaVacio();
}
