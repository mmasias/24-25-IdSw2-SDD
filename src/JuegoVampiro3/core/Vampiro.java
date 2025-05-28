package JuegoVampiro3.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que representa al vampiro enemigo.
 * Principio SRP: Responsabilidad única - gestión del estado y comportamiento del vampiro.
 * Principio LSP: Implementación coherente de IPersonaje.
 */
public class Vampiro extends Personaje {
    private List<Mordida> ataques;
    private static final int LIMITE_DESMAYO = 20;
    private Random random;

    public Vampiro(int energia) {
        super(energia, LIMITE_DESMAYO);
        this.ataques = new ArrayList<>();
        this.random = new Random();
        inicializarAtaques();
    }

    private void inicializarAtaques() {
        ataques.add(new Mordida("Mordida leve", 5, 90));
        ataques.add(new Mordida("Mordida moderada", 10, 60));
        ataques.add(new Mordida("Mordida fuerte", 20, 40));
    }

    @Override
    public String getNombre() {
        return "Vampiro";
    }

    public List<Mordida> getAtaques() {
        return new ArrayList<>(ataques); // Defensive copy
    }

    @Override
    public Ataque seleccionarAtaque() {
        if (estaDesmayado()) {
            return null;
        }

        int indice = random.nextInt(ataques.size());
        return ataques.get(indice);
    }

    @Override
    public void pasarTurno() {
        super.pasarTurno();
    }
}
