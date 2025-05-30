package src.moduloInventario.controlador;

import src.moduloInventario.modelo.Celda;
import src.moduloInventario.vista.IVistaInventario;

import java.util.ArrayList;
import java.util.List;

public class controladorInventario {
    private List<Celda> celdas;
    private IVistaInventario vista;

    public controladorInventario(IVistaInventario vista) {
        this.vista = vista;
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
}
