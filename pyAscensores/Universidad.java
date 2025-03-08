package pyAscensores;

import java.util.ArrayList;
import java.util.List;

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
        // Asegurarse de que la persona esté en la lista de esperando de la planta 0
        plantas.get(3).getEsperando().add(persona);  // Planta 0 está en el índice 3
    
        // Llamamos al ascensor para recoger a la persona
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
        plantaOrigen.getEnPlanta().add(persona); // Asegurarse de agregarla a la planta de destino
        persona.setPlantaActual(plantaDestino);
        Planta plantaNueva = plantas.get(plantaDestino + 3);
        plantaNueva.getEnPlanta().remove(persona); // Eliminar de la planta de origen
    }

    private void actualizarAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.mover();
        }
    }

    public void imprimirEstado(int hora, int minuto) {
        // Contar el número total de personas en la universidad
        int totalPersonas = 0;
        for (Planta planta : plantas) {
            totalPersonas += planta.getEsperando().size();  // Personas esperando
            totalPersonas += planta.getEnPlanta().size();  // Personas en la planta
        }
    
        // Imprimir la hora
        System.out.printf("Hora: %2d:%02d\n", hora, minuto);
        System.out.println("Total personas en la universidad: " + totalPersonas); // Mostramos el total
    
        System.out.println("           Personas                                           Personas");
        System.out.println("           esperando                                          en la planta");
        
        // Imprimir las plantas y el estado de los ascensores
        for (int i = 3; i >= -3; i--) {
            Planta planta = plantas.get(i + 3);
            
            // Obtener los valores de personas esperando y personas en planta
            String esperando = planta.getPersonasEsperando();
            String ascensor1 = ascensores.get(0).representacion(i);
            String ascensor2 = ascensores.get(1).representacion(i);
            String enPlanta = planta.getPersonasEnPlanta();
            
            // Imprimir el estado de la planta
            System.out.printf("Planta %2d    %-5s esperando | |    %-5s    | |    %-5s    | |    %-5s\n", 
                i, esperando, ascensor1, ascensor2, enPlanta);
        }
        System.out.println("                       /--------- Ascensores ------/");
    }
    
    
    public List<Persona> getPersonas() {
        return personas;
    }
}
