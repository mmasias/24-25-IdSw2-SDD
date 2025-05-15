package factory;

import implementacion.vista.VisualizadorSimulacion;
import interfaces.vista.IVisualizador;

/**
 * Factory para componentes de la vista.
 * Implementa el principio de inversión de dependencias (DIP)
 * y facilita la creación y configuración de objetos de la vista.
 */
public class VistaFactory {

    private VistaFactory() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Crea una instancia de VisualizadorSimulacion
     */
    public static IVisualizador crearVisualizador() {
        return new VisualizadorSimulacion();
    }
}
