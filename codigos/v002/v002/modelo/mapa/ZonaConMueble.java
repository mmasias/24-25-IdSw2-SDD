package v002.modelo.mapa;

public class ZonaConMueble extends Zona {
    
    @Override
    public boolean tieneMueble() {
        return true;
    }
    
    @Override
    public void ensuciar() {
        // No se puede ensuciar una zona con mueble
    }
    
    @Override
    public void limpiar() {
        // No se puede limpiar una zona con mueble
    }
}