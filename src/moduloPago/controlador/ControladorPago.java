package src.moduloPago.controlador;

import src.moduloPago.modelo.Efectivo;
import src.moduloPago.modelo.Tarjeta;
import src.moduloPago.vista.VistaPago;

public class ControladorPago {
    private Efectivo efectivo;
    private Tarjeta tarjeta;
    private VistaPago vistaPago;

    public ControladorPago(Efectivo efectivo, Tarjeta tarjeta, VistaPago vistaPago) {
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
        this.vistaPago = vistaPago;
    }

    public void pagarConEfectivo(double monto) {
        boolean exito = efectivo.pagar(monto);
        vistaPago.mostrarMensaje(exito
            ? "Pago en efectivo exitoso."
            : "Pago insuficiente con efectivo.");
        if (exito) {
            vistaPago.mostrarMontoDisponible(efectivo.getMontoDisponible());
        }
    }

    public void pagarConTarjeta(double monto) {
        boolean exito = tarjeta.pagar(monto);
        vistaPago.mostrarMensaje(exito
            ? "Pago con tarjeta exitoso."
            : "Pago insuficiente con tarjeta.");
        if (exito) {
            vistaPago.mostrarSaldoTarjeta(tarjeta.getSaldoDisponible());
        }
    }

    public void mostrarDesgloseEfectivo() {
        vistaPago.mostrarDesgloseDoble(
            efectivo.getDenominacionesUsuario(),
            efectivo.getDenominacionesCaja()
        );
    }
}
