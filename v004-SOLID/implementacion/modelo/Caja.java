package implementacion.modelo;

import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;

public class Caja implements ICaja {
    private final int id;
    private boolean disponible;
    private ICliente clienteActual;
    private int clientesAtendidos;
    private long tiempoTotalAtencion;

    public Caja(int id) {
        this.id = id;
        this.disponible = true;
        this.clientesAtendidos = 0;
        this.tiempoTotalAtencion = 0;
    }

    @Override
    public boolean estaDisponible() {
        return disponible;
    }

    @Override
    public void atenderCliente(ICliente cliente) {
        if (!disponible) {
            throw new IllegalStateException("La caja no está disponible");
        }
        this.clienteActual = cliente;
        this.disponible = false;
    }

    @Override
    public void actualizar(long tiempoActual) {
        if (!disponible && clienteActual != null) {
            int productosRestantes = clienteActual.getCantidadProductos();
            if (productosRestantes > 0) {
                clienteActual.setCantidadProductos(productosRestantes - 1);
            }
            if (clienteActual.getCantidadProductos() == 0) {
                liberarCaja();
            }
        }
    }

    @Override
    public void liberarCaja() {
        if (clienteActual != null) {
            clientesAtendidos++;
            tiempoTotalAtencion += clienteActual.getTiempoAtencion();
            clienteActual = null;
        }
        disponible = true;
    }

    @Override
    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    @Override
    public double getTiempoPromedioAtencion() {
        return clientesAtendidos == 0 ? 0 : (double) tiempoTotalAtencion / clientesAtendidos;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ICliente getClienteActual() {
        return clienteActual;
    }

    @Override
    public boolean esRapida() {
        return false;
    }
}
