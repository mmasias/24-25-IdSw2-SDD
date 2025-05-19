package controlador;

import modelo.IEdificio;
import modelo.Universidad;

public interface IControladorSimulacion {
    void avanzarSimulacion();
    IEdificio getEdificio();
    boolean debeFinalizarSimulacion();
    Universidad getUniversidad();
}
