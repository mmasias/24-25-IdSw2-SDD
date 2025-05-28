package src.models;

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
        return dineroTotal;
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

    public Map<Double, Integer> devolverCambio(double cambio) {
        Map<Double, Integer> cambioEntregado = new HashMap<>();
        double restante = cambio;

        for (Map.Entry<Double, Integer> entrada : monedasTotales.entrySet()) {
            double denominacion = entrada.getKey();
            int cantidad = entrada.getValue();

            while (restante >= denominacion && cantidad > 0) {
                restante -= denominacion;
                cantidad--;
                cambioEntregado.put(denominacion, cambioEntregado.getOrDefault(denominacion, 0) + 1);
            }
        }

        if (restante > 0) {
            throw new IllegalArgumentException("No se puede devolver el cambio exacto.");
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
}