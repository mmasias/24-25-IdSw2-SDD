package pyAscensoresV1.src;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private Tiempo tiempo;
    private List<Planta> plantas;
    private List<Ascensor> ascensores;
    private List<Persona> personas;
    private ControlAscensor control;

    public Universidad(Tiempo tiempo) {
        this.plantas = new ArrayList<>();
        this.ascensores = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.tiempo = tiempo;
        this.control = new ControlAscensor(ascensores);
        inicializarEdificio();
    }

    private void inicializarEdificio() {
        for (int i = -3; i <= 3; i++) {
            plantas.add(new Planta(i));
        }

        ascensores.add(new Ascensor("A"));
        ascensores.add(new Ascensor("B"));
    }

    public boolean estaAbierta() {
        int hora = tiempo.getHora();
        return hora >= 8 && hora < 21 && !tiempo.esFinDeSemana() && !tiempo.esFestivo();
    }

    public void acogerPersona(Persona persona) {
        if (estaAbierta()) {
            personas.add(persona);
            persona.llamarAlAscensor(control);
        }
    }

    public void evolucionDeLaUniversidad() {
        for (Ascensor ascensor : ascensores) {
            ascensor.mover();
        }
    }

    public void imprimirEstado() {
        System.out.println(tiempo.darLaHora());
        System.out.println("Personas en la universidad: " + personas.size());
        for (Ascensor ascensor : ascensores) {
            ascensor.imprimirEstado();
        }

        for (Planta planta : plantas) {
            planta.imprimirEstado();
        }
    }

    public void simular() {
        if (estaAbierta()) {
            evolucionDeLaUniversidad();
            imprimirEstado();
        } else {
            System.out.println("La universidad está cerrada.");
        }
    }
}
