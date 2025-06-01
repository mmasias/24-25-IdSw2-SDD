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

    public Map<Double, Integer> entregarCambio(double montoCambio) {
        Map<Double, Integer> denominacionesCaja = caja.getDenominaciones();
        Map<Double, Integer> cambioEntregado = new HashMap<>();
        int montoRestante = (int) Math.round(montoCambio * 100);

        List<Double> denominacionesOrdenadas = new ArrayList<>(denominacionesCaja.keySet());
        denominacionesOrdenadas.sort(Collections.reverseOrder());

        for (double denominacion : denominacionesOrdenadas) {
            int denominacionCent = (int) Math.round(denominacion * 100);
            int cantidadDisponible = denominacionesCaja.getOrDefault(denominacion, 0);
            int cantidadNecesaria = montoRestante / denominacionCent;

            if (cantidadNecesaria > 0 && cantidadDisponible > 0) {
                int cantidadUsada = Math.min(cantidadNecesaria, cantidadDisponible);
                cambioEntregado.put(denominacion, cantidadUsada);
                montoRestante -= cantidadUsada * denominacionCent;
            }

            if (montoRestante == 0)
                break;
        }

        if (montoRestante != 0) {
            // No se puede entregar el cambio exacto
            return new HashMap<>();
        }

        // Descontar las monedas/billetes de la caja
        for (Map.Entry<Double, Integer> entry : cambioEntregado.entrySet()) {
            caja.retirarDenominacion(entry.getKey(), entry.getValue());
        }

        return cambioEntregado;
    }

    public void mostrarDesgloseCaja() {
        System.out.println("Total disponible: " + caja.getTotal() + "€");
        System.out.println("Denominaciones disponibles:");
        caja.getDenominaciones()
                .forEach((denominacion, cantidad) -> System.out.println(denominacion + "€: " + cantidad + " unidades"));
    }
}