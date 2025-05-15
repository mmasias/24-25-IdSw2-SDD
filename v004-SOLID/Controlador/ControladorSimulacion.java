package Controlador;
import Modelo.Caja;
import Modelo.Cola;
import Modelo.GestorCajas;
import Modelo.Estadisticas;
import Modelo.Cliente;
import Interfaces.IVisualizador;
import Interfaces.IGeneradorClientes;

public class ControladorSimulacion {
    private final Cola cola;
    private final GestorCajas gestorCajas;
    private final Estadisticas estadisticas;
    private final IVisualizador visualizador;
    private final IGeneradorClientes generador;

    public ControladorSimulacion(Cola cola, GestorCajas gestorCajas, Estadisticas estadisticas,IVisualizador visualizador, IGeneradorClientes generador) {
        this.cola = cola;
        this.gestorCajas = gestorCajas;
        this.estadisticas = estadisticas;
        this.visualizador = visualizador;
        this.generador = generador;
    }

    public void iniciarSimulacion(int duracionJornada) {
        for (int minuto = 1; minuto <= duracionJornada; minuto++) {
            System.out.println("\n--- Minuto " + minuto + " ---");

            if (Math.random() <= generador.probabilidadLlegada()) {
                Cliente nuevoCliente = generador.generarCliente();
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
        estadisticas.mostrarResumen();
    }
}