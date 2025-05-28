package JuegoVampiro3.core;

/**
 * Clase que representa un arma del guerrero.
 * Principio SRP: Responsabilidad única - representar un arma específica.
 */
public class Arma extends Ataque {
    private String nombre;

    public Arma(String nombre, int dañoBase, int probabilidadExito) {
        super(dañoBase, probabilidadExito);
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        // Para mostrar en la lista de armas en VistaConsola
        return nombre + " (Daño: " + getDaño() + ", Éxito: " + getProbabilidadExito() + "%)"; 
    }
} 
