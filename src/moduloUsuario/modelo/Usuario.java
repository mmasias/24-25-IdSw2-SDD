package src.moduloUsuario.modelo;

import src.moduloPago.Efectivo;
import src.moduloPago.Tarjeta;

public class Usuario {
    private Efectivo efectivo;
    private Tarjeta tarjeta;

    public Usuario(Efectivo efectivo, Tarjeta tarjeta) {
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
    }

    public Efectivo getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Efectivo efectivo) {
        this.efectivo = efectivo;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}