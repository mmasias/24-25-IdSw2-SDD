package v002;
public class ZonaDeRecarga extends Zona {

    private VistaConsola vista;

    public ZonaDeRecarga(VistaConsola vista) {
        super();
        this.vista = vista;
    }

    @Override
    public void ensuciar() {
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
