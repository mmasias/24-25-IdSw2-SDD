package controlador;

import modelo.Tiempo;
import modelo.TiempoSimulado;
import modelo.Universidad;

import java.util.Random;

public class ControladorSimulacion {
    private Universidad universidad;
    private Random random;
    private static final int PROBABILIDAD_LLEGADA_HORA_PUNTA = 70; // Alta probabilidad en hora punta
    private static final int PROBABILIDAD_LLEGADA_NORMAL = 30;     // Probabilidad normal
    private static final int PROBABILIDAD_SALIDA_FIN_DIA = 90;     // Probabilidad alta de salir al final del día
    private static final int PLANTAS = 7;
    
    // Control de patrones de entrada/salida
    private boolean esFinalDelDia = false;

    public ControladorSimulacion() {
        Tiempo tiempo = new TiempoSimulado(1, 8, 0); // Empezamos a las 8:00
        this.universidad = new Universidad(tiempo);
        this.random = new Random();
        
        // No inicializamos con personas, todas entrarán por planta 0
    }
    
    private boolean esHoraPunta() {
        int hora = universidad.getTiempo().getHora();
        int minuto = universidad.getTiempo().getMinuto();
        
        // Hora punta por la mañana (8-9)
        if (hora == 8 && minuto <= 30) return true;
        
        // Hora punta de mediodía (13-14)
        if (hora >= 13 && hora < 14) return true;
        
        // Hora punta de tarde (17-18)
        if (hora >= 17 && hora < 18) return true;
        
        return false;
    }
    
    private boolean esFinalDia() {
        int hora = universidad.getTiempo().getHora();
        // Consideramos final del día a partir de las 18:00
        return hora >= 18;
    }
    
    public void avanzarSimulacion() {
        universidad.getTiempo().avanzarMinuto();
        int hora = universidad.getTiempo().getHora();
        int minuto = universidad.getTiempo().getMinuto();

        if (universidad.estaAbierta()) {
            // Detectamos si estamos en final del día
            esFinalDelDia = esFinalDia();
            
            if (!esFinalDelDia) {
                // ENTRADA DE PERSONAS (solo por planta 0)
                int probabilidadLlegada = esHoraPunta() ? 
                    PROBABILIDAD_LLEGADA_HORA_PUNTA : PROBABILIDAD_LLEGADA_NORMAL;
                    
                if (random.nextInt(100) < probabilidadLlegada) {
                    // Todas las personas entran por planta 0
                    int origen = 0;
                    int destino;
                    
                    // Solo pueden ir a otras plantas (no se quedan en planta 0)
                    do {
                        destino = random.nextInt(PLANTAS) - 3;
                    } while (destino == origen);
                    
                    universidad.solicitarAscensor(origen, destino);
                }
                
                // MOVIMIENTO ENTRE PLANTAS (personas que ya están dentro)
                if (random.nextInt(100) < 20) { // 20% de probabilidad de movimiento interno
                    // Eligiendo una planta de origen aleatoria (excluyendo planta 0)
                    int origen;
                    do {
                        origen = random.nextInt(PLANTAS) - 3;
                    } while (origen == 0);
                    
                    // 70% de probabilidad de que quieran ir a planta 0 (para salir)
                    int destino;
                    if (random.nextInt(100) < 70) {
                        destino = 0;
                    } else {
                        // 30% probabilidad de ir a otra planta
                        do {
                            destino = random.nextInt(PLANTAS) - 3;
                        } while (destino == origen);
                    }
                    
                    // Solo creamos esta solicitud si hay personas en esa planta
                    if (universidad.obtenerCantidadEnPlanta(origen) > 0) {
                        universidad.moverPersonaEntrePlantas(origen, destino);
                    }
                }
            } else {
                // FINAL DEL DÍA: evacuación de la universidad
                // Mayor probabilidad de que las personas quieran salir
                for (int planta = -3; planta <= 3; planta++) {
                    if (planta != 0 && universidad.obtenerCantidadEnPlanta(planta) > 0) {
                        // Las personas en plantas diferentes a 0 quieren ir a planta 0 para salir
                        if (random.nextInt(100) < PROBABILIDAD_SALIDA_FIN_DIA) {
                            universidad.moverPersonaEntrePlantas(planta, 0);
                        }
                    }
                }
                
                // Las personas que llegan a planta 0 salen de la universidad
                universidad.sacarPersonasDePlanta(0, 0.5); // 50% de personas en planta 0 salen en cada ciclo
            }
            
            universidad.evolucionar();
        }
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public boolean estaUniversidadAbierta() {
        return universidad.estaAbierta();
    }
    
    public boolean universidadVacia() {
        // Verificar que no queden personas en la universidad
        for (int planta = -3; planta <= 3; planta++) {
            if (universidad.obtenerCantidadEnPlanta(planta) > 0 || 
                universidad.obtenerCantidadEsperando(planta) > 0) {
                return false;
            }
        }
        
        // Verificar que no haya personas en los ascensores
        for (int i = 0; i < universidad.getAscensores().size(); i++) {
            if (universidad.getAscensores().get(i).personasEnElAscensor() > 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean debeFinalizarSimulacion() {
        // La simulación termina cuando la universidad está cerrada Y vacía
        return !universidad.estaAbierta() && universidadVacia();
    }
}