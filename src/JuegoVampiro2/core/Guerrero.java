package com.JuegoVampiro2.core;

import java.util.ArrayList;
import java.util.List;

public class Guerrero extends Personaje {
    private List<Arma> armas;
    private Pocion pocion;
    private boolean defendiendo;
    private static final int LIMITE_DESMAYO = 30;
    private int accionActual = 0;

    public Guerrero(int energia) {
        super(energia, LIMITE_DESMAYO);
        this.armas = new ArrayList<>();
        this.pocion = new Pocion(this.getEnergiaMaxima()); 
        this.defendiendo = false;
        
        armas.add(new Arma("Espada", 7, 50));
        armas.add(new Arma("Hacha", 15, 25));
        armas.add(new Arma("Martillo", 30, 12));
    }

    @Override
    public String getNombre() {
        return "Héroe";
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public boolean estaDefendiendo() {
        return defendiendo;
    }


    public boolean tienePocionActiva() {
        return pocion.estaEnUso();
    }

    public Pocion getPocion() {
        return pocion;
    }

    public void beberPocion() {
        pocion.beber();
    }

    public boolean avanzarTurnoPocion() {
        boolean pocionCompletada = pocion.avanzarTurno();
        if (pocionCompletada) {
            recuperarTodoEnergia();
        }
        return pocionCompletada;
    }

    public void defender() {
        this.defendiendo = true;
    }

    public void finalizarDefensa() {
        this.defendiendo = false;
    }

    public int reducirDañoRecibido(int daño) {
        if (defendiendo) {
            if (Math.random() < 0.8) {
                int dañoReducido = Math.max(0, daño - 5);
                return dañoReducido;
            } else {
            }
        }
        return daño;
    }

    @Override
    public Ataque seleccionarAtaque(int indiceArma) {
        if (estaDesmayado() || tienePocionActiva() || indiceArma < 0 || indiceArma >= armas.size()) {
            return null;
        }
        return armas.get(indiceArma);
    }
    

    @Override
    public Ataque seleccionarAtaque() {
         throw new UnsupportedOperationException("Usar seleccionarAtaque(int indiceArma) para el jugador.");
    }

    public void setAccionActual(int accion) {
        if (!estaDesmayado() && !tienePocionActiva()) {
            this.accionActual = accion;
        }
    }

    public void resetAccionActual() {
        this.accionActual = 0;
        if (!quiereDefenderse()) {
             finalizarDefensa();
        }
    }

    public boolean quiereAtacar() {
        return accionActual == 1;
    }

    public boolean quiereDefenderse() {
        return accionActual == 2;
    }

    public boolean quiereBeberPocion() {
        return accionActual == 3;
    }
    
    @Override
    public void pasarTurno() {
        super.pasarTurno();
    }
} 
