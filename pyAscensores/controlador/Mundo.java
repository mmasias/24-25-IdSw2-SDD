package controlador;

import modelo.*;
import vista.ConsolaVista;
import java.util.Random;

public class Mundo {
    public static final int DIA = 1;
    public static final int HORA_INICIO = 19;
    public static final int MINUTO_INICIO = 50;
    public static final long PAUSA_SIMULACION_MS = 200;
    public static final double PROBABILIDAD_INGRESO = 0.8;

    private Tiempo tiempo;
    private Universidad universidad;
    private ConsolaVista vista;
    private Random random = new Random();

    public Mundo() {
        this.tiempo = new Tiempo(DIA, HORA_INICIO, MINUTO_INICIO);
        this.universidad = new Universidad(tiempo);
        this.vista = new ConsolaVista();
    }

    public void simularDia() {
        boolean diaTerminado = false;
        while (!diaTerminado) {
            tiempo.avanzarMinuto();

            if (universidad.estaAbierta()) {
                generarLlegadaSiProcede();
            } else {
                vista.mostrarCierre();
            }
            universidad.actualizarEstancias();
            universidad.actualizarEstado();
            vista.mostrarEstado(universidad.getPlantas(), universidad.getAscensores(), tiempo, universidad);

            if (tiempo.getHora() == Tiempo.HORA_CIERRE) {
                vista.mostrarTotalesFinales(universidad);
            }
            vista.finalDia();
            pausar();
        }
    }

    private void generarLlegadaSiProcede() {
        if (random.nextDouble() < PROBABILIDAD_INGRESO) {
            Persona persona = new Persona();
            Planta plantaIngreso = universidad.getPlantas().get(Universidad.INGRESO);
            plantaIngreso.personaEsperaAscensor(persona);
            universidad.getControlAscensor().procesarLlamada(
                new Llamada(Universidad.INGRESO, persona.getPlantaDestino(), persona));
            universidad.incrementarTotalPersonasIngresadas();
        }
    }

    private void pausar() {
        try {
            Thread.sleep(PAUSA_SIMULACION_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
