package JuegoVampiro2.core;

import java.util.Random;

public abstract class Ataque {
    private int dañoBase;
    protected int probabilidadExito;
    private Random random;

    public Ataque(int dañoBase, int probabilidadExito) {
        this.dañoBase = dañoBase;
        this.probabilidadExito = probabilidadExito;
        this.random = new Random();
    }

    public abstract String getNombre();

    public int getDaño() {
        return dañoBase;
    }

    public boolean esExitoso() {
        return random.nextInt(100) < probabilidadExito;
    }
} 
