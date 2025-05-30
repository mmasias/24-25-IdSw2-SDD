package modelo;

import java.util.*;

public class Planta {
    private int numero;
    private Queue<Persona> esperando = new LinkedList<>();
    private List<Persona> enPlanta = new ArrayList<>();

    public Planta(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Queue<Persona> getEsperando() {
        return esperando;
    }

    public List<Persona> getEnPlanta() {
        return enPlanta;
    }

    public void personaEsperaAscensor(Persona p) {
        esperando.add(p);
    }

    public int getCantidadEsperando() {
        return esperando.size();
    }

    public int getCantidadEnPlanta() {
        return enPlanta.size();
    }

    public void registrarEntrada(Persona p) {
        if (numero == 0 && p.debeSalir()) {
            p.marcarSalida();
        } else {
            enPlanta.add(p);
        }
    }

    public List<Persona> actualizarEstanciasYGenerarSalidas() {
        List<Persona> paraSalir = new ArrayList<>();

        if (numero == 0) {
            Iterator<Persona> it = enPlanta.iterator();
            while (it.hasNext()) {
                Persona persona = it.next();
                persona.decrementarTiempo();
                if (persona.haSalido()) {
                    it.remove();
                }
            }

            esperando.removeIf(p -> {
                p.decrementarTiempo();
                return p.debeSalir();
            });

        } else {
            Iterator<Persona> it = enPlanta.iterator();
            while (it.hasNext()) {
                Persona persona = it.next();
                persona.decrementarTiempo();
                if (persona.debeSalir()) {
                    persona.marcarSalida();
                    paraSalir.add(persona);
                    it.remove();
                    esperando.add(persona);
                }
            }
        }

        return paraSalir;
    }

    public void evacuarPersonas() {
        for (Persona p : new ArrayList<>(enPlanta)) {
            p.marcarSalida();
            enPlanta.remove(p);
            esperando.add(p);
        }
    }

    public List<Persona> personasEsperando() {
        return new ArrayList<>(esperando);
    }
}
