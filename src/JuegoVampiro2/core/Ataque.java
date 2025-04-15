package com.JuegoVampiro2.core;

import java.util.Random;

public abstract class Ataque {
    private int dañoBase;
    protected int probabilidadExito; // Porcentaje (0-100)
    private Random random;

    public Ataque(int dañoBase, int probabilidadExito) {
        this.dañoBase = dañoBase;
        this.probabilidadExito = probabilidadExito;
        this.random = new Random();
    }

    public int getDaño() {
        // Podría haber variaciones en el daño aquí en el futuro
        return dañoBase;
    }

    public boolean esExitoso() {
        return random.nextInt(100) < probabilidadExito;
    }
} 
