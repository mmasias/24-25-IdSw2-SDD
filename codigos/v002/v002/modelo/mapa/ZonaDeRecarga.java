package v002.modelo.mapa;

import v002.modelo.entidades.Aspiradora;
import v002.vista.VistaConsola;

public class ZonaDeRecarga extends Zona {

    private VistaConsola vista;

    public ZonaDeRecarga(VistaConsola vista) {
        super();
        this.vista = vista;
    }

    @Override
    public void ensuciar() {
        // No se puede ensuciar una zona de recarga
    }

    @Override
    public boolean tieneMueble() {
        return false;
    }

    public void recargar(Aspiradora aspiradora) {
        vista.mostrarRecargandoBateria();
        aspiradora.recargarBateria();
    }
}