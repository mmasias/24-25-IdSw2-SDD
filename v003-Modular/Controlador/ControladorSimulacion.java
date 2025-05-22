package Controlador;
import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Modelo.Cliente;
import Util.GeneradorClientes;
import Vista.VisualizadorSimulacion;
import Vista.EstadisticasVista;

public class ControladorSimulacion {
    private final Cola cola;
    private final GestorCajas gestorCajas;
    private final Estadisticas estadisticas;
    private final VisualizadorSimulacion visualizador;
    private final EstadisticasVista estadisticasVista;
    
    public ControladorSimulacion(Cola cola, GestorCajas gestorCajas, Estadisticas estadisticas, VisualizadorSimulacion visualizador, EstadisticasVista estadisticasVista) {
        this.cola = cola;
        this.gestorCajas = gestorCajas;
        this.estadisticas = estadisticas;
        this.visualizador = visualizador;
        this.estadisticasVista = estadisticasVista;
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
            
            visualizador.mostrarEstado(cola, gestorCajas);
        }
        
        estadisticas.setClientesPendientes(cola.cantidad());
        estadisticasVista.mostrarResumen(estadisticas);
    }
}