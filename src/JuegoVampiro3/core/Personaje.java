package JuegoVampiro3.core;

import JuegoVampiro3.core.interfaces.IPersonaje;

public abstract class Personaje implements IPersonaje {
    private int energia;
    private int energiaMaxima;
    private boolean desmayado;
    private int limiteDesmayo;

    protected Personaje(int energia, int limiteDesmayo) {
        this.energia = energia;
        this.energiaMaxima = energia;
        this.limiteDesmayo = limiteDesmayo;
        this.desmayado = false;
    }

    @Override
    public int getEnergia() {
        return energia;
    }

    @Override
    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    @Override
    public boolean estaDesmayado() {
        return desmayado;
    }

    @Override
    public boolean estaVivo() {
        return energia > 0;
    }

    @Override
    public void recibirDaño(int daño) {
        if (daño < 0) return;
        
        energia -= daño;
        if (energia <= 0) {
            energia = 0;
        } else if (energia <= limiteDesmayo && !desmayado) {
            desmayado = true;
        }
    }

    @Override
    public void recuperarEnergia(int cantidad) {
        if (cantidad < 0) return;
        
        energia += cantidad;
        if (energia > energiaMaxima) {
            energia = energiaMaxima;
        }

        if (desmayado && energia > limiteDesmayo) {
            desmayado = false;
        }
    }

    public void recuperarTodoEnergia() {
        energia = energiaMaxima;
        desmayado = false;
    }

    @Override
    public void pasarTurno() {
        if (desmayado) {
            recuperarEnergia(2);
        }
    }

    @Override
    public abstract String getNombre();
    
    @Override
    public abstract Ataque seleccionarAtaque();

    protected int getLimiteDesmayo() {
        return limiteDesmayo;
    }
} 
