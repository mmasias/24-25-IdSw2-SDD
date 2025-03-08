package pyAscensores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universidad {
    private List<Planta> plantas;
    private List<Ascensor> ascensores;
    private List<Persona> personas;
    private int horaApertura = 8; // La universidad abre a las 8:00 AM
    private int horaCierre = 19; // La universidad cierra a las 7:00 PM

    public Universidad() {
        plantas = new ArrayList<>();
        ascensores = new ArrayList<>();
        personas = new ArrayList<>();

        for (int i = -3; i <= 3; i++) {
            plantas.add(new Planta(i));
        }

        ascensores.add(new Ascensor(6));
        ascensores.add(new Ascensor(6));
    }

    public boolean estaAbierta(int hora) {
        return hora >= horaApertura && hora < horaCierre;
    }

    public void acogerPersona(Persona persona) {
        this.llamarAscensor(persona);
    }

    private void llamarAscensor(Persona persona) {
        // Buscar el ascensor más cercano
        Ascensor mejor = null;
        int distanciaMinima = Integer.MAX_VALUE;
        
        for (Ascensor ascensor : ascensores) {
            int distancia = Math.abs(ascensor.getPlantaActual() - persona.getPlantaActual());
            if (distancia < distanciaMinima && ascensor.puedeRecogerPersona(persona)) {
                distanciaMinima = distancia;
                mejor = ascensor;
            }
        }
        
        if (mejor != null) {
            mejor.recogerPersona(persona);
        }
    }

    public void actualizarEstado() {
        for (Persona persona : new ArrayList<>(personas)) {
            persona.reducirTiempoEnPlanta();
            if (persona.getTiempoEnPlanta() <= 0 && persona.getPlantaActual() != 0) {
                moverPersona(persona, 0); // Si la persona termina su tiempo, va a la planta 0
            }
        }
        actualizarAscensores();
    }

    public void moverPersona(Persona persona, int plantaDestino) {
        Planta plantaOrigen = plantas.get(persona.getPlantaActual() + 3);
        plantaOrigen.getEsperando().remove(persona);
        plantaOrigen.getEnPlanta().add(persona);
        persona.setPlantaActual(plantaDestino);
        Planta plantaNueva = plantas.get(plantaDestino + 3);
        plantaNueva.getEnPlanta().remove(persona);
    }

    private void actualizarAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.mover();
        }
    }

    public void imprimirEstado() {
        System.out.println("Personas esperando y en planta:");
        for (int i = 3; i >= -3; i--) {
            Planta planta = plantas.get(i + 3);
            String esperando = planta.getPersonasEsperando();
            String ascensor1 = ascensores.get(0).representacion(i);
            String ascensor2 = ascensores.get(1).representacion(i);
            String enPlanta = planta.getPersonasEnPlanta();

            System.out.printf("Planta %2d    %-5s      | |    %-5s    | |    %-5s    | |    %-5s\n", i, esperando, ascensor1, ascensor2, enPlanta);
        }
        System.out.println("                       /--------- Ascensores ------/");
    }

    public List<Persona> getPersonas() {
        return personas;
    }
}
