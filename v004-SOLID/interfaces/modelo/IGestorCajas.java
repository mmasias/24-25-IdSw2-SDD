package interfaces.modelo;

import java.util.List;

public interface IGestorCajas {
    void inicializarCajas(int numeroCajas);
    ICaja obtenerCajaDisponible();
    List<ICaja> obtenerTodasLasCajas();
    boolean hayCajasDisponibles();
    void actualizarCajas(long tiempoActual);
}