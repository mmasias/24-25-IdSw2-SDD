package src.moduloInventario.controlador;

import src.moduloCaja.controlador.ControladorCaja;
import src.moduloEmpleado.controlador.ControladorEmpleado;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;
import src.moduloInventario.vista.IVistaInventario;
import src.moduloInventario.vista.vistaInventario;
import src.moduloPago.vista.VistaPago;
import java.util.List;

public class ControladorInventario {
    private List<Celda> celdas;
    private IVistaInventario vista;
    private ControladorEmpleado controladorEmpleado;

    public ControladorInventario(ControladorEmpleado controladorEmpleado, List<Celda> celdas) {
        this.vista = new vistaInventario();
        this.controladorEmpleado = controladorEmpleado;
        this.celdas = celdas;
    }

    public void agregarCelda(Celda celda) {
        celdas.add(celda);
    }

    public void mostrarInventario() {
        for (int i = 0; i < celdas.size(); i++) {
            vista.mostrarCelda(celdas.get(i), i);
        }
    }

    public Celda getCelda(int indice) {
        if (indice >= 0 && indice < celdas.size()) {
            return celdas.get(indice);
        }
        return null;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public void mensajePersonalizado(String texto) {
        vista.mostrarMensaje(texto);
    }

    public void despacharProducto(int numProducto, ControladorCaja controladorCaja, VistaPago vistaPago) {
        if (numProducto >= 0 && numProducto < celdas.size()) {
            Celda celda = celdas.get(numProducto);
            if (celda.getCantidad() > 0) {
                celda.disminuirCantidad();
                celdas.set(numProducto, celda);
                vistaPago.mostrarMensaje("Producto despachado: " + celda.getProducto().getNombre());
            } else {
                vistaPago.mostrarMensaje("Producto agotado.");
                Producto producto = celda.getProducto();
                if (celda.getCantidad() <= 0) {
                    int cantidadRecarga = 5;
                    controladorEmpleado.cargarCelda(celda, producto, cantidadRecarga);
                }
            }
        } else {
            vistaPago.mostrarMensaje("Celda inválida.");
        }
    }
}
