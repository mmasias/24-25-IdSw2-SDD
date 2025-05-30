package src.moduloPago.modelo;

public class Tarjeta implements Pago {
    private String numero;
    private String titular;
    private double saldoDisponible;

    public Tarjeta(String numero, String titular, double saldoDisponible) {
        this.numero = numero;
        this.titular = titular;
        this.saldoDisponible = saldoDisponible;
    }

    @Override
    public boolean pagar(double monto) {
        if (saldoDisponible >= monto) {
            saldoDisponible -= monto;
            return true;
        }
        return false;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void recargar(double monto) {
        this.saldoDisponible += monto;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
