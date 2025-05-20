package implementacion.modelo;

import interfaces.modelo.ICliente;

public class Cliente implements ICliente {
    private int id;
    private long tiempoLlegada;
    private long tiempoInicioAtencion;
    private long tiempoAtencion;
    private boolean atendido;

    public Cliente(int id, long tiempoLlegada) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.atendido = false;
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
        if (tiempoInicioAtencion == 0) {
            return 0; 
        }
        return tiempoInicioAtencion - tiempoLlegada;
    }

    @Override
    public long getTiempoAtencion() {
        return tiempoAtencion;
    }

    @Override
    public void setTiempoAtencion(long tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
        this.atendido = true;
    }

    @Override
    public void setTiempoInicioAtencion(long tiempoInicioAtencion) {
        this.tiempoInicioAtencion = tiempoInicioAtencion;
    }

    @Override
    public boolean estaAtendido() {
        return atendido;
    }
}