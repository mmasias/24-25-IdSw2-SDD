import proyecto.diseñoModular.proyectoMejorado.Controlador.ControladorSimulacion;
import proyecto.diseñoModular.proyectoMejorado.Modelo.Cola;
import proyecto.diseñoModular.proyectoMejorado.Modelo.Estadisticas;
import proyecto.diseñoModular.proyectoMejorado.Modelo.GestorCajas;
import proyecto.diseñoModular.proyectoMejorado.Vista.VisualizadorSimulacion;

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
