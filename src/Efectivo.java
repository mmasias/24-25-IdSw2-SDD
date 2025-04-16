package src;


public class Efectivo {
    private double denominacion;
    private EfectivoAceptado tipoEfectivo;

    public Efectivo(EfectivoAceptado tipoEfectivo) {
        this.denominacion = tipoEfectivo.getValor();
        this.tipoEfectivo = tipoEfectivo;
    }

    public double getDenominacion() {
        return denominacion;
    }

    public EfectivoAceptado getTipoEfectivo() {
        return tipoEfectivo;
    }

}