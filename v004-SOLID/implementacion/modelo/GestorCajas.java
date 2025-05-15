package implementacion.modelo;

import interfaces.modelo.ICaja;
import interfaces.modelo.IGestorCajas;

import java.util.ArrayList;
import java.util.List;

public class GestorCajas implements IGestorCajas {
    private List<ICaja> cajas;

    public GestorCajas() {
        this.cajas = new ArrayList<>();
    }

    @Override
    public void inicializarCajas(int numeroCajas) {
        cajas.clear();
        for (int i = 0; i < numeroCajas; i++) {
            cajas.add(new Caja(i + 1));
        }
    }

    @Override
    public ICaja obtenerCajaDisponible() {
        for (ICaja caja : cajas) {
            if (caja.estaDisponible()) {
                return caja;
            }
        }
        return null;
    }

    @Override
    public List<ICaja> obtenerTodasLasCajas() {
        return new ArrayList<>(cajas);
    }

    @Override
    public boolean hayCajasDisponibles() {
        return cajas.stream().anyMatch(ICaja::estaDisponible);
    }

    @Override
    public void actualizarCajas(long tiempoActual) {
        for (ICaja caja : cajas) {
            if (!caja.estaDisponible()) {
                // Validamos si ya terminó el tiempo de atención
                // Asumimos que podemos hacer un cast seguro porque solo manejamos nuestras implementaciones
                Caja cajaConcreta = (Caja) caja;
                if (tiempoActual >= cajaConcreta.getTiempoFinAtencion()) {
                    caja.liberarCaja();
                }
            }
        }
    }
}