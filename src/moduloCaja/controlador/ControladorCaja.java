package src.moduloCaja.controlador;

import src.moduloCaja.modelo.Caja;
import src.moduloCaja.vista.VistaCaja;

public class ControladorCaja {
    private Caja caja;
    private VistaCaja vista;

    public ControladorCaja(Caja caja, VistaCaja vista) {
        this.caja = caja;
        this.vista = vista;
    }

    public void agregarFondos(double monto) {
        String mensaje = (monto <= 0) ? "Error: El monto debe ser mayor que cero." : null;
        if (mensaje != null) {
            vista.mostrarMensaje(mensaje);
            return;
        }
        caja.agregarFondos(monto);
        vista.mostrarMensaje("Fondos agregados correctamente.");
    }

    public void retirarFondos(double monto) {
        String mensaje = (monto <= 0) ? "Error: El monto debe ser mayor que cero."
                : (caja.getTotal() < monto) ? "Error: Fondos insuficientes en la caja."
                        : null;
        if (mensaje != null) {
            vista.mostrarMensaje(mensaje);
            return;
        }
        caja.retirarFondos(monto);
        vista.mostrarMensaje("Fondos retirados correctamente.");
    }

    public void mostrarTotal() {
        vista.mostrarTotal(caja.getTotal());
    }

    public void retirarTodo() {
        double retirado = caja.retirarTodo();
        vista.mostrarMensaje("Se han retirado " + retirado + "€ de la caja.");
    }

    public void entregarCambio(double monto) {
        String mensaje = (monto < 0) ? "Error: El monto de cambio no puede ser negativo."
                : (!caja.entregarCambio(monto)) ? "Error: Fondos insuficientes en la caja para entregar cambio."
                        : null;
        if (mensaje != null) {
            vista.mostrarMensaje(mensaje);
            return;
        }
        vista.mostrarMensaje("Cambio entregado correctamente: " + monto + "€");
    }
}