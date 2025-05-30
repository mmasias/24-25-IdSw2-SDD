package src.moduloPago;

import java.util.Map;

import src.moduloCaja.modelo.Caja;

import java.util.HashMap;

public class Efectivo implements Pago {
    private double montoDisponible;
    private Caja caja;
    private Map<Double, Integer> denominaciones; 

    public Efectivo(double montoDisponible, Caja caja) {
        this.montoDisponible = montoDisponible;
        this.caja = caja;
        this.denominaciones = new HashMap<>();
    }

    @Override
    public boolean pagar(double monto) {
        if (montoDisponible >= monto) {
            montoDisponible -= monto;
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
