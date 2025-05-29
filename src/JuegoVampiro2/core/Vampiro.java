package JuegoVampiro2.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vampiro extends Personaje {
    private List<Mordida> ataques;
    private static final int LIMITE_DESMAYO = 20;

    public Vampiro(int energia) {
        super(energia, LIMITE_DESMAYO);
        this.ataques = new ArrayList<>();
        
        ataques.add(new Mordida("Mordida leve", 5, 90));
        ataques.add(new Mordida("Mordida moderada", 10, 60));
        ataques.add(new Mordida("Mordida fuerte", 20, 40));
    }

    @Override
    public String getNombre() {
        return "Vampiro";
    }

    public List<Mordida> getAtaques() {
        return ataques;
    }

    @Override
    public Ataque seleccionarAtaque() {
        if (estaDesmayado()) {
            return null;
        }

        Random random = new Random();
        int indice = random.nextInt(ataques.size());
        Mordida ataqueElegido = ataques.get(indice);
        
        return ataqueElegido;
    }

    @Override
    public Ataque seleccionarAtaque(int indice) {
       return seleccionarAtaque();
    }

     @Override
    public void pasarTurno() {
        super.pasarTurno();
} 
}
