package JuegoVampiro2.core;

public class Arma extends Ataque {
    private String nombre;

    public Arma(String nombre, int dañoBase, int probabilidadExito) {
        super(dañoBase, probabilidadExito);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        // Para mostrar en la lista de armas en VistaConsola
        return nombre + " (Daño: " + getDaño() + ", Éxito: " + super.probabilidadExito + "%)"; 
        // Acceder a probabilidadExito directamente o hacerla protected/añadir getter en Ataque
    }
} 
