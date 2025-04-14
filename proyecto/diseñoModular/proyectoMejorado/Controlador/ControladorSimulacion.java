package proyecto.diseñoModular.proyectoMejorado.Controlador;
import proyecto.diseñoModular.proyectoMejorado.Modelo.Cliente;
import proyecto.diseñoModular.proyectoMejorado.Modelo.Cola;
import proyecto.diseñoModular.proyectoMejorado.Modelo.GestorCajas;
import proyecto.diseñoModular.proyectoMejorado.Util.GeneradorClientes;
import proyecto.diseñoModular.proyectoMejorado.Modelo.Estadisticas;
import proyecto.diseñoModular.proyectoMejorado.Vista.VisualizadorSimulacion;

public class ControladorSimulacion {
    private final Cola cola;
    private final GestorCajas gestorCajas;
    private final Estadisticas estadisticas;
    private final VisualizadorSimulacion visualizador;
    
    public ControladorSimulacion(Cola cola, GestorCajas gestorCajas, Estadisticas estadisticas, VisualizadorSimulacion visualizador) {
        this.cola = cola;
        this.gestorCajas = gestorCajas;
        this.estadisticas = estadisticas;
        this.visualizador = visualizador;
    }
    
    public void iniciarSimulacion(int duracionJornada) {
        for (int minuto = 1; minuto <= duracionJornada; minuto++) {
            System.out.println("\n--- Minuto " + minuto + " ---");
            
            // Llegada de clientes
            if (Math.random() <= GeneradorClientes.PROB_LLEGADA) {
                Cliente nuevoCliente = GeneradorClientes.generarCliente();
                cola.agregar(nuevoCliente);
                System.out.println("+ Cliente llega (" + nuevoCliente.getItems() + " items)");
            } else {
                System.out.println("· No llega nadie");
            }
            
            // Procesar cajas
            gestorCajas.procesar(cola, estadisticas);
            estadisticas.registrarMinuto(cola);
            
            // Mostrar estado
            visualizador.mostrarEstado(cola, gestorCajas);
        }
        
        // Finalizar
        estadisticas.setClientesPendientes(cola.cantidad());
        estadisticas.mostrarResumen();
    }
}
