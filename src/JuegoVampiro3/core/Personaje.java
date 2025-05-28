package JuegoVampiro2.core;

public abstract class Personaje {
    private int energia;
    private int energiaMaxima;
    private boolean desmayado;
    private int limiteDesmayo;

    public Personaje(int energia, int limiteDesmayo) {
        this.energia = energia;
        this.energiaMaxima = energia;
        this.limiteDesmayo = limiteDesmayo;
        this.desmayado = false;
    }

    public int getEnergia() {
        return energia;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public boolean estaDesmayado() {
        return desmayado;
    }

    public boolean estaVivo() {
        return energia > 0;
    }

    public void recibirDaño(int daño) {
        energia -= daño;
        if (energia <= 0) {
            energia = 0;
        } else if (energia <= limiteDesmayo && !desmayado) {
            desmayado = true;
        }
    }

    public void recuperarEnergia(int cantidad) {
        energia += cantidad;
        if (energia > energiaMaxima) {
            energia = energiaMaxima;
        }
        
        // Si recupera suficiente energía, ya no estará desmayado
        if (desmayado && energia > limiteDesmayo) {
            desmayado = false;
        }
    }

    public void recuperarTodoEnergia() {
        energia = energiaMaxima;
        desmayado = false;
    }

    public void pasarTurno() {
        if (desmayado) {
            recuperarEnergia(2); // Recupera 2 puntos por turno si está desmayado
        }
    }

    public abstract String getNombre();
    
    public abstract Ataque seleccionarAtaque();

    public abstract Ataque seleccionarAtaque(int indice); 
} 
