package implementacion.modelo;

import interfaces.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class GestorCajas implements IGestorCajas {

    private List<ICaja> cajas;
    private int porcentajeRapidas = 25; // Porcentaje de cajas rápidas (puedes modificarlo si quieres)

    public GestorCajas() {
        this.cajas = new ArrayList<>();
    }

    @Override
    public void inicializarCajas(int numCajas) {
        cajas.clear();
        int numRapidas = (int) Math.ceil(numCajas * (porcentajeRapidas / 100.0));
        int id = 1;
        for (int i = 0; i < numRapidas; i++) {
            cajas.add(new CajaRapida(id++));
        }
        for (int i = numRapidas; i < numCajas; i++) {
            cajas.add(new Caja(id++));
        }
    }

    @Override
    public boolean hayCajasDisponibles() {
        return cajas.stream().anyMatch(ICaja::estaDisponible);
    }

    @Override
    public ICaja obtenerCajaDisponible(ICliente cliente) {
        for (ICaja caja : cajas) {
            if (caja.estaDisponible()) {
                if (caja instanceof CajaRapida) {
                    if (((CajaRapida) caja).puedeAtender(cliente)) {
                        return caja;
                    }
                } else {
                    return caja;
                }
            }
        }
        return null;
    }

    @Override
    public List<ICaja> obtenerTodasLasCajas() {
        return cajas;
    }

    @Override
    public void actualizarCajas(long tiempoActual) {
        // Ya no necesario aquí: las cajas se actualizan en ControladorSimulacion.
    }
}
