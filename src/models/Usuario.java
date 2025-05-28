package src.models;

import java.util.HashMap;
import java.util.Map;

import src.utils.ScannerUtils;

public class Usuario {
    private Map<Double, Integer> efectivo;
    private Tarjeta tarjetaMonedero;
    private Tarjeta tarjetaBancaria;

    public Usuario() {
        this.efectivo = new HashMap<>();
        this.tarjetaMonedero = new Tarjeta(Tarjeta.Tipo.MONEDERO, 50.0);
        this.tarjetaBancaria = new Tarjeta(Tarjeta.Tipo.BANCARIA, 100.0);
    }

    public Map<Double, Integer> getEfectivo() {
        return efectivo;
    }

    public Tarjeta getTarjetaMonedero() {
        return tarjetaMonedero;
    }

    public Tarjeta getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void mostrarSaldos() {
        System.out.println("Saldo en tarjeta monedero: €" + tarjetaMonedero.getSaldo());
        System.out.println("Saldo en tarjeta bancaria: €" + tarjetaBancaria.getSaldo());
    }

    public void agregarEfectivo(double denominacion, int cantidad) {
        efectivo.put(denominacion, efectivo.getOrDefault(denominacion, 0) + cantidad);
    }

    public void descontarSaldoMonedero(double monto) {
        tarjetaMonedero.descontarSaldo(monto);
    }

    public void descontarSaldoBancario(double monto) {
        tarjetaBancaria.descontarSaldo(monto);
    }
    public double ingresarEfectivo() {
    return ScannerUtils.leerDouble("Ingrese una denominación de efectivo: ");
    }
    public void actualizarEfectivoConCambio(Map<Double, Integer> efectivoUsado, Map<Double, Integer> cambioEntregado) {
        // Restar el efectivo usado
        for (Map.Entry<Double, Integer> entrada : efectivoUsado.entrySet()) {
            double denominacion = entrada.getKey();
            int cantidad = entrada.getValue();
            efectivo.put(denominacion, efectivo.getOrDefault(denominacion, 0) - cantidad);
        }
    
        // Agregar el cambio entregado
        for (Map.Entry<Double, Integer> entrada : cambioEntregado.entrySet()) {
            double denominacion = entrada.getKey();
            int cantidad = entrada.getValue();
            efectivo.put(denominacion, efectivo.getOrDefault(denominacion, 0) + cantidad);
        }
    }
}