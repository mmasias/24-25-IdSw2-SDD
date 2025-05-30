package implementacion.modelo;

import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;

public class Caja implements ICaja {
    private int id;
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

    public void actualizarEstado(long tiempoActual) {
        if (!disponible && clienteActual != null) {
            int productos = clienteActual.getCantidadProductos();
            if (productos > 0) {
                clienteActual.setCantidadProductos(productos - 1);  
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
        if (clientesAtendidos == 0) return 0;
        return (double) tiempoTotalAtencion / clientesAtendidos;
    }

    @Override
    public int getId() {
        return id;
    }

    public ICliente getClienteActual() {
        return clienteActual;
    }
}
