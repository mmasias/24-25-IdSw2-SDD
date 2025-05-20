package interfaces.modelo;

import java.util.List;

public interface ICola {
    void agregarCliente(ICliente cliente);
    ICliente siguienteCliente();
    boolean estaVacia();
    int getTamanio();
    List<ICliente> getClientes();
}