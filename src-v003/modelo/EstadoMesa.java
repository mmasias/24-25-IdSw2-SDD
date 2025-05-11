package modelo;

public enum EstadoMesa {
    LIBRE("Libre"),
    RESERVADA("Reservada"),
    OCUPADA("Ocupada");

    private final String texto;

    EstadoMesa(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
