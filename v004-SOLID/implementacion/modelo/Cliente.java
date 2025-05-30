package implementacion.modelo;

import interfaces.modelo.ICliente;

public class Cliente implements ICliente {
    private int id;
    private long tiempoLlegada;
    private long tiempoEspera;
    private long tiempoAtencion;
    private int cantidadProductos;

    public Cliente(int id, long tiempoLlegada, int cantidadProductos) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.cantidadProductos = cantidadProductos;
        this.tiempoEspera = 0;
        this.tiempoAtencion = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public long getTiempoLlegada() {
        return tiempoLlegada;
    }

    @Override
    public long getTiempoEspera() {
        return tiempoEspera;
    }

    @Override
    public void setTiempoEspera(long tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    @Override
    public long getTiempoAtencion() {
        return tiempoAtencion;
    }

    @Override
    public void setTiempoAtencion(long tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public int getCantidadProductos() {
        return cantidadProductos;
    }

    @Override
    public void setCantidadProductos(int cantidad) {
        this.cantidadProductos = cantidad;
    }

    @Override
    public String toString() {
        return "Cliente " + id + " (" + cantidadProductos + " productos)";
    }
}
