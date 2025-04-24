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
        asignarPlantasAAscensores();
    }

    private void inicializarEdificio() {
        inicializarPlantas();
        inicializarAscensores();
    }

    private void inicializarPlantas() {
        for (int i = PLANTA_MINIMA; i <= PLANTA_MAXIMA; i++) {
            plantas.add(new Planta(i));
        }
    }

    private void inicializarAscensores() {
        ascensores.add(new Ascensor("A"));
        ascensores.add(new Ascensor("B"));
    }

    private void asignarPlantasAAscensores() {
        for (Ascensor ascensor : ascensores) {
            ascensor.asignarPlantas(plantas);
        }
    }

    public boolean estaAbierta() {
        int hora = tiempo.getHora();
        return hora >= HORA_APERTURA && hora < HORA_CIERRE && !tiempo.esFinDeSemana() && !tiempo.esFestivo();
    }

    public void acogerPersona(Persona persona) {
        if (estaAbierta()) {
            personas.add(persona);
            asignarPersonaAPlanta(persona);
            persona.llamarAlAscensor(control);
        }
    }
    public void acogerPersona(int origen, int destino) {
        Persona persona = new Persona(destino) {
            @Override
            public int getPlantaOrigen() {
                return origen;
            }
        };
        acogerPersona(persona);
    }


    private void asignarPersonaAPlanta(Persona persona) {
        for (Planta planta : plantas) {
            if (planta.getNumero() == persona.getPlantaOrigen()) {
                planta.personaEsperaAscensor(persona);
                break;
            }
        }
    }

    public void evolucionDeLaUniversidad() {
        control.moverAscensores();
    }

    public void imprimirEstado() {
        imprimirCabeceraEstado();
        imprimirEstadoPlantas();
        imprimirPieEstado();
    }

    private void imprimirCabeceraEstado() {
        System.out.println(tiempo.darLaHora());
        System.out.println("     Personas                     Personas");
        System.out.println("     esperando                    en la planta\n");
    }

    private void imprimirEstadoPlantas() {
        for (int i = PLANTA_MAXIMA; i >= PLANTA_MINIMA; i--) {
            imprimirEstadoPlanta(i);
        }
    }

    private void imprimirEstadoPlanta(int plantaNumero) {
        StringBuilder linea = new StringBuilder();

        int esperando = obtenerCantidadEsperando(plantaNumero);
        int enPlanta = obtenerCantidadEnPlanta(plantaNumero);

        String esperaTexto = String.format("   ___%d_ ", esperando);
        linea.append("Planta ").append(String.format("%2d", plantaNumero)).append(esperaTexto);

        agregarEstadoAscensores(linea, plantaNumero);

        linea.append("     __").append(enPlanta).append("__");

        System.out.println(linea.toString());
    }

    protected int obtenerCantidadEsperando(int plantaNumero) {
        for (Planta planta : plantas) {
            if (planta.getNumero() == plantaNumero) {
                return planta.getCantidadEsperando();
            }
        }
        return 0;
    }

    protected int obtenerCantidadEnPlanta(int plantaNumero) {
        for (Planta planta : plantas) {
            if (planta.getNumero() == plantaNumero) {
                return planta.getCantidadEnPlanta();
            }
        }
        return 0;
    }

    private void agregarEstadoAscensores(StringBuilder linea, int plantaNumero) {
        for (Ascensor ascensor : ascensores) {
            if (ascensor.getPlantaActualAsInt() == plantaNumero) {
                linea.append("  [v").append(ascensor.personasEnElAscensor()).append("v]");
            } else {
                linea.append("   | | ");
            }
        }
    }

    public Tiempo getTiempo() {
        return tiempo;
    }

    public List<Planta> getPlantas() {
        return plantas;
    }

    public List<Ascensor> getAscensores() {
        return ascensores;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    private void imprimirPieEstado() {
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