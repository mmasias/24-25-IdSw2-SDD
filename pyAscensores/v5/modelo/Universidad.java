package modelo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Universidad implements IEdificio {
    private final ITiempo tiempo;
    private final List<ITransporte> ascensores = new ArrayList<>();
    private final List<IPlanta> plantas = new ArrayList<>();
    private final Map<Integer, Integer> personasEnPlanta = new HashMap<>();
    private final Random random = new Random();
    
    private static final int PLANTA_MIN = -3;
    private static final int PLANTA_MAX = 3;
    private static final double PROB_MOVIMIENTO_INTERNO = 0.1;
    private static final double PROB_IR_A_PLANTA_CERO = 0.7;

    public Universidad(ITiempo tiempo) {
        this.tiempo = tiempo;
        
       
        for (int i = PLANTA_MIN; i <= PLANTA_MAX; i++) {
            plantas.add(new Planta(i));
            personasEnPlanta.put(i, 0); 
        }
        
        
        ascensores.add(new Ascensor(0));
        ascensores.add(new Ascensor(1));
        ascensores.add(new Ascensor(2));
        ascensores.add(new Ascensor(4)); 
    }
    
    @Override
    public void solicitarTransporte(int origen, int destino) {
   
        if (origen < PLANTA_MIN || origen > PLANTA_MAX || 
            destino < PLANTA_MIN || destino > PLANTA_MAX) {
            return;
        }
        
      
        IPersona persona = new Persona(origen, destino);
        plantas.get(origen - PLANTA_MIN).agregarPersonaEsperando(persona);
    }
    
    @Override
    public void moverPersonaEntrePlantas(int origen, int destino) {
       
        if (personasEnPlanta.getOrDefault(origen, 0) > 0) {
            
            personasEnPlanta.put(origen, personasEnPlanta.get(origen) - 1);
            
          
            solicitarTransporte(origen, destino);
        }
    }
    
    @Override
    public void sacarPersonasDePlanta(int planta, double porcentaje) {
        int cantidadPersonas = personasEnPlanta.getOrDefault(planta, 0);
        int personasASacar = (int) Math.ceil(cantidadPersonas * porcentaje);
        
        if (personasASacar > 0) {
            personasEnPlanta.put(planta, cantidadPersonas - personasASacar);
        }
    }

    @Override
    public void evolucionar() {
       
        for (ITransporte transporte : ascensores) {
            
            if (transporte instanceof Ascensor) {
                
                int plantaActual = transporte.getUbicacionActual();
                int personasAntes = transporte.getCantidadPersonas();
                
               
                ((Ascensor)transporte).evolucionar(plantas);
                
               
                int personasDespues = transporte.getCantidadPersonas();
                int personasBajadas = personasAntes - personasDespues;
                
                if (personasBajadas > 0) {
                    personasEnPlanta.put(plantaActual, 
                                         personasEnPlanta.getOrDefault(plantaActual, 0) + personasBajadas);
                }
            }
        }
        
       
        gestionarMovimientoPersonasEnPlantas();
    }
    
    private void gestionarMovimientoPersonasEnPlantas() {
      
        boolean esFinalDelDia = tiempo.getHora() >= 18;
        
        
        for (int nivel = PLANTA_MIN; nivel <= PLANTA_MAX; nivel++) {
            int personasEnEstePlanta = personasEnPlanta.getOrDefault(nivel, 0);
            
           
            if (personasEnEstePlanta <= 0) continue;
            
            if (esFinalDelDia) {
               
                if (nivel != 0) { 
                    int personasQueSeMueven = calcularPersonasAMover(personasEnEstePlanta, 0.6);
                    
                    for (int i = 0; i < personasQueSeMueven; i++) {
                        
                        personasEnPlanta.put(nivel, personasEnPlanta.get(nivel) - 1);
                        solicitarTransporte(nivel, 0);
                    }
                } 
                else {
                  
                    sacarPersonasDePlanta(0, 0.5); 
                }
            }
            else {
                
                if (random.nextDouble() < PROB_MOVIMIENTO_INTERNO) {
                    int personasQueSeMueven = calcularPersonasAMover(personasEnEstePlanta, 0.3);
                    
                    for (int i = 0; i < personasQueSeMueven; i++) {
                        int destino;
                        
                        
                        if (random.nextDouble() < PROB_IR_A_PLANTA_CERO && nivel != 0) {
                            destino = 0;
                        } else {
                           
                            do {
                                destino = random.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
                            } while (destino == nivel);
                        }
                        
                       
                        personasEnPlanta.put(nivel, personasEnPlanta.get(nivel) - 1);
                        
                       
                        solicitarTransporte(nivel, destino);
                    }
                }
            }
        }
    }
    
    private int calcularPersonasAMover(int totalPersonas, double factorMaximo) {
        
        int maximo = Math.max(1, (int)(totalPersonas * factorMaximo));
        return Math.min(random.nextInt(maximo) + 1, totalPersonas);
    }

    @Override
    public int obtenerCantidadEsperando(int planta) {
        return obtenerPlanta(planta).getCantidadEsperando();
    }

    @Override
    public int obtenerCantidadEnPlanta(int planta) {
        return personasEnPlanta.getOrDefault(planta, 0);
    }

    @Override
    public IPlanta obtenerPlanta(int nivel) {
        int indice = nivel - PLANTA_MIN;
        if (indice >= 0 && indice < plantas.size()) {
            return plantas.get(indice);
        }
        throw new IllegalArgumentException("Planta fuera de rango: " + nivel);
    }

    @Override
    public ITiempo getTiempo() {
        return tiempo;
    }

    @Override
    public List<ITransporte> getTransportes() {
        return ascensores;
    }

    @Override
    public boolean estaAbierto() {
        return tiempo.esHorarioComercial();
    }
    
    @Override
    public boolean estaVacio() {
       
        for (int planta = PLANTA_MIN; planta <= PLANTA_MAX; planta++) {
            if (obtenerCantidadEnPlanta(planta) > 0 || obtenerCantidadEsperando(planta) > 0) {
                return false;
            }
        }
        

        for (ITransporte transporte : ascensores) {
            if (transporte.getCantidadPersonas() > 0) {
                return false;
            }
        }
        
        return true;
    }

    public Ascensor[] getAscensores() {
        Ascensor[] ascensoresArray = new Ascensor[ascensores.size()];
        for (int i = 0; i < ascensores.size(); i++) {
            ascensoresArray[i] = (Ascensor) ascensores.get(i);
        }
        return ascensoresArray;
    }
}
