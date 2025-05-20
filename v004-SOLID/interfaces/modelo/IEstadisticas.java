package interfaces.modelo;

public interface IEstadisticas {
    void registrarLlegadaCliente(ICliente cliente);
    void registrarClienteAtendido(ICliente cliente, ICaja caja);
    double getTiempoPromedioEspera();
    double getTiempoPromedioAtencion();
    int getClientesAtendidos();
    int getClientesEnCola();
}