package factory;

import implementacion.vista.VisualizadorSimulacion;
import interfaces.vista.IVisualizador;

public class VistaFactory {

    private VistaFactory() {
    }

    public static IVisualizador crearVisualizador() {
        return new VisualizadorSimulacion();
    }
}
