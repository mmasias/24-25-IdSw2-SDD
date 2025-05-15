package pyAscensores.v1;

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

        ascensores.add(new Ascensor(6, plantas));
        ascensores.add(new Ascensor(6, plantas));
    }

    public boolean estaAbierta(int hora) {
        return hora >= horaApertura && hora < horaCierre;
    }

    public void acogerPersona(Persona persona) {
        // Asegurarse de que la persona esté en la lista de esperando de la planta 0
        plantas.get(3).agregarPersonaEsperando(persona); // Planta 0 está en el índice 3

        // Llamamos al ascensor para recoger a la persona
        this.llamarAscensor(persona);
    }

    
        
        private void llamarAscensor(Persona persona) {
        // Buscar el ascensor más cercano
        Ascensor mejor = null;
        int distanciaMinima = Integer.MAX_VALUE;
    
        for (Ascensor ascensor : ascensores) {
            int distancia = Math.abs(ascensor.getPlantaActual() - persona.getPlantaActual());
            if (distancia < distanciaMinima && ascensor.puedeRecogerPersona()) {
                distanciaMinima = distancia;
                mejor = ascensor;
            }
        }
    
        if (mejor != null) {
            mejor.recogerPersona(persona); // Asignar la persona al ascensor
        }
    }
public void actualizarEstado() {
    // Procesar personas esperando en cada planta
    for (Planta planta : plantas) {
        List<Persona> esperando = planta.getEsperando();
        for (Persona persona : new ArrayList<>(esperando)) {
            llamarAscensor(persona); // Llamar al ascensor para recoger a la persona
        }
    }

    // Actualizar el estado de los ascensores
    actualizarAscensores();
}

    public void moverPersona(Persona persona, int plantaDestino) {
        Planta plantaOrigen = plantas.get(persona.getPlantaActual() + 3);
        plantaOrigen.removerPersonaEnPlanta(persona); // Eliminar de la planta de origen
        persona.setPlantaActual(plantaDestino);
        Planta plantaNueva = plantas.get(plantaDestino + 3);
        plantaNueva.agregarPersonaEnPlanta(persona); // Agregar a la planta de destino
    }

        private void actualizarAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.actualizarEstado(); // Determina si el ascensor debe subir, bajar o detenerse
            ascensor.mover();            // Mueve el ascensor a la siguiente planta
        }
    }

        public void imprimirEstado(int hora, int minuto) {
        // Imprimir la hora actual
        System.out.printf("Hora: %02d:%02d\n", hora, minuto);
    
        // Encabezado del dibujo
        System.out.println("           Personas                                           Personas");
        System.out.println("           esperando                                          en la planta");
    
        // Iterar sobre las plantas desde la más alta (3) hasta la más baja (-3)
        for (int i = 3; i >= -3; i--) {
            Planta planta = plantas.get(i + 3); // Obtener la planta correspondiente
    
            // Obtener información de la planta y los ascensores
            String esperando = planta.getPersonasEsperando(); // Personas esperando en la planta
            String ascensor1 = ascensores.get(0).representacion(i); // Estado del ascensor 1
            String ascensor2 = ascensores.get(1).representacion(i); // Estado del ascensor 2
            String enPlanta = planta.getPersonasEnPlanta(); // Personas en la planta
    
            // Imprimir el estado de la planta
            System.out.printf("Planta %2d    %-5s esperando | |    %-5s    | |    %-5s    | |    %-5s\n", 
                i, esperando, ascensor1, ascensor2, enPlanta);
        }
    
        // Separador visual para los ascensores
        System.out.println("                       /--------- Ascensores ------/");
    }

    public void irPorEscalera(Persona persona, int plantaDestino) {
        
        if (plantaDestino < -3 || plantaDestino > 3) {
            System.out.println("Planta destino inválida.");
            return;
        }

    
        Planta plantaActual = plantas.get(persona.getPlantaActual() + 3);
        Planta nuevaPlanta = plantas.get(plantaDestino + 3);

        plantaActual.removerPersonaEnPlanta(persona);
        persona.setPlantaActual(plantaDestino);
        nuevaPlanta.agregarPersonaEnPlanta(persona);

        System.out.println("La persona ha subido/bajado por las escaleras a la planta " + plantaDestino);
    }

    public List<Persona> getPersonas() {
        return personas;
    }
}