public class ZonaRecarga extends Zona {

    private VistaConsola vista;

    public ZonaRecarga(VistaConsola vista) {
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
