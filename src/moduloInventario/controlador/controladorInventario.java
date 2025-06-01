package src.moduloInventario.controlador;

import src.moduloCaja.controlador.ControladorCaja;
import src.moduloEmpleado.controlador.ControladorEmpleado;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;
import src.moduloInventario.vista.IVistaInventario;
import src.moduloPago.vista.VistaPago;
import src.moduloUsuario.modelo.Usuario;
import src.moduloUsuario.vista.IVistaUsuario;

import java.util.ArrayList;
import java.util.List;

public class controladorInventario {
    private List<Celda> celdas;
    private IVistaInventario vista;
    private ControladorEmpleado controladorEmpleado;

    public controladorInventario(IVistaInventario vista, ControladorEmpleado controladorEmpleado) {
        this.vista = vista;
        this.controladorEmpleado = controladorEmpleado;
        this.celdas = new ArrayList<>();
    }

    public void agregarCelda(Celda celda) {
        celdas.add(celda);
    }

    public void mostrarInventario() {
        for (int i = 0; i < celdas.size(); i++) {
            vista.mostrarCelda(celdas.get(i), i);
        }
    }

    public boolean disminuirProducto(int indice) {
        if (indice >= 0 && indice < celdas.size()) {
            Celda celda = celdas.get(indice);
            if (celda.getCantidad() > 0) {
                celda.disminuirCantidad();
                vista.mostrarMensaje("Producto entregado.");
                return true;
            } else {
                vista.mostrarMensaje("Producto agotado.");

                Producto producto = celda.getProducto();
                if (celda.getCantidad() <= 0) {
                    int cantidadRecarga = 5;
                    controladorEmpleado.cargarCelda(celda, producto, cantidadRecarga);
                }
            }
        } else {
            vista.mostrarMensaje("Celda inválida.");
        }
        return false;
    }

    public Celda getCelda(int indice) {
        if (indice >= 0 && indice < celdas.size()) {
            return celdas.get(indice);
        }
        return null;
    }

    public void mensajePersonalizado(String texto) {
        vista.mostrarMensaje(texto);
    }

    public void despacharProducto(int numProducto, Usuario usuario, ControladorCaja controladorCaja, VistaPago vistaPago, IVistaUsuario vistaUsuario) {
        if (numProducto >= 0 && numProducto < celdas.size()) {
            Celda celda = celdas.get(numProducto);
            if (celda.getCantidad() > 0) {
                celda.disminuirCantidad();
                vistaPago.mostrarMensaje("Producto despachado: " + celda.getProducto().getNombre());
    
                // Mostrar estado final de la caja
                vistaPago.mostrarMensaje("\n=== Estado final de la caja ===");
                controladorCaja.mostrarDesgloseCaja();
    
                // Mostrar estado final del usuario
                vistaPago.mostrarMensaje("\n=== Estado final del usuario ===");
                vistaUsuario.mostrarUsuario(usuario);
            } else {
                vistaPago.mostrarMensaje("Producto agotado.");
            }
        } else {
            vistaPago.mostrarMensaje("Celda inválida.");
        }
    }
}
