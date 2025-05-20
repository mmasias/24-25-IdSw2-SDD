package implementacion.modelo;

import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;

public class Caja implements ICaja {
    private int id;
    private boolean disponible;
    private ICliente clienteActual;
    private int clientesAtendidos;
    private long tiempoTotalAtencion;
    private long tiempoFinAtencion;

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

        long tiempoAtencion = (long) (Math.random() * 4 + 1);
        cliente.setTiempoInicioAtencion(System.currentTimeMillis());
        cliente.setTiempoAtencion(tiempoAtencion);

        this.tiempoFinAtencion = System.currentTimeMillis() + tiempoAtencion;
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
        if (clientesAtendidos == 0) {
            return 0;
        }
        return (double) tiempoTotalAtencion / clientesAtendidos;
    }

    @Override
    public int getId() {
        return id;
    }

    public long getTiempoFinAtencion() {
        return tiempoFinAtencion;
    }
}