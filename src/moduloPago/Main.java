package src.moduloPago;

import src.moduloCaja.Caja;
import src.moduloPago.modelo.Efectivo;
import src.moduloPago.modelo.Tarjeta;
import src.moduloPago.vista.VistaPago;
import src.moduloPago.controlador.ControladorPago;

public class Main {
    public static void main(String[] args) {
        // Crear objetos necesarios
        Caja caja = new Caja(20);
        Efectivo efectivo = new Efectivo(50.0);
        Tarjeta tarjeta = new Tarjeta("1234-5678-9876-5432", "Juan Pérez", 100.0);
        VistaPago vista = new VistaPago();

        // Agregar denominaciones al efectivo del usuario
        efectivo.agregarDenominacionUsuario(0.10, 10);
        efectivo.agregarDenominacionUsuario(1.0, 5);

        // Agregar denominaciones a la caja
        efectivo.agregarDenominacionCaja(0.10, 20);
        efectivo.agregarDenominacionCaja(5.0, 2);

        // Crear el controlador
        ControladorPago controlador = new ControladorPago(efectivo, tarjeta, vista);

        // Probar pagos
        controlador.pagarConEfectivo(20.0);   // Suficiente efectivo
        controlador.pagarConEfectivo(40.0);   // Insuficiente efectivo

        controlador.pagarConTarjeta(30.0);    // Suficiente saldo
        controlador.pagarConTarjeta(90.0);    // Insuficiente saldo

        // Mostrar desglose de efectivo
        controlador.mostrarDesgloseEfectivo();
    }
}