package src.moduloCaja.modelo;

import java.util.HashMap;
import java.util.Map;

public class Caja {
    private double total;
    private Map<Double, Integer> denominaciones;

    public Caja(double montoInicial) {
        this.total = montoInicial;
        this.denominaciones = new HashMap<>(); 
        inicializarDenominaciones();
    }

    public void recibirPago(double monto) {
        this.total += monto;
    }

    public void agregarFondos(double monto) {
        if (monto > 0) {
            total += monto;
        } else {
            throw new IllegalArgumentException("Monto inválido para agregar.");
        }
    }

    public void retirarFondos(double monto) {
        if (monto > 0 && monto <= total) {
            total -= monto;
        } else {
            throw new IllegalArgumentException("Monto inválido para retirar.");
        }
    }

    public double retirarTodo() {
        double monto = this.total;
        this.total = 0;
        return monto;
    }

    public double getTotal() {
        return total;
    }

    public boolean entregarCambio(double monto) {
        if (monto > 0 && monto <= total) {
            total -= monto;
            return true;
        }
        return false; 
    }

    public void retirarPago(double monto) {
        if (this.total >= monto) {
            this.total -= monto;
        } else {
            this.total = 0;
        }
    }

    private void inicializarDenominaciones() {
        denominaciones.put(20.0, 2);  
        denominaciones.put(10.0, 3);  
        denominaciones.put(5.0, 5);   
        denominaciones.put(2.0, 10);  
        denominaciones.put(1.0, 20);  
        denominaciones.put(0.5, 30);  
        denominaciones.put(0.2, 50);  
        denominaciones.put(0.1, 100); 
        denominaciones.put(0.05, 200);
    
        total = denominaciones.entrySet().stream()
            .mapToDouble(entry -> entry.getKey() * entry.getValue())
            .sum();
    }

    public void mostrarDesgloseCaja() {
        System.out.println("Total disponible: " + total + "€");
        System.out.println("Denominaciones disponibles:");
        denominaciones.forEach((denominacion, cantidad) -> 
            System.out.println(denominacion + "€: " + cantidad + " unidades"));
    }

    public void agregarDenominacion(double denominacion, int cantidad) {
        denominaciones.put(denominacion, denominaciones.getOrDefault(denominacion, 0) + cantidad);
        total += denominacion * cantidad; 
    }

    public void retirarDenominacion(double denominacion, int cantidad) {
        int cantidadActual = denominaciones.getOrDefault(denominacion, 0);
        if (cantidadActual >= cantidad) {
            denominaciones.put(denominacion, cantidadActual - cantidad);
            total -= denominacion * cantidad; 
        } else {
            throw new IllegalArgumentException("Fondos insuficientes para retirar la denominación.");
        }
    }
    public Map<Double, Integer> getDenominaciones() {
        return denominaciones;
    }

    
}
