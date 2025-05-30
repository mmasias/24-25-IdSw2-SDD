package interfaces.modelo;

public interface ICliente {
    int getId();
    long getTiempoLlegada();
    long getTiempoEspera();
    void setTiempoEspera(long tiempoEspera);
    long getTiempoAtencion();
    void setTiempoAtencion(long tiempoAtencion);
    int getCantidadProductos();
    void setCantidadProductos(int cantidad); 
}
