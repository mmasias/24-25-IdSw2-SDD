package src;

public class PagoTarjeta {
    
    private Tarjeta tarjeta;
    private double monto;
    private String tipoPago;

    public PagoTarjeta(Tarjeta tarjeta, double monto) {
        this.tarjeta = tarjeta;
        this.monto = monto;
        this.tipoPago = tarjeta.getTipoTarjeta();
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public boolean realizarPago() {
        if (tarjeta.getSaldo() >= monto) {
            tarjeta.setSaldo(tarjeta.getSaldo() - monto);
            return true; // Pago exitoso
        } else {
            return false; // Fondos insuficientes
        }
    }
}
