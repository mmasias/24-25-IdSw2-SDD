package JuegoVampiro3.core;

public class Mordida extends Ataque {
    private String nombre;

    public Mordida(String nombre, int daño, int probabilidadExito) {
        super(daño, probabilidadExito);
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
} 
