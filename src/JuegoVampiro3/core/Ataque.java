package JuegoVampiro3.core;

import java.util.Random;

/**
 * Clase base abstracta para todos los tipos de ataque.
 * Principio SRP: Responsabilidad única - gestión del comportamiento básico de ataques.
 * Principio OCP: Abierta para extensión - nuevos tipos de ataque pueden heredar.
 */
public abstract class Ataque {
    private int dañoBase;
    protected int probabilidadExito; // Porcentaje (0-100)
    private Random random;

    public Ataque(int dañoBase, int probabilidadExito) {
        this.dañoBase = dañoBase;
        this.probabilidadExito = probabilidadExito;
        this.random = new Random();
    }

    public abstract String getNombre();

    public int getDaño() {
        // Podría haber variaciones en el daño aquí en el futuro
        return dañoBase;
    }

    public boolean esExitoso() {
        return random.nextInt(100) < probabilidadExito;
    }

    public int getProbabilidadExito() {
        return probabilidadExito;
    }
} 
