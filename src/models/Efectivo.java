package src.models;

public class Efectivo {
    public enum Tipo {
        MONEDA, BILLETE
    }

    private double denominacion;
    private Tipo tipo;

    public Efectivo(double denominacion, Tipo tipo) {
        this.denominacion = denominacion;
        this.tipo = tipo;
    }

    public double getDenominacion() {
        return denominacion;
    }

    public Tipo getTipo() {
        return tipo;
    }
}