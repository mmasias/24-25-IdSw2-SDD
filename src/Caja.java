package src;

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

    public void actualizarCaja(Efectivo efectivo) {
        if (efectivo.getTipo() == Efectivo.Tipo.MONEDA) {
            monedasTotales.put(efectivo.getDenominacion(),
                monedasTotales.getOrDefault(efectivo.getDenominacion(), 0) + 1);
        } else if (efectivo.getTipo() == Efectivo.Tipo.BILLETE) {
            billetesTotales.put(efectivo.getDenominacion(),
                billetesTotales.getOrDefault(efectivo.getDenominacion(), 0) + 1);
        }
        dineroTotal += efectivo.getDenominacion();
    }

    public void mostrarContenidoCaja() {
        System.out.println("Contenido de la caja:");
        System.out.println("Monedas:");
        for (Map.Entry<Double, Integer> entry : monedasTotales.entrySet()) {
            System.out.println("Denominación: " + entry.getKey() + " - Cantidad: " + entry.getValue());
        }
        System.out.println("Billetes:");
        for (Map.Entry<Double, Integer> entry : billetesTotales.entrySet()) {
            System.out.println("Denominación: " + entry.getKey() + " - Cantidad: " + entry.getValue());
        }
        System.out.println("Dinero total en la caja: " + calcularDineroTotal());
    }

    public Map<Double, Integer> devolverCambio(double cambio) {
        Map<Double, Integer> cambioEntregado = new HashMap<>();

        for (Double denominacion : monedasTotales.keySet().stream().sorted((a, b) -> Double.compare(b, a)).toList()) {
            while (cambio >= denominacion && monedasTotales.get(denominacion) > 0) {
                cambio -= denominacion;
                cambio = Math.round(cambio * 100.0) / 100.0; 
                monedasTotales.put(denominacion, monedasTotales.get(denominacion) - 1);
                cambioEntregado.put(denominacion, cambioEntregado.getOrDefault(denominacion, 0) + 1);
                dineroTotal -= denominacion; 
            }
        }

        for (Double denominacion : billetesTotales.keySet().stream().sorted((a, b) -> Double.compare(b, a)).toList()) {
            while (cambio >= denominacion && billetesTotales.get(denominacion) > 0) {
                cambio -= denominacion;
                cambio = Math.round(cambio * 100.0) / 100.0; 
                billetesTotales.put(denominacion, billetesTotales.get(denominacion) - 1);
                cambioEntregado.put(denominacion, cambioEntregado.getOrDefault(denominacion, 0) + 1);
                dineroTotal -= denominacion; 
            }
        }

        if (cambio > 0) {
            System.out.println("No se pudo devolver el cambio exacto. Faltante: " + cambio);
        }

        return cambioEntregado;
    }

    public double calcularDineroTotal() {
        double total = 0.0;

        for (Map.Entry<Double, Integer> entry : monedasTotales.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }

        for (Map.Entry<Double, Integer> entry : billetesTotales.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }

        return total;
    }
}