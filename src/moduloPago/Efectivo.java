package src.moduloPago;

import java.util.Map;
import java.util.HashMap;

import src.moduloCaja.Caja;

public class Efectivo implements Pago {
    private double montoDisponible;
    private Caja caja;
    private Map<Double, Integer> denominaciones; // Mapa para el desglose de efectivo

    public Efectivo(double montoDisponible, Caja caja) {
        this.montoDisponible = montoDisponible;
        this.caja = caja;
        this.denominaciones = new HashMap<>();
    }

    @Override
    public boolean pagar(double monto) {
        if (montoDisponible >= monto) {
            montoDisponible -= monto;
            // No sumar a la caja aquí, se debe hacer en la lógica de la máquina solo el precio real
            return true;
        }
        return false;
    }

    public double getMontoDisponible() {
        return montoDisponible;
    }

    public void agregarEfectivo(double monto) {
        this.montoDisponible += monto;
    }

    public void mostrarDesglose() {
        for (Map.Entry<Double, Integer> entry : denominaciones.entrySet()) {
            System.out.println("Denominación $" + entry.getKey() + ": " + entry.getValue() + " unidades");
        }
    }

    // Métodos auxiliares para manejar denominaciones si lo necesitas
    public void agregarDenominacion(double denominacion, int cantidad) {
        denominaciones.put(denominacion, denominaciones.getOrDefault(denominacion, 0) + cantidad);
    }

    public void quitarDenominacion(double denominacion, int cantidad) {
        if (denominaciones.containsKey(denominacion)) {
            int actual = denominaciones.get(denominacion);
            if (actual >= cantidad) {
                denominaciones.put(denominacion, actual - cantidad);
            }
        }
    }
}
