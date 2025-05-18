package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Universidad {
    private final Tiempo tiempo;
    private final List<Ascensor> ascensores = new ArrayList<>();
    private final List<Planta> plantas = new ArrayList<>();
    private final Map<Integer, Integer> personasEnPlanta = new HashMap<>();
    private final Random random = new Random();

    public Universidad(Tiempo tiempo) {
        this.tiempo = tiempo;
        for (int i = -3; i <= 3; i++) {
            plantas.add(new Planta(i));
            personasEnPlanta.put(i, 0); // Inicializar contador de personas en cada planta
        }
        // Crear 4 ascensores como en el ejemplo
        ascensores.add(new Ascensor(0)); // ID 0 para seguir el ejemplo
        ascensores.add(new Ascensor(1));
        ascensores.add(new Ascensor(2));
        ascensores.add(new Ascensor(4)); // ID 4 como en el ejemplo
    }

    public void acogerPersona(int origen, int destino) {
        Planta planta = obtenerPlanta(origen);
        planta.agregarPersona(new Persona(origen, destino));
    }
    
    public void solicitarAscensor(int origen, int destino) {
        // Crea una nueva persona y la añade a la cola de espera de la planta 'origen'
        Persona persona = new Persona(origen, destino);
        plantas.get(origen + 3).añadirPersonaEsperando(persona);
    }
    
    public void moverPersonaEntrePlantas(int origen, int destino) {
        // Verifica si hay personas en la planta de origen
        if (personasEnPlanta.getOrDefault(origen, 0) > 0) {
            // Reduce el contador de la planta origen
            personasEnPlanta.put(origen, personasEnPlanta.get(origen) - 1);
            
            // Agrega a la persona a la cola de espera de ascensor en esa planta
            solicitarAscensor(origen, destino);
        }
    }
    
    public void sacarPersonasDePlanta(int planta, double porcentaje) {
        int cantidadPersonas = personasEnPlanta.getOrDefault(planta, 0);
        int personasASacar = (int) Math.ceil(cantidadPersonas * porcentaje);
        
        if (personasASacar > 0) {
            personasEnPlanta.put(planta, cantidadPersonas - personasASacar);
        }
    }

    public void evolucionar() {
        // Por cada ascensor, realiza su lógica de movimiento y recogida/descenso de personas
        for (Ascensor ascensor : ascensores) {
            // Guarda información sobre personas antes de evolucionar
            int plantaActual = ascensor.getPlantaActualAsInt();
            int personasAntes = ascensor.personasEnElAscensor();
            
            // Evoluciona el ascensor (dejará salir personas, subirá nuevas y se moverá)
            ascensor.evolucionar(plantas);
            
            // Calcula cuántas personas bajaron en esta planta
            int personasDespues = ascensor.personasEnElAscensor();
            int personasBajadas = personasAntes - personasDespues;
            
            if (personasBajadas > 0) {
                personasEnPlanta.put(plantaActual, personasEnPlanta.get(plantaActual) + personasBajadas);
            }
        }
        
        // Comportamiento aleatorio de personas en plantas (moverse entre plantas)
        for (int nivel = -3; nivel <= 3; nivel++) {
            // Las personas tienen probabilidad de decidir ir a otra planta
            if (personasEnPlanta.getOrDefault(nivel, 0) > 0 && random.nextDouble() < 0.1) {
                // 10% de probabilidad de que alguien quiera ir a otra planta
                int personasQueSeMueven = Math.min(random.nextInt(3) + 1, personasEnPlanta.get(nivel));
                for (int i = 0; i < personasQueSeMueven; i++) {
                    int destino;
                    // Las personas tienden a querer salir por planta 0
                    if (random.nextDouble() < 0.7 && nivel != 0) {
                        destino = 0; // 70% probabilidad de ir a planta baja
                    } else {
                        // 30% probabilidad de ir a otra planta
                        do {
                            destino = random.nextInt(7) - 3;
                        } while (destino == nivel);
                    }
                    
                    // Reduce el contador de personas en esta planta
                    personasEnPlanta.put(nivel, personasEnPlanta.get(nivel) - 1);
                    
                    // Solicitan ascensor desde su planta actual
                    solicitarAscensor(nivel, destino);
                }
            }
        }
    }

    public void simular() {
        evolucionar();
    }

    public int obtenerCantidadEsperando(int planta) {
        return obtenerPlanta(planta).cantidadEsperando();
    }

    public int obtenerCantidadEnPlanta(int planta) {
        return personasEnPlanta.getOrDefault(planta, 0);
    }

    public Planta obtenerPlanta(int nivel) {
        return plantas.get(nivel + 3);
    }

    public Tiempo getTiempo() {
        return tiempo;
    }

    public List<Ascensor> getAscensores() {
        return ascensores;
    }

    public boolean estaAbierta() {
        return tiempo.getHora() >= 8 && tiempo.getHora() < 20;
    }

    public Planta[] getPlantas() {
        return plantas.toArray(new Planta[0]);
    }
}
