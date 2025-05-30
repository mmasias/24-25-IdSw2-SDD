package implementacion.controlador;

import factory.ModeloFactory;
import interfaces.controlador.IControladorSimulacion;
import interfaces.modelo.*;
import interfaces.util.IGeneradorClientes;
import interfaces.vista.IVisualizador;

import javax.swing.SwingUtilities;
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
    private final int maxPasos;
    private int numCajas;
    private int maxClientes;
    private double tasaLlegada;

    public ControladorSimulacion(
        ICola cola,
        IGestorCajas gestorCajas,
        IEstadisticas estadisticas,
        IGeneradorClientes generadorClientes,
        IVisualizador visualizador) {
      this.cola              = cola;
      this.gestorCajas       = gestorCajas;
      this.estadisticas      = estadisticas;
      this.generadorClientes = generadorClientes;
      this.visualizador      = visualizador;

      this.enEjecucion       = false;
      this.tiempoActual      = 0;
      this.numPasos          = 0;
      this.maxPasos          = implementacion.util.Constantes.Simulacion.MAX_PASOS_SIMULACION;
      this.numCajas          = implementacion.util.Constantes.Config.NUM_CAJAS_DEFAULT;
      this.maxClientes       = implementacion.util.Constantes.Config.MAX_CLIENTES_DEFAULT;
      this.tasaLlegada       = implementacion.util.Constantes.Simulacion.TASA_LLEGADA_DEFAULT;
    }

    @Override
    public void iniciarSimulacion() {
      if (enEjecucion) return;
      gestorCajas.inicializarCajas(numCajas);
      enEjecucion = true;
      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
        @Override public void run() { ejecutarPaso(); }
      }, 0, implementacion.util.Constantes.Simulacion.TIEMPO_PASO_DEFAULT);
    }

    @Override
    public void pausarSimulacion() {
      if (!enEjecucion) return;
      enEjecucion = false;
      timer.cancel();
      timer = null;
    }

    @Override
    public void reiniciarSimulacion() {
      pausarSimulacion();
      tiempoActual = 0;
      numPasos     = 0;
      cola         = ModeloFactory.crearCola();
      gestorCajas.inicializarCajas(numCajas);
      estadisticas = ModeloFactory.crearEstadisticas(cola);
      SwingUtilities.invokeLater(() -> visualizador.mostrarMensaje("Simulación reiniciada"));
    }

    @Override
    public void ejecutarPaso() {
      if (numPasos >= maxPasos) {
        pausarSimulacion();
        SwingUtilities.invokeLater(() -> visualizador.mostrarEstadisticasFinales(estadisticas));
        return;
      }
      tiempoActual++;
      numPasos++;

      for (ICaja caja : gestorCajas.obtenerTodasLasCajas()) {
        caja.actualizar(tiempoActual);
      }

      if (generadorClientes.debeGenerarClienteEnEstePaso(tasaLlegada)) {
        ICliente nuevo = generadorClientes.generarNuevoCliente(tiempoActual);
        cola.agregarCliente(nuevo);
        estadisticas.registrarLlegadaCliente(nuevo);
      }

      while (!cola.estaVacia() && gestorCajas.hayCajasDisponibles()) {
        ICliente c = cola.siguienteCliente();
        ICaja caja = gestorCajas.obtenerCajaDisponible(c);
        if (caja != null) {
          c.setTiempoEspera(tiempoActual - c.getTiempoLlegada());
          caja.atenderCliente(c);
          c.setTiempoAtencion(c.getCantidadProductos());
          estadisticas.registrarClienteAtendido(c, caja);
        } else {
          cola.agregarClienteAlInicio(c);
          break;
        }
      }

      SwingUtilities.invokeLater(() ->
        visualizador.actualizarVistaSimulacion(cola, gestorCajas.obtenerTodasLasCajas(), estadisticas)
      );
    }

    @Override
    public void configurarParametros(int numCajas, int maxClientes, double tasaLlegada) {
      if (numCajas<=0 || maxClientes<=0 || tasaLlegada<=0 || tasaLlegada>1) {
        SwingUtilities.invokeLater(() -> visualizador.mostrarMensaje("Parámetros inválidos"));
        return;
      }
      this.numCajas    = numCajas;
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
