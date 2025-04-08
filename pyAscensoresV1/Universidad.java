package pyAscensoresV1;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private Tiempo tiempo;
    private List<Planta> plantas;
    private List<Ascensor> ascensores;
    private List<Persona> personas;

    public Universidad() {
        this.plantas = new ArrayList<>();
        this.ascensores = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.inicializarEdificio();
    }

    public Universidad(Tiempo tiempo) {
        this();
        this.tiempo = tiempo;
    }

    private void inicializarEdificio() {
        // Inicializa plantas (-3 a 3)
        for (int i = -3; i <= 3; i++) {
            plantas.add(new Planta(i));
        }

        // Inicializa ascensores (puedes ajustar cantidad)
        ascensores.add(new Ascensor("A"));
        ascensores.add(new Ascensor("B"));
    }

    public boolean estaAbierta() {
        int hora = tiempo.getHora();
        return hora >= 8 && hora < 21 && !tiempo.esFinDeSemana() && !tiempo.esFestivo();
    }

    public void acogerPersona(Persona persona) {

    }

    public void actualizarEstado() {
        // Simular comportamiento de ascensores, personas moviéndose, etc.


    }

    public void imprimirEstado() {
        System.out.println("Hora actual: " + tiempo.darLaHora());
        System.out.println("Personas en la universidad: " + personas.size());
        for (Ascensor ascensor : ascensores) {
            ascensor.imprimirEstado();
        }

        for (Planta planta : plantas) {
            planta.imprimirEstado();
        }
    }

    public void gestionarEmergencias() {

    }

    public void programarMantenimiento() {
        System.out.println("Planificando mantenimiento...");
        for (Ascensor ascensor : ascensores) {
            ascensor.revisarMantenimiento();
        }
    }

    public void controlarAccesos() {
        System.out.println("Control de accesos activado.");
    }

    public void simular() { // invoca desde Mundo en cada iteración
        if (estaAbierta()) {
            actualizarEstado();
            imprimirEstado();
        } else {
            System.out.println("La universidad está cerrada.");
        }
    }
}
