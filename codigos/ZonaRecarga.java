public class ZonaRecarga extends Zona {

    public ZonaRecarga() {
        super();
    }

    @Override
    public void ensuciar() {
    }

    @Override
    public boolean tieneMueble() {
        return false;
    }

    public void recargar(Aspiradora aspiradora) {
        System.out.println("Recargando la aspiradora...");
        aspiradora.recargarBateria();
    }
}
