package src.moduloCaja.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.moduloCaja.modelo.Caja;
import src.moduloCaja.vista.VistaCaja;
import src.moduloPago.modelo.Efectivo;

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

    public boolean entregarCambio(double montoCambio) {
        Map<Double, Integer> denominacionesCaja = caja.getDenominaciones();
        Map<Double, Integer> cambioEntregado = new HashMap<>();
        double montoRestante = montoCambio;
    
        List<Double> denominacionesOrdenadas = new ArrayList<>(denominacionesCaja.keySet());
        denominacionesOrdenadas.sort(Collections.reverseOrder());
    
        for (double denominacion : denominacionesOrdenadas) {
            int cantidadDisponible = denominacionesCaja.getOrDefault(denominacion, 0);
            int cantidadNecesaria = (int) (montoRestante / denominacion);
    
            if (cantidadNecesaria > 0 && cantidadDisponible > 0) {
                int cantidadUsada = Math.min(cantidadNecesaria, cantidadDisponible);
                cambioEntregado.put(denominacion, cantidadUsada);
                montoRestante -= cantidadUsada * denominacion;
            }
    
            if (montoRestante <= 0) break;
        }
    
        if (montoRestante > 0) {
            return false;
        }
    
        for (Map.Entry<Double, Integer> entry : cambioEntregado.entrySet()) {
            double denominacion = entry.getKey();
            int cantidadUsada = entry.getValue();
            caja.retirarDenominacion(denominacion, cantidadUsada);
        }
    
        return true;
    }

    public void calcularCambio(double precio, Efectivo efectivoUsuario) {
    double montoPagado = efectivoUsuario.getMontoDisponible();
    double cambio = montoPagado - precio;

    if (cambio < 0) {
        vista.mostrarMensaje("Error: El monto pagado es insuficiente.");
        return;
    }

    if (!caja.entregarCambio(cambio)) {
        vista.mostrarMensaje("Error: Fondos insuficientes en la caja para entregar cambio.");
        return;
    }

    efectivoUsuario.retirarMonto(precio); 
    efectivoUsuario.agregarMonto(cambio); 
    vista.mostrarMensaje("Cambio entregado correctamente: " + cambio + "€");
    }

    
}