package src.moduloEmpleado.controlador;

import src.moduloCaja.modelo.Caja;
import src.moduloEmpleado.modelo.Empleado;
import src.moduloEmpleado.vista.VistaEmpleado;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;

public class ControladorEmpleado {
    private Empleado empleado;
    private VistaEmpleado vista;

    public ControladorEmpleado(Empleado empleado) {
        this.empleado = empleado;
        this.vista = new VistaEmpleado();
    }

    public void recargarCaja(Caja caja, double monto) {
        String mensaje = (caja == null) ? "Error: La caja no puede ser nula."
                        : (monto <= 0) ? "Error: El monto debe ser mayor que cero."
                        : null;
        if (mensaje != null) {
            vista.mostrarMensaje(mensaje);
            return;
        }
        empleado.recargarCaja(caja, monto);
        vista.mostrarMensaje("Caja recargada correctamente.");
    }

    public void vaciarCaja(Caja caja) {
        String mensaje = (caja == null) ? "Error: La caja no puede ser nula."
                        : (caja.getTotal() <= 0) ? "Error: La caja ya está vacía."
                        : null;
        if (mensaje != null) {
            vista.mostrarMensaje(mensaje);
            return;
        }
        empleado.vaciarCaja(caja);
        vista.mostrarMensaje("Caja vaciada correctamente.");
    }

    public void cargarCelda(Celda celda, Producto producto, int cantidad) {
        String mensaje = (celda == null) ? "Error: La celda no puede ser nula."
                        : (producto == null) ? "Error: El producto no puede ser nulo."
                        : (cantidad <= 0) ? "Error: La cantidad debe ser mayor que cero."
                        : null;
        if (mensaje != null) {
            vista.mostrarMensaje(mensaje);
            return;
        }
        empleado.cargarCelda(celda, producto, cantidad);
        vista.mostrarMensaje("Celda cargada correctamente.");
    }

    public void mostrarNombreEmpleado() {
        vista.mostrarMensaje("Empleado: " + empleado.getNombre());
    }
}
