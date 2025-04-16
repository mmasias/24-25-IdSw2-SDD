package src;

public class Efectivo {
    private double denominacion;
    private EfectivoAceptado tipoEfectivo;
    private Tipo tipo;

    public enum Tipo {
        BILLETE,
        MONEDA
    }

    public Efectivo(EfectivoAceptado tipoEfectivo) {
        this.denominacion = tipoEfectivo.getValor();
        this.tipoEfectivo = tipoEfectivo;
    }

    public Efectivo(double denominacion, Tipo tipo) {
        this.denominacion = denominacion;
        this.tipo = tipo;
    }

    public void setDenominacion(double denominacion) {
        this.denominacion = denominacion;
    }

    public double getDenominacion() {
        return denominacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}