package implementacion.modelo;

import interfaces.modelo.ICliente;

public class CajaRapida extends Caja {

    private static final int MAX_ITEMS_PERMITIDOS = 10;

    public CajaRapida(int id) {
        super(id);
    }

    @Override
    public void atenderCliente(ICliente cliente) {
        if (cliente.getCantidadItems() > MAX_ITEMS_PERMITIDOS) {
            throw new IllegalArgumentException("Caja rápida solo permite hasta " + MAX_ITEMS_PERMITIDOS + " productos");
        }
        super.atenderCliente(cliente);
    }

    public boolean puedeAtender(ICliente cliente) {
        return cliente.getCantidadItems() <= MAX_ITEMS_PERMITIDOS;
    }
}
