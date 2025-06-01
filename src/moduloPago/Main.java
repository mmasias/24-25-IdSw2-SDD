package src.moduloPago;

import src.moduloCaja.modelo.Caja;
import src.moduloPago.modelo.Efectivo;
import src.moduloPago.modelo.Tarjeta;
import src.moduloPago.vista.VistaPago;
import src.moduloPago.controlador.ControladorPago;

public class Main {
    public static void main(String[] args) {
        Caja caja = new Caja(20);
        Efectivo efectivo = new Efectivo(50.0);
        Tarjeta tarjeta = new Tarjeta("1234-5678-9876-5432", "Juan Pérez", 100.0);
        VistaPago vista = new VistaPago();

        efectivo.agregarDenominacionUsuario(0.10, 10);
        efectivo.agregarDenominacionUsuario(1.0, 5);

        efectivo.agregarDenominacionCaja(0.10, 20);
        efectivo.agregarDenominacionCaja(5.0, 2);

        ControladorPago controlador = new ControladorPago(efectivo, tarjeta, vista);

        controlador.pagarConEfectivo(20.0);
        controlador.pagarConEfectivo(40.0);

        controlador.pagarConTarjeta(30.0);
        controlador.pagarConTarjeta(90.0);

        controlador.mostrarDesgloseEfectivo();
    }
}