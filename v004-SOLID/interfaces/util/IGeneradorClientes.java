package interfaces.util;

import interfaces.modelo.ICliente;

public interface IGeneradorClientes {
    ICliente generarNuevoCliente(long tiempoActual);
    boolean debeGenerarClienteEnEstePaso(double probabilidad);
    void configurarTasaLlegada(double tasaLlegada);
}