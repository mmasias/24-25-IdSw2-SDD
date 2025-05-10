package modelo;

public class Mesa {
    private final int numero;
    private final int capacidad;
    private final String ubicacion;
    private EstadoMesa estado;

    public Mesa(int numero, int capacidad, String ubicacion) {
        if (capacidad <= 0) throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = EstadoMesa.LIBRE;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void cambiarEstado(EstadoMesa nuevoEstado) {
        if (nuevoEstado == null) throw new IllegalArgumentException("Estado no puede ser nulo");
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Mesa " + numero + " (Capacidad: " + capacidad + ", Ubicación: " + ubicacion + ", Estado: " + estado.getTexto() + ")";
    }
}
