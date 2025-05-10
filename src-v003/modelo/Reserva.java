package modelo;

import java.util.Date;

public class Reserva {
    private final String cliente;
    private final Date fecha;
    private final int numeroComensales;
    private final String preferencias;
    private Mesa mesaAsignada;

    public Reserva(String cliente, Date fecha, int numeroComensales, String preferencias) {
        if (cliente == null || cliente.isBlank()) throw new IllegalArgumentException("Cliente no puede ser vacío");
        this.cliente = cliente;
        this.fecha = fecha;
        this.numeroComensales = numeroComensales;
        this.preferencias = preferencias;
    }

    public void asignarMesa(Mesa mesa) {
        if (mesa == null) throw new IllegalArgumentException("Mesa no puede ser nula");
        mesa.cambiarEstado(EstadoMesa.RESERVADA);
        this.mesaAsignada = mesa;
    }

    public String getCliente() {
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNumeroComensales() {
        return numeroComensales;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public Mesa getMesaAsignada() {
        return mesaAsignada;
    }

    @Override
    public String toString() {
        return "Reserva para " + cliente + " el " + fecha + " (" + numeroComensales + " personas) - Mesa asignada: " +
                (mesaAsignada != null ? mesaAsignada.getNumero() : "Pendiente");
    }
}
