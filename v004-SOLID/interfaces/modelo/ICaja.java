package interfaces.modelo;

public interface ICaja {
    boolean estaDisponible();
    void atenderCliente(ICliente cliente);
    void liberarCaja();
    int getClientesAtendidos();
    double getTiempoPromedioAtencion();
    int getId();

    void actualizar(long tiempoActual);

    ICliente getClienteActual();

    boolean esRapida();
}
