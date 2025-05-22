package Controlador;
import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Modelo.Cliente;
import Util.GeneradorClientes;
import Vista.VisualizadorSimulacion;
import Vista.VisualizadorEstadisticas;

public class ControladorSimulacion {
    private final Cola cola;
    private final GestorCajas gestorCajas;
    private final Estadisticas estadisticas;
    private final VisualizadorSimulacion visualizadorSimulacion;
    private final VisualizadorEstadisticas visualizadorEstadisticas;
    
    public ControladorSimulacion(Cola cola, GestorCajas gestorCajas, Estadisticas estadisticas, VisualizadorSimulacion visualizador, VisualizadorEstadisticas estadisticasVista) {
        this.cola = cola;
        this.gestorCajas = gestorCajas;
        this.estadisticas = estadisticas;
        this.visualizadorSimulacion = visualizador;
        this.visualizadorEstadisticas = estadisticasVista;
    }
    
    public void iniciarSimulacion(int duracionJornada) {
        for (int minuto = 1; minuto <= duracionJornada; minuto++) {
            System.out.println("\n--- Minuto " + minuto + " ---");
            
            if (Math.random() <= GeneradorClientes.PROB_LLEGADA) {
                Cliente nuevoCliente = GeneradorClientes.generarCliente();
                cola.agregar(nuevoCliente);
                System.out.println("+ Cliente llega (" + nuevoCliente.getItems() + " items)");
            } else {
                System.out.println("· No llega nadie");
            }
            
            gestorCajas.procesar(cola, estadisticas);
            estadisticas.registrarMinuto(cola);
            
            visualizadorSimulacion.mostrarEstado(cola, gestorCajas);
        }
        
        estadisticas.setClientesPendientes(cola.cantidad());
        visualizadorEstadisticas.mostrarResumen(estadisticas);
    }
}