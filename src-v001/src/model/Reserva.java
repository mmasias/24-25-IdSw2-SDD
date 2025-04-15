package model;

import java.util.Date;

public class Reserva {
    private String cliente;
    private Date fecha;
    private int numeroComensales;
    private String preferencias;
    private Mesa mesaAsignada;

    public Reserva(String cliente, Date fecha, int numeroComensales, String preferencias) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.numeroComensales = numeroComensales;
        this.preferencias = preferencias;
    }

    public void asignarMesa(Mesa mesa) {
        this.mesaAsignada = mesa;
        mesa.setEstado("Reservada");
    }

    public String getCliente() { return cliente; }
    public Date getFecha() { return fecha; }
    public int getNumeroComensales() { return numeroComensales; }
    public String getPreferencias() { return preferencias; }
    public Mesa getMesaAsignada() { return mesaAsignada; }

    @Override
    public String toString() {
        return "Reserva para " + cliente + " el " + fecha + " (" + numeroComensales + " personas) - Mesa asignada: " 
               + (mesaAsignada != null ? mesaAsignada.getNumero() : "Pendiente");
    }
}
