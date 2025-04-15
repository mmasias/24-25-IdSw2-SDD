package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Usuario {
    private Map<Double, Integer> efectivo;
    private double tarjetaMonedero;
    private double tarjetaBancaria;

    public Usuario() {
        this.efectivo = new HashMap<>();
        inicializarEfectivo();
        this.tarjetaMonedero = generarSaldoInicial(1, 40, 1);
        this.tarjetaBancaria = generarSaldoInicial(1, 40, 1);
    }

    private void inicializarEfectivo() {
        double[] denominaciones = {0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0};
        for (double denom : denominaciones) {
            efectivo.put(denom, (int) generarSaldoInicial(0, 10, 1));
        }
    }

    private double generarSaldoInicial(int min, int max, int pasos) {
        Random random = new Random();
        int rango = ((max - min) / pasos) + 1;
        return min + (random.nextInt(rango) * pasos);
    }

    public boolean seleccionarMaquina(String numMaquina) {
        return numMaquina != null && !numMaquina.isEmpty();
    }

    public boolean ingresarSeleccion(String producto) {
        return producto != null && !producto.isEmpty();
    }
    
    public String elegirMetodoPago() {
        return "EFECTIVO";
    }

    public boolean ingresarEfectivo(double denominacion) {
        return efectivo.containsKey(denominacion);
    }

    public boolean ingresarTarjeta(String tipoTarjeta) {
        return tipoTarjeta != null && (tipoTarjeta.equals("MONEDERO") || tipoTarjeta.equals("BANCARIA"));
    }

    public Map<Double, Integer> getEfectivo() {
        return new HashMap<>(efectivo);
    }

    public double getTarjetaMonedero() {
        return tarjetaMonedero;
    }

    public double getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void mostrarSaldos() {
        System.out.println("Saldo actual");
        System.out.println("Efectivo:");
        for (Map.Entry<Double, Integer> entry : efectivo.entrySet()) {
            System.out.println("Denominación: €" + entry.getKey() + " - Cantidad: " + entry.getValue());
        }
        System.out.println("Tarjeta Monedero: €" + tarjetaMonedero);
        System.out.println("Tarjeta Bancaria: €" + tarjetaBancaria);
    }
}