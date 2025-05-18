package modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ascensor {
    private final int id;
    private int plantaActual = 0;
    private int direccion = 1; // 1: sube, -1: baja
    private final Queue<Persona> personas = new LinkedList<>();
    private static final int CAPACIDAD = 4;

    public Ascensor(int id) {
        this.id = id;
    }

    public int getPlantaActualAsInt() {
        return plantaActual;
    }

    public int getId() {
        return id;
    }

    public int getDireccion() {
        return direccion;
    }

    public int personasEnElAscensor() {
        return personas.size();
    }

    public void mover() {
        // Al final del día, los ascensores priorizan ir a planta 0
        if (esFinDeJornada()) {
            // Si estamos por encima de la planta 0, bajamos
            if (plantaActual > 0) {
                direccion = -1;
            } 
            // Si estamos por debajo de la planta 0, subimos
            else if (plantaActual < 0) {
                direccion = 1;
            }
            // Si estamos en planta 0, no nos movemos
            else {
                return;
            }
        }
        
        // Movimiento normal
        plantaActual += direccion;
        
        // Si llegamos a los límites, cambiamos de dirección
        if (plantaActual > 3) {
            plantaActual = 3;
            direccion = -1;
        } else if (plantaActual < -3) {
            plantaActual = -3;
            direccion = 1;
        }
    }
    
    private boolean esFinDeJornada() {
        // Esta lógica podría refinarse con información del tiempo
        // Para simplificar, asumimos que un ascensor vacío al final del día
        // debería dirigirse a planta 0
        return personas.isEmpty() && Math.random() < 0.8; // 80% probabilidad para priorizar ir a planta 0
    }

    public void dejarSalir() {
        int cantidadInicial = personas.size();
        personas.removeIf(p -> p.getDestino() == plantaActual);
        int personasQueSalieron = cantidadInicial - personas.size();
        
        // Si dejamos salir personas, tal vez queramos quedarnos un poco más en esta planta
        if (personasQueSalieron > 0 && Math.random() < 0.3) {
            // 30% de probabilidad de cambiar dirección tras dejar salir gente
            // (simula comportamiento más realista de ascensor)
            direccion *= -1;
        }
    }

    public void subirPersona(Persona persona) {
        if (personas.size() < CAPACIDAD) {
            personas.add(persona);
            
            // Si subimos personas y el ascensor está vacío, podríamos querer
            // movernos en la dirección de su destino
            if (personas.size() == 1) {
                if (persona.getDestino() > plantaActual) {
                    direccion = 1; // Subir
                } else if (persona.getDestino() < plantaActual) {
                    direccion = -1; // Bajar
                }
            }
        }
    }

    public void evolucionar(List<Planta> plantas) {
        // Primero dejamos salir a las personas que llegan a su destino
        dejarSalir();
        
        // Luego recogemos a personas en la planta actual si hay espacio
        Planta plantaActual = plantas.get(this.plantaActual + 3);
        
        // Mientras haya personas esperando y espacio en el ascensor
        while (!plantaActual.getEsperando().isEmpty() && personas.size() < CAPACIDAD) {
            Persona persona = plantaActual.getEsperando().poll();
            subirPersona(persona);
        }
        
        // Finalmente movemos el ascensor
        mover();
    }
}