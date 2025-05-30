package implementacion.modelo;

import interfaces.modelo.ICliente;

public class CajaRapida extends Caja {

    private static final int MAX_PRODUCTOS_PERMITIDOS = 10;

    public CajaRapida(int id) {
        super(id);
    }

    @Override
    public void atenderCliente(ICliente cliente) {
        if (cliente.getCantidadProductos() > MAX_PRODUCTOS_PERMITIDOS) {
            throw new IllegalArgumentException("Caja rápida solo permite hasta " + MAX_PRODUCTOS_PERMITIDOS + " productos");
        }
        super.atenderCliente(cliente);
    }

    public boolean puedeAtender(ICliente cliente) {
        return cliente.getCantidadProductos() <= MAX_PRODUCTOS_PERMITIDOS;
    }
}
