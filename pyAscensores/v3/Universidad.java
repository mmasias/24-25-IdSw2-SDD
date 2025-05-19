package pyAscensores.v3;

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
        ascensores.add(new Ascensor("A1"));
        ascensores.add(new Ascensor("A2"));
    }

    private void asignarPlantasAAscensores() {
        ascensores.forEach(ascensor -> ascensor.asignarPlantas(plantas));
    }

    public boolean estaAbierta() {
        return tiempo.getHora() >= HORA_APERTURA && tiempo.getHora() < HORA_CIERRE
                && !tiempo.esFinDeSemana() && !tiempo.esFestivo();
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
            public int getPlantaOrigen() { return origen; }
        };
        acogerPersona(persona);
    }

    private void asignarPersonaAPlanta(Persona persona) {
        plantas.stream()
               .filter(p -> p.getNumero() == persona.getPlantaOrigen())
               .findFirst()
               .ifPresent(p -> p.personaEsperaAscensor(persona));
    }

    public int obtenerCantidadEsperando(int plantaNumero) {
        return plantas.stream()
                .filter(p -> p.getNumero() == plantaNumero)
                .mapToInt(Planta::getCantidadEsperando)
                .findFirst().orElse(0);
    }

    public int obtenerCantidadEnPlanta(int plantaNumero) {
        return plantas.stream()
                .filter(p -> p.getNumero() == plantaNumero)
                .mapToInt(Planta::getCantidadEnPlanta)
                .findFirst().orElse(0);
    }

    public void evolucionDeLaUniversidad() {
        control.moverAscensores();
    }

    public void imprimirEstado() {
        System.out.println(tiempo.darLaHora());
        plantas.stream()
               .sorted((p1, p2) -> Integer.compare(p2.getNumero(), p1.getNumero()))
               .forEach(this::imprimirEstadoPlanta);
        System.out.println("       /--------- Ascensores ------/");
    }

    private void imprimirEstadoPlanta(Planta planta) {
        StringBuilder linea = new StringBuilder();
        linea.append("Planta ").append(String.format("%2d", planta.getNumero())).append("   ___")
             .append(planta.getCantidadEsperando()).append("_ ");
        ascensores.stream()
                  .filter(a -> a.getPlantaActualAsInt() == planta.getNumero())
                  .forEach(a -> linea.append("  [").append(a.getId()).append(":").append(a.personasEnElAscensor()).append("]"));
        linea.append("     __").append(planta.getCantidadEnPlanta()).append("__");
        System.out.println(linea);
    }

    public Tiempo getTiempo() { return tiempo; }
    public List<Planta> getPlantas() { return plantas; }
    public List<Ascensor> getAscensores() { return ascensores; }
    public List<Persona> getPersonas() { return personas; }

    public void simular() {
        if (estaAbierta()) {
            evolucionDeLaUniversidad();
            imprimirEstado();
        } else {
            System.out.println("La universidad está cerrada.");
        }
    }
}
