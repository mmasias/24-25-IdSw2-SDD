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
            System.out.println(getNombre() + " se ha desmayado por falta de energía!");
        }
    }

    public void recuperarEnergia(int cantidad) {
        energia += cantidad;
        if (energia > energiaMaxima) {
            energia = energiaMaxima;
        }
        
        if (desmayado && energia > limiteDesmayo) {
            desmayado = false;
            System.out.println(getNombre() + " se ha recuperado del desmayo!");
        }
    }

    public void recuperarTodoEnergia() {
        energia = energiaMaxima;
        desmayado = false;
    }

    public void pasarTurno() {
        if (desmayado) {
            recuperarEnergia(2);
        }
    }

    public abstract String getNombre();
    
    public abstract Ataque seleccionarAtaque();
} 