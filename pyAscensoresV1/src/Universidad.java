package pyAscensoresV1.src;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private static final int PLANTA_MINIMA = -3;
    private static final int PLANTA_MAXIMA = 3;
    private static final int HORA_APERTURA = 8;
    private static final int HORA_CIERRE = 21;

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
        inicializarEdificio();
        this.control = new ControlAscensor(ascensores);
        for (Ascensor ascensor : ascensores) {
            ascensor.asignarPlantas(plantas);
        }
    }

    private void inicializarEdificio() {
        for (int i = PLANTA_MINIMA; i <= PLANTA_MAXIMA; i++) {
            plantas.add(new Planta(i));
        }
        ascensores.add(new Ascensor("A"));
        ascensores.add(new Ascensor("B"));
    }

    public boolean estaAbierta() {
        int hora = tiempo.getHora();
        return hora >= HORA_APERTURA && hora < HORA_CIERRE && !tiempo.esFinDeSemana() && !tiempo.esFestivo();
    }

    public void acogerPersona(Persona persona) {
        if (estaAbierta()) {
            personas.add(persona);
            for (Planta planta : plantas) {
                if (planta.getNumero() == persona.getPlantaOrigen()) {
                    planta.personaEsperaAscensor(persona);
                    break;
                }
            }
            persona.llamarAlAscensor(control);
        }
    }

    public void evolucionDeLaUniversidad() {
        control.moverAscensores();
    }

    public void imprimirEstado() {
        System.out.println(tiempo.darLaHora());
        System.out.println("     Personas                     Personas");
        System.out.println("     esperando                    en la planta\n");

        for (int i = PLANTA_MAXIMA; i >= PLANTA_MINIMA; i--) {
            StringBuilder linea = new StringBuilder();

            int esperando = 0;
            int enPlanta = 0;

            for (Planta planta : plantas) {
                if (planta.getNumero() == i) {
                    esperando = planta.getCantidadEsperando();
                    enPlanta = planta.getCantidadEnPlanta();
                    break;
                }
            }

            String esperaTexto = String.format("   ___%d_ ", esperando);
            linea.append("Planta ").append(String.format("%2d", i)).append(esperaTexto);

            for (Ascensor ascensor : ascensores) {
                if (ascensor.getPlantaActualAsInt() == i) {
                    linea.append("  [v").append(ascensor.personasEnElAscensor()).append("v]");
                } else {
                    linea.append("   | | ");
                }
            }

            linea.append("     __").append(enPlanta).append("__");

            System.out.println(linea.toString());
        }

        System.out.println("       /--------- Ascensores ------/");
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