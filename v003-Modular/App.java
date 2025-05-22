import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Vista.VisualizadorSimulacion;
import Vista.VisualizadorEstadisticas;
import Controlador.ControladorSimulacion;

public class App {
    public static void main(String[] args) {
        Cola cola = new Cola();
        GestorCajas gestorCajas = new GestorCajas(4);  
        Estadisticas estadisticas = new Estadisticas();
        VisualizadorSimulacion visualizadorSimulacion = new VisualizadorSimulacion();
        VisualizadorEstadisticas visualizadorEstadisticas = new VisualizadorEstadisticas();
        ControladorSimulacion controlador = new ControladorSimulacion(cola, gestorCajas, estadisticas, visualizadorSimulacion, visualizadorEstadisticas);
        
        controlador.iniciarSimulacion(720);
    }
}