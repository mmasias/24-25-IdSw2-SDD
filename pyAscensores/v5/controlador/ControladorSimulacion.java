package controlador;

import modelo.ITiempo;
import modelo.TiempoSimulado;
import modelo.IEdificio;
import modelo.Universidad;

import java.util.Random;

public class ControladorSimulacion implements IControladorSimulacion {
    private final IEdificio universidad;
    private final Random random;
    

    private static final int PROBABILIDAD_LLEGADA_HORA_PUNTA = 70;
    private static final int PROBABILIDAD_LLEGADA_NORMAL = 30;
    private static final int PROBABILIDAD_MOVIMIENTO_INTERNO = 20;
    private static final int PROBABILIDAD_SALIDA_FIN_DIA = 90;
    private static final int PLANTA_MIN = -3;
    private static final int PLANTA_MAX = 3;
    
    private boolean esFinalDelDia = false;

    public ControladorSimulacion() {

        ITiempo tiempo = new TiempoSimulado(1, 8, 0); 
        this.universidad = new Universidad(tiempo);
        this.random = new Random();
    }
    
    private boolean esHoraPunta() {
        int hora = universidad.getTiempo().getHora();
        int minuto = universidad.getTiempo().getMinuto();
        
       
        if (hora == 8 && minuto <= 30) return true;
        
        
        if (hora >= 13 && hora < 14) return true;
        
    
        if (hora >= 17 && hora < 18) return true;
        
        return false;
    }
    
    private boolean esFinalDia() {
        int hora = universidad.getTiempo().getHora();
        
        return hora >= 18;
    }
    
    @Override
    public void avanzarSimulacion() {
        universidad.getTiempo().avanzarMinuto();

        if (universidad.estaAbierto()) {
           
            esFinalDelDia = esFinalDia();
            
            if (!esFinalDelDia) {
               
                procesarEntradasDePersonas();
                
                
                procesarMovimientoInterno();
            } else {
               
                procesarEvacuacionDelEdificio();
            }
            
            universidad.evolucionar();
        }
    }
    
    private void procesarEntradasDePersonas() {
        int probabilidadLlegada = esHoraPunta() ? 
            PROBABILIDAD_LLEGADA_HORA_PUNTA : PROBABILIDAD_LLEGADA_NORMAL;
            
        if (random.nextInt(100) < probabilidadLlegada) {
           
            int origen = 0;
            int destino;
           
            do {
                destino = random.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
            } while (destino == origen);
            
            universidad.solicitarTransporte(origen, destino);
        }
    }
    
    private void procesarMovimientoInterno() {
        if (random.nextInt(100) < PROBABILIDAD_MOVIMIENTO_INTERNO) {
          
            int origen;
            do {
                origen = random.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
            } while (origen == 0);
            
           
            int destino;
            if (random.nextInt(100) < 70) {
                destino = 0;
            } else {
               
                do {
                    destino = random.nextInt(PLANTA_MAX - PLANTA_MIN + 1) + PLANTA_MIN;
                } while (destino == origen);
            }
            
           
            if (universidad.obtenerCantidadEnPlanta(origen) > 0) {
                universidad.moverPersonaEntrePlantas(origen, destino);
            }
        }
    }
    
    private void procesarEvacuacionDelEdificio() {
       
        for (int planta = PLANTA_MIN; planta <= PLANTA_MAX; planta++) {
            if (planta != 0 && universidad.obtenerCantidadEnPlanta(planta) > 0) {

                if (random.nextInt(100) < PROBABILIDAD_SALIDA_FIN_DIA) {
                    universidad.moverPersonaEntrePlantas(planta, 0);
                }
            }
        }
        
        
        universidad.sacarPersonasDePlanta(0, 0.5);
    }

    @Override
    public IEdificio getEdificio() {
        return universidad;
    }

    @Override
    public boolean debeFinalizarSimulacion() {
    
        return !universidad.estaAbierto() && universidad.estaVacio();
    }

    @Override
    public Universidad getUniversidad() {
        return (Universidad) universidad;
    }
}
