import Modelo.Cola;
import Modelo.Cliente;
import Modelo.GestorCajas;
import Util.GeneradorClientes;
import Vista.VisualizadorSimulacion;
import Controlador.ControladorSimulacion;
import Modelo.Estadisticas;


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