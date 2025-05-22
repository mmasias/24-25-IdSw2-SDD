import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Vista.VisualizadorSimulacion;
import Vista.EstadisticasVista;
import Controlador.ControladorSimulacion;

public class App {
    public static void main(String[] args) {
        Cola cola = new Cola();
        GestorCajas gestorCajas = new GestorCajas(4);  
        Estadisticas estadisticas = new Estadisticas();
        VisualizadorSimulacion visualizador = new VisualizadorSimulacion();
        EstadisticasVista estadisticasVista = new EstadisticasVista();
        ControladorSimulacion controlador = new ControladorSimulacion(cola, gestorCajas, estadisticas, visualizador, estadisticasVista);
        
        controlador.iniciarSimulacion(720);
    }
}