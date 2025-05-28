package JuegoVampiro3.core;

/**
 * Clase que representa un ataque de mordida del vampiro.
 * Principio SRP: Responsabilidad única - representar un tipo específico de ataque.
 */
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
