package JuegoVampiro3.core;

import JuegoVampiro3.core.interfaces.ILuchador;
import java.util.ArrayList;
import java.util.List;

public class Guerrero extends Personaje implements ILuchador {
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
        inicializarArmas();
    }

    private void inicializarArmas() {
        armas.add(new Arma("Espada", 7, 50));
        armas.add(new Arma("Hacha", 15, 25));
        armas.add(new Arma("Martillo", 30, 12));
    }

    @Override
    public String getNombre() {
        return "Héroe";
    }

    @Override
    public Ataque seleccionarAtaque() {
        return seleccionarAtaque(0);
    }

    @Override
    public Ataque seleccionarAtaque(int indiceArma) {
        if (!puedeAtacar() || indiceArma < 0 || indiceArma >= armas.size()) {
            return null;
        }
        return armas.get(indiceArma);
    }

    @Override
    public boolean puedeAtacar() {
        return !estaDesmayado() && !tienePocionActiva();
    }

    public List<Arma> getArmas() {
        return new ArrayList<>(armas);
    }

    public boolean estaDefendiendo() {
        return defendiendo;
    }

    public boolean tienePocionActiva() {
        return pocion.EstaEnUso();
    }

    public Pocion getPocion() {
        return pocion;
    }

    public void beberPocion() {
        if (!tienePocionActiva() && !estaDesmayado()) {
            pocion.beber();
        }
    }

    public boolean avanzarTurnoPocion() {
        boolean pocionCompletada = pocion.avanzarTurno();
        if (pocionCompletada) {
            recuperarTodoEnergia();
        }
        return pocionCompletada;
    }

    public void defender() {
        if (!estaDesmayado() && !tienePocionActiva()) {
            this.defendiendo = true;
        }
    }

    public void finalizarDefensa() {
        this.defendiendo = false;
    }

    public int reducirDañoRecibido(int daño) {
        if (defendiendo && Math.random() < 0.8) {
            return Math.max(0, daño - 5);
        }
        return daño;
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
