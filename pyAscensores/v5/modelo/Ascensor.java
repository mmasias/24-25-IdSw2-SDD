package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ascensor implements ITransporte {
    private final int id;
    private int plantaActual = 0;
    private int direccion = 1; // 1: sube, -1: baja
    private final List<IPersona> personas = new ArrayList<>();
    private static final int CAPACIDAD = 4;
    private static final int PLANTA_MIN = -3;
    private static final int PLANTA_MAX = 3;
    private static final double PROB_CAMBIO_DIRECCION = 0.3;
    private static final double PROB_IR_A_PLANTA_CERO = 0.8;

    public Ascensor(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getUbicacionActual() {
        return plantaActual;
    }

    @Override
    public int getDireccion() {
        return direccion;
    }

    @Override
    public int getCantidadPersonas() {
        return personas.size();
    }
    
    @Override
    public List<IPersona> getPersonasTransportadas() {
        return new ArrayList<>(personas);
    }
    
    @Override
    public int getCapacidadDisponible() {
        return CAPACIDAD - personas.size();
    }
    
    @Override
    public boolean tieneEspacio() {
        return personas.size() < CAPACIDAD;
    }

    @Override
    public void mover() {
        
        if (debeIrAPlantaCero()) {
            priorizarPlantaCero();
        }
        
        plantaActual += direccion;
        
        
        if (plantaActual > PLANTA_MAX) {
            plantaActual = PLANTA_MAX;
            cambiarDireccion();
        } else if (plantaActual < PLANTA_MIN) {
            plantaActual = PLANTA_MIN;
            cambiarDireccion();
        }
    }
    
    private boolean debeIrAPlantaCero() {
       
        return personas.isEmpty() && Math.random() < PROB_IR_A_PLANTA_CERO;
    }
    
    private void priorizarPlantaCero() {
       
        if (plantaActual > 0) {
            direccion = -1;
        } 
       
        else if (plantaActual < 0) {
            direccion = 1;
        }
    }
    
    private void cambiarDireccion() {
        direccion *= -1;
    }

    @Override
    public int dejarSalir() {
        int cantidadInicial = personas.size();
        Iterator<IPersona> it = personas.iterator();
        while (it.hasNext()) {
            IPersona persona = it.next();
            if (persona.getDestino() == plantaActual) {
                it.remove();
            }
        }
        int personasQueSalieron = cantidadInicial - personas.size();
        
        
        if (personasQueSalieron > 0 && Math.random() < PROB_CAMBIO_DIRECCION) {
            cambiarDireccion();
        }
        
        return personasQueSalieron;
    }

    @Override
    public void subirPersona(IPersona persona) {
        if (tieneEspacio()) {
            personas.add(persona);
            
           
            if (personas.size() == 1) {
                adaptarDireccionADestino(persona);
            }
        }
    }
    
    private void adaptarDireccionADestino(IPersona persona) {
        if (persona.getDestino() > plantaActual) {
            direccion = 1; // Subir
        } else if (persona.getDestino() < plantaActual) {
            direccion = -1; // Bajar
        }
    }
    
    public void evolucionar(List<IPlanta> plantas) {
       
        dejarSalir();
        
      
        IPlanta plantaActual = plantas.get(this.plantaActual - PLANTA_MIN);
        
      
        while (tieneEspacio() && plantaActual.tienePersonasEsperando()) {
            IPersona persona = plantaActual.sacarPersonaEsperando();
            subirPersona(persona);
        }
        
      
        mover();
    }

    public String getPlantaActual() {
       
        return plantaActual == 0 ? "B" : String.valueOf(plantaActual);
    }
}