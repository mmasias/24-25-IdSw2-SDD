package pyAscensoresV1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ascensor {
    private static final int CAPACIDAD = 6;

    private enum Direccion { SUBIENDO, BAJANDO, PARADO }
    private Direccion direccion = Direccion.PARADO;

    private String id;
    private int plantaActual;
    private List<Persona> personas;
    private List<Llamada> llamadasPendientes;

    public Ascensor(String id) {
        this.id = id;
        this.plantaActual = 0;
        this.personas = new ArrayList<>();
        this.llamadasPendientes = new ArrayList<>();
    }

    public void recibirLlamada(Persona persona, int origen, int destino) {
        llamadasPendientes.add(new Llamada(persona, origen, destino));
    }

    public void mover() {
        if (direccion == Direccion.SUBIENDO) {
            plantaActual++;
            if (plantaActual >= 3) direccion = Direccion.BAJANDO;
        } else if (direccion == Direccion.BAJANDO) {
            plantaActual--;
            if (plantaActual <= -3) direccion = Direccion.SUBIENDO;
        } else {
            direccion = Direccion.SUBIENDO;
        }
    }

    public void atenderLlamadas() {
        // Bajan personas
        Iterator<Persona> it = personas.iterator();
        while (it.hasNext()) {
            Persona p = it.next();
            if (p.deseaBajarEn(plantaActual)) {
                p.bajarEn(plantaActual);
                it.remove();
            }
        }

        // Suben personas que hicieron llamada en esta planta
        List<Llamada> atendidas = new ArrayList<>();
        for (Llamada llamada : llamadasPendientes) {
            if (llamada.origen == plantaActual && personas.size() < CAPACIDAD) {
                llamada.persona.subir();
                personas.add(llamada.persona);
                atendidas.add(llamada);
            }
        }
        llamadasPendientes.removeAll(atendidas);
    }

    public void simular() {
        atenderLlamadas();
        mover();
    }

    public void imprimirEstado() {
        System.out.println("Ascensor " + id + " en planta " + plantaActual + " [" + direccion + "] con " + personas.size() + " personas.");
    }

    private static class Llamada {
        Persona persona;
        int origen;
        int destino;

        public Llamada(Persona p, int o, int d) {
            persona = p;
            origen = o;
            destino = d;
        }
    }
}
