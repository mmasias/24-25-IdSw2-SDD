package implementacion.modelo;

public class Mesa {
    private int numero;
    private int capacidad;
    private String ubicacion;
    private EstadoMesa estado;

    public Mesa(int numero, int capacidad, String ubicacion) {
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

    public EstadoMesa getEstado() {
        return estado;
    }

    public void cambiarEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    public void ocupar() {
        this.estado = EstadoMesa.OCUPADA;
    }

    public void liberar() {
        this.estado = EstadoMesa.LIBRE;
    }

    @Override
    public String toString() {
        return "Mesa #" + numero + " (" + capacidad + " personas, " + ubicacion + ") - Estado: " + estado;
    }
}
