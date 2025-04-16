package src;


public class Efectivo {
    private double denominacion;
    private Tipo tipo;

    public enum Tipo {
        BILLETE,
        MONEDA
    }

    public Efectivo(double denominacion, Tipo tipo) {
        this.denominacion = denominacion;
        this.tipo = tipo;
    }

    public double getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(double denominacion) {
        this.denominacion = denominacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}