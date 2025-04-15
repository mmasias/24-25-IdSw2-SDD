package modelo;


public class Mesa {
    private int numero;
    private int capacidad;
    private String ubicacion;
    private String estado;

    public Mesa(int numero, int capacidad, String ubicacion) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = "Libre";
    }

    public int getNumero() { return numero; }
    public int getCapacidad() { return capacidad; }
    public String getUbicacion() { return ubicacion; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Mesa " + numero + " (Capacidad: " + capacidad + ", Ubicación: " + ubicacion + ", Estado: " + estado + ")";
    }
}
