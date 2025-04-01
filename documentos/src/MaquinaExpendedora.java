package src;

import java.util.Map;

public class MaquinaExpendedora {
    private float saldo;
    private Map<String, Integer> inventario;
    private Map<String, Float> precios;

    public MaquinaExpendedora() {
        this.saldo = 0;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Map<String, Integer> getInventario() {
        return inventario;
    }

    public void setInventario(Map<String, Integer> inventario) {
        this.inventario = inventario;
    }

    public Map<String, Float> getPrecios() {
        return precios;
    }

    public void setPrecios(Map<String, Float> precios) {
        this.precios = precios;
    }
}
