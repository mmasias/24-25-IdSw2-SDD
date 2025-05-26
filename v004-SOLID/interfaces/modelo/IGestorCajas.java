package interfaces.modelo;

import java.util.List;

public interface IGestorCajas {
    void inicializarCajas(int numeroCajas);
    ICaja obtenerCajaDisponible(ICliente cliente);
    List<ICaja> obtenerTodasLasCajas();
    boolean hayCajasDisponibles();
    void actualizarCajas(long tiempoActual);
}