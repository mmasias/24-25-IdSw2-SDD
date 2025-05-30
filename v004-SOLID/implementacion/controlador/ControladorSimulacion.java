package implementacion.controlador;

import interfaces.controlador.IControladorSimulacion;
import interfaces.modelo.*;
import interfaces.util.IGeneradorClientes;
import interfaces.vista.IVisualizador;

import java.util.Timer;
import java.util.TimerTask;

public class ControladorSimulacion implements IControladorSimulacion {
    private ICola cola;
    private IGestorCajas gestorCajas;
    private IEstadisticas estadisticas;
    private IGeneradorClientes generadorClientes;
    private IVisualizador visualizador;

    private Timer timer;
    private boolean enEjecucion;
    private long tiempoActual;
    private int numPasos;
    private int maxPasos;
    private int numCajas;
    private int maxClientes;
    private double tasaLlegada;

    public ControladorSimulacion(
            ICola cola,
            IGestorCajas gestorCajas,
            IEstadisticas estadisticas,
            IGeneradorClientes generadorClientes,
            IVisualizador visualizador) {

        this.cola = cola;
        this.gestorCajas = gestorCajas;
        this.estadisticas = estadisticas;
        this.generadorClientes = generadorClientes;
        this.visualizador = visualizador;

        this.enEjecucion = false;
        this.tiempoActual = 0;
        this.numPasos = 0;
        this.maxPasos = implementacion.util.Constantes.Simulacion.MAX_PASOS_SIMULACION;
        this.numCajas = implementacion.util.Constantes.Config.NUM_CAJAS_DEFAULT;
        this.maxClientes = implementacion.util.Constantes.Config.MAX_CLIENTES_DEFAULT;
        this.tasaLlegada = implementacion.util.Constantes.Simulacion.TASA_LLEGADA_DEFAULT;
    }

    @Override
    public void iniciarSimulacion() {
        if (enEjecucion) {
            return;
        }

        gestorCajas.inicializarCajas(numCajas);

        enEjecucion = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ejecutarPaso();
            }
        }, 0, implementacion.util.Constantes.Simulacion.TIEMPO_PASO_DEFAULT);
    }

    @Override
    public void pausarSimulacion() {
        if (!enEjecucion) {
            return;
        }

        enEjecucion = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void reiniciarSimulacion() {
        pausarSimulacion();

        tiempoActual = 0;
        numPasos = 0;

        cola = new implementacion.modelo.Cola();
        gestorCajas.inicializarCajas(numCajas);
        estadisticas = new implementacion.modelo.Estadisticas(cola);

        visualizador.mostrarMensaje("Simulación reiniciada");
    }

    @Override
    public void ejecutarPaso() {
        if (numPasos >= maxPasos) {
            pausarSimulacion();
            visualizador.mostrarEstadisticasFinales(estadisticas);
            return;
        }

        tiempoActual++;
        numPasos++;

        
        for (ICaja caja : gestorCajas.obtenerTodasLasCajas()) {
            if (caja instanceof implementacion.modelo.Caja) {
                ((implementacion.modelo.Caja) caja).actualizarEstado(tiempoActual);
            }
        }

        
        if (generadorClientes.debeGenerarClienteEnEstePaso(tasaLlegada)) {
            ICliente nuevoCliente = generadorClientes.generarNuevoCliente(tiempoActual);
            cola.agregarCliente(nuevoCliente);
            estadisticas.registrarLlegadaCliente(nuevoCliente);
        }

        
        while (!cola.estaVacia() && gestorCajas.hayCajasDisponibles()) {
            ICliente clienteAAtender = cola.siguienteCliente();
            ICaja cajaDisponible = gestorCajas.obtenerCajaDisponible(clienteAAtender);

            if (cajaDisponible != null) {
                cajaDisponible.atenderCliente(clienteAAtender);
                clienteAAtender.setTiempoAtencion(clienteAAtender.getCantidadProductos());
                estadisticas.registrarClienteAtendido(clienteAAtender, cajaDisponible);
            } else {
                cola.agregarClienteAlInicio(clienteAAtender);
                break;
            }
        }

        visualizador.actualizarVistaSimulacion(cola, gestorCajas.obtenerTodasLasCajas(), estadisticas);
    }

    @Override
    public void configurarParametros(int numCajas, int maxClientes, double tasaLlegada) {
        if (numCajas <= 0 || maxClientes <= 0 || tasaLlegada <= 0 || tasaLlegada > 1) {
            visualizador.mostrarMensaje("Parámetros inválidos");
            return;
        }

        this.numCajas = numCajas;
        this.maxClientes = maxClientes;
        this.tasaLlegada = tasaLlegada;
        generadorClientes.configurarTasaLlegada(tasaLlegada);

        if (enEjecucion) {
            pausarSimulacion();
            gestorCajas.inicializarCajas(numCajas);
            iniciarSimulacion();
        }
    }
}
