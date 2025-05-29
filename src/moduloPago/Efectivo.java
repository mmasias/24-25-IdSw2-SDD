package src.moduloPago;

import src.moduloCaja.Caja;

public class Efectivo implements Pago {
    private double montoDisponible;
    private Caja caja;

    public Efectivo(double montoDisponible, Caja caja) {
        this.montoDisponible = montoDisponible;
        this.caja = caja;
    }

    @Override
    public boolean pagar(double monto) {
        if (montoDisponible >= monto) {
            montoDisponible -= monto;
            caja.recibirPago(monto);
            return true;
        }
        return false;
    }

    public double getMontoDisponible() {
        return montoDisponible;
    }

    public void agregarEfectivo(double monto) {
        this.montoDisponible += monto;
    }
}
