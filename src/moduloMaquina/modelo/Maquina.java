package src.moduloMaquina.modelo;

import src.moduloInventario.modelo.Celda;
import src.moduloCaja.modelo.Caja;

import java.util.List;

public class Maquina {
    private List<Celda> celdas;
    private Caja caja;

    public Maquina(List<Celda> celdas, Caja caja) {
        this.celdas = celdas;
        this.caja = caja;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public Caja getCaja() {
        return caja;
    }
}
