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
    public void retirarSaldo(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        if (monto > saldoDisponible) {
            throw new IllegalArgumentException("Saldo insuficiente en la tarjeta.");
        }
        saldoDisponible -= monto;
    }

    @Override
    public String toString() {
        return "Tarjeta [Número: " + numero + ", Titular: " + titular + ", Saldo disponible: " + saldoDisponible + "€]";
    }
}
