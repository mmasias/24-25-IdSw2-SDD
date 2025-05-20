import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Vista.VisualizadorSimulacion;
import Controlador.ControladorSimulacion;

public class App {
    public static void main(String[] args) {
        Cola cola = new Cola();
        GestorCajas gestorCajas = new GestorCajas(4);  
        Estadisticas estadisticas = new Estadisticas();
        VisualizadorSimulacion visualizador = new VisualizadorSimulacion();
        ControladorSimulacion controlador = new ControladorSimulacion(cola, gestorCajas, estadisticas, visualizador);
        
        controlador.iniciarSimulacion(720);
    }
}