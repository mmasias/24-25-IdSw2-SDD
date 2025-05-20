package factory;

import implementacion.controlador.ControladorSimulacion;
import interfaces.controlador.IControladorSimulacion;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;
import interfaces.modelo.IGestorCajas;
import interfaces.util.IGeneradorClientes;
import interfaces.vista.IVisualizador;


public class ControladorFactory {

    private ControladorFactory() {
    }

    public static IControladorSimulacion crearControlador(
            ICola cola,
            IGestorCajas gestorCajas,
            IEstadisticas estadisticas,
            IGeneradorClientes generadorClientes,
            IVisualizador visualizador) {

        return new ControladorSimulacion(
                cola,
                gestorCajas,
                estadisticas,
                generadorClientes,
                visualizador
        );
    }
}