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

    public boolean pagarConEfectivo(double monto) {
        boolean exito = efectivo.pagar(monto);
        if (vistaPago != null) {
            vistaPago.mostrarMensaje(exito
                ? "Pago en efectivo exitoso."
                : "Pago insuficiente con efectivo.");
            if (exito) {
                vistaPago.mostrarMontoDisponible(efectivo.getMontoDisponible());
            }
        }
        return exito; 
    }

    public boolean pagarConTarjeta(double monto) {
        try {
            tarjeta.retirarSaldo(monto);
            vistaPago.mostrarMensaje("Pago con tarjeta exitoso.");
            return true;
        } catch (IllegalArgumentException e) {
            vistaPago.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    public void mostrarDesgloseEfectivo() {
        vistaPago.mostrarDesgloseUsuario(
            efectivo.getDenominacionesUsuario()
        );
    }

    
}
