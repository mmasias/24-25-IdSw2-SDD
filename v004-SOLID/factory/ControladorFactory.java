package factory;

import implementacion.controlador.ControladorSimulacion;
import interfaces.controlador.IControladorSimulacion;
import interfaces.modelo.ICola;
import interfaces.modelo.IEstadisticas;
import interfaces.modelo.IGestorCajas;
import interfaces.util.IGeneradorClientes;
import interfaces.vista.IVisualizador;

/**
 * Factory para componentes del controlador.
 * Implementa el principio de inversión de dependencias (DIP)
 * y facilita la creación y configuración de objetos del controlador.
 */
public class ControladorFactory {

    private ControladorFactory() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Crea una instancia de ControladorSimulacion con todas sus dependencias
     */
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