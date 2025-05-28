package src.repositories;

import src.models.Caja;
import src.models.Efectivo;

import java.util.HashMap;
import java.util.Map;

public class CajaRepository {
    private Map<Double, Integer> monedasTotales;
    private Map<Double, Integer> billetesTotales;
    private Map<String, Caja> cajas;

    public CajaRepository() {
        this.monedasTotales = new HashMap<>();
        this.billetesTotales = new HashMap<>();
        this.cajas = new HashMap<>();
    }

    public void agregarCaja(String idMaquina, Caja caja) {
        cajas.put(idMaquina, caja);
    }

    public Map<String, Caja> obtenerCajas() {
        return cajas;
    }

    public void agregarEfectivo(Efectivo efectivo) {
        if (efectivo.getTipo() == Efectivo.Tipo.MONEDA) {
            monedasTotales.put(efectivo.getDenominacion(),
                monedasTotales.getOrDefault(efectivo.getDenominacion(), 0) + 1);
        } else if (efectivo.getTipo() == Efectivo.Tipo.BILLETE) {
            billetesTotales.put(efectivo.getDenominacion(),
                billetesTotales.getOrDefault(efectivo.getDenominacion(), 0) + 1);
        }
    }
    

    public Map<Double, Integer> obtenerMonedas() {
        return monedasTotales;
    }

    public Map<Double, Integer> obtenerBilletes() {
        return billetesTotales;
    }


    public void actualizarEfectivo(double denominacion, int cantidad, Efectivo.Tipo tipo) {
        if (tipo == Efectivo.Tipo.MONEDA) {
            monedasTotales.put(denominacion, cantidad);
        } else if (tipo == Efectivo.Tipo.BILLETE) {
            billetesTotales.put(denominacion, cantidad);
        }
    }
}