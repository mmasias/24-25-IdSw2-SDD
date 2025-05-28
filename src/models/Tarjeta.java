package src.models;

public class Tarjeta {
    public enum Tipo {
        MONEDERO, BANCARIA
    }

    private Tipo tipo;
    private double saldo;

    public Tarjeta(Tipo tipo, double saldo) {
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void descontarSaldo(double monto) {
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= monto;
    }
}