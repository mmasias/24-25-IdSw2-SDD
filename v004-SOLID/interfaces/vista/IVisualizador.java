package interfaces.vista;

import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;

import java.util.List;

public interface IVisualizador {
    void actualizarVistaSimulacion(ICola cola, List<ICaja> cajas, IEstadisticas estadisticas);
    void mostrarEstadisticasFinales(IEstadisticas estadisticas);
    void mostrarMensaje(String mensaje);
}