import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Vista.VisualizadorSimulacion;
import Controlador.ControladorSimulacion;

public class App {
    public static void main(String[] args) {
        // Crear instancias necesarias
        Cola cola = new Cola();
        GestorCajas gestorCajas = new GestorCajas(4);  // Número de cajas
        Estadisticas estadisticas = new Estadisticas();
        VisualizadorSimulacion visualizador = new VisualizadorSimulacion();
        ControladorSimulacion controlador = new ControladorSimulacion(cola, gestorCajas, estadisticas, visualizador);
        
        // Ejecutar la simulación por 720 minutos (por ejemplo)
        controlador.iniciarSimulacion(720);
    }
}