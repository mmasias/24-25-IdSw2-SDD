package src;

import java.util.List;

public class Usuario {
    private double efectivo;
    private double tarjetaMonedero;
    private double tarjetaBancaria;

    public Usuario() {
        Random random = new Random();
        this.efectivo = generarAleatorioConPasos(1, 50, 1);
        this.tarjetaMonedero = generarAleatorioConPasos(1, 40, 1);
        this.tarjetaBancaria = generarAleatorioConPasos(1, 40, 1);
    }

    private double generarAleatorioConPasos(int min, int max, int pasos) {
        Random random = new Random();
        int rango = ((max - min) / pasos) + 1;
        return min + (random.nextInt(rango) * pasos);
    }

    public void seleccionarMaquina(String numMaquina) {
        System.out.println("Seleccionando la máquina " + numMaquina);
    }

    public void ingresarSeleccion(String producto) {
        System.out.println("Producto seleccionado: " + producto);
    }
    
    public void elegirMetodoPago() {
        System.out.println("Elegir método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta Monedero");
        System.out.println("3. Tarjeta Bancaria");
    }

    public void ingresarEfectivo() {
        System.out.println("Ingresa el efectivo:");
        System.out.println("1. Moneda de €0,05");
        System.out.println("2. Moneda de €0,20");
        System.out.println("3. Moneda de €0,50");
        System.out.println("4. Moneda de €1");
        System.out.println("5. Moneda de €2");
        System.out.println("6. Billete de €5");
        System.out.println("7. Billete de €10");
        System.out.println("8. Billete de €20");
    }

    public void ingresarTarjeta(String tipoTarjeta) {
        System.out.println("Tarjeta ingresada: " + tipoTarjeta);
    }

    public double getEfectivo() {
        return efectivo;
    }

    public double getTarjetaMonedero() {
        return tarjetaMonedero;
    }

    public double getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void mostrarSaldos() {
        System.out.println("Saldo actual");
        System.out.println("Efectivo: €" + efectivo);
        System.out.println("Tarjeta Monedero: €" + tarjetaMonedero);
        System.out.println("Tarjeta Bancaria: €" + tarjetaBancaria);
    }
}