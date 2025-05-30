package modelo;

import java.util.*;

public class Ascensor {
    public static final int CAPACIDAD_MAXIMA = 6;

    private String id;
    private int plantaActual = Universidad.INGRESO;
    private List<Persona> personas = new ArrayList<>();
    private Queue<Llamada> llamadas = new LinkedList<>();
    private Map<Integer, Planta> plantas;
    private int totalTransportadas = 0;

    public Ascensor(String id) {
        this.id = id;
    }

    public void asignarPlantas(Map<Integer, Planta> plantas) {
        this.plantas = plantas;
    }

    public void atenderLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    public void mover() {
        bajar();
        recoger();
        if (!personas.isEmpty()) {
            moverHacia(personas.get(0).getPlantaDestino());
        } else if (!llamadas.isEmpty()) {
            Llamada siguiente = llamadas.peek();
            moverHacia(siguiente.getPlantaOrigen());
        } else {
            for (Planta p : plantas.values()) {
                if (!p.getEsperando().isEmpty()) {
                    moverHacia(p.getNumero());
                    break;
                }
            }
        }
    }

    private void moverHacia(int destino) {
        plantaActual += Integer.compare(destino, plantaActual);
    }

    private void bajar() {
        personas.removeIf(p -> {
            if (p.getPlantaDestino() == plantaActual) {
                plantas.get(plantaActual).registrarEntrada(p);
                return true;
            }
            return false;
        });
    }

    private void recoger() {
        Planta planta = plantas.get(plantaActual);
        Iterator<Persona> it = planta.getEsperando().iterator();
        while (it.hasNext() && personas.size() < CAPACIDAD_MAXIMA) {
            Persona p = it.next();
            personas.add(p);
            totalTransportadas++;
            it.remove();
            llamadas.removeIf(l -> l.getPersona() == p);
        }
    }

    public void vaciar() {
        personas.clear();
        llamadas.clear();
    }

    public int getPlantaActual() {
        return plantaActual;
    }

    public String getId() {
        return id;
    }

    public int getCantidadPersonas() {
        return personas.size();
    }

    public int getTotalTransportadas() {
        return totalTransportadas;
    }

    public boolean tieneLlamadaPara(Persona p) {
        return llamadas.stream().anyMatch(l -> l.getPersona() == p);
    }
}
