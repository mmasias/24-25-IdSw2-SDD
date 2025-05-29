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
        return nombre + " (Daño: " + getDaño() + ", Éxito: " + super.probabilidadExito + "%)"; 
    }
} 
