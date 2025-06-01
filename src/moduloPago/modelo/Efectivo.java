package src.moduloPago.modelo;

import java.util.Map;
import java.util.HashMap;


public class Efectivo implements Pago {
    private Double cantidad;
    private double montoDisponible;
    private Map<Double, Integer> denominacionesUsuario; 
    private Map<Double, Integer> denominacionesCaja; 

    public Efectivo(double montoDisponible) {
        this.montoDisponible = montoDisponible;
        this.denominacionesUsuario = new HashMap<>();
        this.denominacionesCaja = new HashMap<>();
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

    public Map<Double, Integer> getDenominacionesUsuario() {
        return denominacionesUsuario;
    }

    public Map<Double, Integer> getDenominacionesCaja() {
        return denominacionesCaja;
    }

    public void mostrarDesgloseUsuario() {
        System.out.println("Desglose de denominaciones del usuario:");
        for (Map.Entry<Double, Integer> entry : denominacionesUsuario.entrySet()) {
            System.out.println("Denominación $" + entry.getKey() + ": " + entry.getValue() + " unidades");
        }
        System.out.println("------------------------------");
    }

    public void mostrarDesgloseCaja() {
        System.out.println("Desglose de denominaciones de caja:");
        for (Map.Entry<Double, Integer> entry : denominacionesCaja.entrySet()) {
            System.out.println("Denominación $" + entry.getKey() + ": " + entry.getValue() + " unidades");
        }
        System.out.println("------------------------------");
    }

    public void agregarDenominacionUsuario(double denominacion, int cantidad) {
        denominacionesUsuario.put(denominacion, denominacionesUsuario.getOrDefault(denominacion, 0) + cantidad);
    }

    public void agregarDenominacionCaja(double denominacion, int cantidad) {
        denominacionesCaja.put(denominacion, denominacionesCaja.getOrDefault(denominacion, 0) + cantidad);
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
