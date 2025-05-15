package interfaces.modelo;

public interface ICliente {
    int getId();
    long getTiempoLlegada();
    long getTiempoEspera();
    long getTiempoAtencion();
    void setTiempoAtencion(long tiempoAtencion);
    void setTiempoInicioAtencion(long tiempoInicioAtencion);
    boolean estaAtendido();
}