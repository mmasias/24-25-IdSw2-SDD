package pyAscensoresV1;

import java.util.ArrayList;
import java.util.List;

import pyAscensores.Persona;

public class Planta {
    private int numero;
    private List<Persona> personasPresentes;
    private List<Persona> personasEsperando;

    public Planta(int numero) {
        this.numero = numero;
        this.personasPresentes = new ArrayList<>();
        this.personasEsperando = new ArrayList<>();
    }

    public List<Persona> personasEnPlanta() {
        return personasPresentes;
    }

    public List<Persona> personasEsperando() {
        return personasEsperando;
    }

    public void registrarTiempoEspera() {
        System.out.println("Registrando tiempo de espera para las personas en la planta " + numero + "...");

        for (Persona persona : personasEsperando) {

            int tiempoEsperaActual = persona.getTiempoEspera();
            persona.setTiempoEspera(tiempoEsperaActual + 1);

            System.out.println("Tiempo de espera actualizado para la persona con destino a la planta "
                    + persona.getPlantaDestino() + ": " + persona.getTiempoEspera() + " unidades.");
        }

        if (personasEsperando.isEmpty()) {
            System.out.println("No hay personas esperando en la planta " + numero + ".");
        }
    }

    public boolean tieneAccesoRestringido() {

        System.out.println("Verificando si la planta " + numero + " tiene acceso restringido...");
        return false;
    }

    public boolean estaDisponible() {

        System.out.println("Verificando si la planta " + numero + " está disponible...");
        return true;
    }

    public void actualizar() {
        System.out.println("Actualizando el estado de la planta " + numero + "...");

        List<Persona> personasMovidas = new ArrayList<>();
        for (Persona persona : personasEsperando) {
            if (persona.getAscensor() != null && persona.getAscensor().estaEnPlantaActual()) {
                personasMovidas.add(persona);
                System.out.println(
                        "Persona con destino a la planta " + persona.getPlantaDestino() + " ha subido al ascensor.");
            }
        }

        personasEsperando.removeAll(personasMovidas);
    }

    public void imprimirEstado() {
        System.out.println("Planta " + numero + " con " + personasPresentes.size() + " personas presentes y "
                + personasEsperando.size() + " personas esperando.");
    }

    public int getNumero() {
        return numero;
    }

    public void agregarPersonaPresente(Persona persona) {
        personasPresentes.add(persona);
        System.out.println("Persona añadida a la planta " + numero + ".");
    }

    public void agregarPersonaEsperando(Persona persona) {
        personasEsperando.add(persona);
        System.out.println("Persona añadida a la lista de espera en la planta " + numero + ".");
    }

    public void removerPersonaPresente(Persona persona) {
        personasPresentes.remove(persona);
        System.out.println("Persona removida de la planta " + numero + ".");
    }

    public void removerPersonaEsperando(Persona persona) {
        personasEsperando.remove(persona);
        System.out.println("Persona removida de la lista de espera en la planta " + numero + ".");
    }
}