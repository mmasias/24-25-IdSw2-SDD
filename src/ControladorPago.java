package src;

public class ControladorPago {

    PagoEfectivo pagoEfectivo;
    PagoTarjeta pagoTarjeta;
    public boolean pagoRealizado = false;

    public ControladorPago() {
        this.pagoEfectivo = null;
        this.pagoTarjeta = null;
    }

    public void iniciarPagoEfectivo(double monto) {
        this.pagoEfectivo = new PagoEfectivo(monto);
    }

    public void agregarEfectivo(Efectivo efectivo) {
        if (pagoEfectivo == null) {
            System.out.println("Inicie el pago en efectivo primero.");
            return;
        }
        pagoEfectivo.ingresarEfectivo(efectivo);
        if (pagoEfectivo.validarEfectivo()) {
            pagoRealizado = true;
            System.out.println("Pago en efectivo validado.");
        } else {
            pagoRealizado = false;
            System.out.println("Monto insuficiente.");
        }
    }

    public void realizarPagoTarjeta(Tarjeta tarjeta, double monto) {
        this.pagoTarjeta = new PagoTarjeta(tarjeta, monto);
        if (pagoTarjeta.realizarPago()) {
            System.out.println("Pago con tarjeta realizado exitosamente.");
        } else {
            System.out.println("Fondos insuficientes en la tarjeta.");
        }
    }

}
