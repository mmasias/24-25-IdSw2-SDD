package vista;

import controlador.IControladorSimulacion;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimuladorAscensores implements IVista {
    private final Random random = new Random();
    private final IControladorSimulacion controlador;
    private final UniversidadVista vista;

    public SimuladorAscensores(IControladorSimulacion controlador) {
        this.controlador = controlador;
        this.vista = new UniversidadVista(controlador.getUniversidad());
    }

    public SimuladorAscensores() {
        this(new controlador.ControladorSimulacion());
    }

    private void generarPersonaAleatoria() {
        int origen = 0; // Siempre planta B
        int destino;
        do {
            destino = random.nextInt(7) - 3; // -3 a 3
        } while (destino == origen);
        controlador.getEdificio().solicitarTransporte(origen, destino);
    }

    @Override
    public void mostrar() {
        System.out.println("Simulación de ascensores de la Universidad (modo automático)");
        vista.mostrar();
    }

    public void simular() {
        mostrar();

        boolean seguirSimulacion = true;

        while (seguirSimulacion) {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ejecutarCicloSimulacion();

            if (controlador.debeFinalizarSimulacion()) {
                System.out.println("La universidad está cerrada y todas las personas han salido.");
                seguirSimulacion = false;
            }
        }

        System.out.println("Simulación finalizada.");
    }

    private void ejecutarCicloSimulacion() {
        generarPersonaAleatoria();
        controlador.avanzarSimulacion();
        vista.mostrar();
    }
}