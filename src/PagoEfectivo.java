package src;

import java.util.ArrayList;
import java.util.List;

public class PagoEfectivo {
    
    private double monto;
    private List<Efectivo> listaEfectivo;
    private double cambio;

    public PagoEfectivo(double monto) {
        this.monto = monto;
        this.listaEfectivo = new ArrayList<Efectivo>();
    }

    public double getMonto() {
        return monto;
    }
    
    public List<Efectivo> getListaEfectivo() {
        return listaEfectivo;
    }

    public double getCambio() {
        return cambio;
    }

    public void ingresarEfectivo(Efectivo efectivo) {
        listaEfectivo.add(efectivo);
    }

    public boolean validarEfectivo() {
        double total = 0;
        for (Efectivo efectivo : listaEfectivo) {
            total += efectivo.getDenominacion();
            cambio = total - monto;
            if (cambio < 0) {
                return false;
            }
        }
        if (total >= monto) {
            return true;
        }
        return false;
    }
}