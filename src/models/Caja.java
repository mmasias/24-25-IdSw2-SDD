package src.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Caja {
    private String idMaquina;
    private Map<Double, Integer> monedasTotales;
    private Map<Double, Integer> billetesTotales;
    private double dineroTotal;

    public Caja(String idMaquina) {
        this.idMaquina = idMaquina;
        this.monedasTotales = new HashMap<>();
        this.billetesTotales = new HashMap<>();
        this.dineroTotal = 0.0;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public double calcularDineroTotal() {
        double total = 0.0;
        for (Map.Entry<Double, Integer> entrada : monedasTotales.entrySet()) {
            total += entrada.getKey() * entrada.getValue();
        }
        for (Map.Entry<Double, Integer> entrada : billetesTotales.entrySet()) {
            total += entrada.getKey() * entrada.getValue();
        }
        return total;
    }

    public void actualizarCaja(Efectivo efectivo, int cantidad) {
        if (efectivo.getTipo() == Efectivo.Tipo.MONEDA) {
            monedasTotales.put(efectivo.getDenominacion(),
                monedasTotales.getOrDefault(efectivo.getDenominacion(), 0) + cantidad);
        } else if (efectivo.getTipo() == Efectivo.Tipo.BILLETE) {
            billetesTotales.put(efectivo.getDenominacion(),
                billetesTotales.getOrDefault(efectivo.getDenominacion(), 0) + cantidad);
        }
    }

    public Map<Double, Integer> devolverCambio(double cambio) {
        Map<Double, Integer> cambioEntregado = new HashMap<>();
        BigDecimal restante = BigDecimal.valueOf(cambio);
    
        // Intentar devolver cambio con monedas primero
        for (Map.Entry<Double, Integer> entrada : monedasTotales.entrySet()) {
            BigDecimal denominacion = BigDecimal.valueOf(entrada.getKey());
            int cantidad = entrada.getValue();
    
            while (restante.compareTo(denominacion) >= 0 && cantidad > 0) {
                restante = restante.subtract(denominacion);
                cantidad--;
                cambioEntregado.put(denominacion.doubleValue(), cambioEntregado.getOrDefault(denominacion.doubleValue(), 0) + 1);
            }
        }
    
        // Intentar devolver cambio con billetes si aún queda restante
        for (Map.Entry<Double, Integer> entrada : billetesTotales.entrySet()) {
            BigDecimal denominacion = BigDecimal.valueOf(entrada.getKey());
            int cantidad = entrada.getValue();
    
            while (restante.compareTo(denominacion) >= 0 && cantidad > 0) {
                restante = restante.subtract(denominacion);
                cantidad--;
                cambioEntregado.put(denominacion.doubleValue(), cambioEntregado.getOrDefault(denominacion.doubleValue(), 0) + 1);
            }
        }
    
        if (restante.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente en caja para dar cambio.");
        }
    
        // Actualizar la caja con el cambio entregado
        for (Map.Entry<Double, Integer> entrada : cambioEntregado.entrySet()) {
            double denominacion = entrada.getKey();
            int cantidad = entrada.getValue();
    
            if (monedasTotales.containsKey(denominacion)) {
                monedasTotales.put(denominacion, monedasTotales.get(denominacion) - cantidad);
            } else if (billetesTotales.containsKey(denominacion)) {
                billetesTotales.put(denominacion, billetesTotales.get(denominacion) - cantidad);
            }
        }
    
        return cambioEntregado;
    }

    public void mostrarContenidoCaja() {
        System.out.println("Contenido de la caja:");
        System.out.println("Monedas:");
        monedasTotales.forEach((denominacion, cantidad) ->
            System.out.println("Denominación: " + denominacion + " - Cantidad: " + cantidad));
        System.out.println("Billetes:");
        billetesTotales.forEach((denominacion, cantidad) ->
            System.out.println("Denominación: " + denominacion + " - Cantidad: " + cantidad));
        System.out.println("Dinero total en la caja: €" + dineroTotal);
    }

    public Map<Double, Integer> obtenerMonedas() {
        return monedasTotales;
    }

    public Map<Double, Integer> obtenerBilletes() {
        return billetesTotales;
    }
}