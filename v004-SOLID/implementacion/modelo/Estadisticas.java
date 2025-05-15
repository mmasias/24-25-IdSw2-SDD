package implementacion.modelo;

import interfaces.modelo.ICaja;
import interfaces.modelo.ICliente;
import interfaces.modelo.IEstadisticas;
import interfaces.modelo.ICola;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas implements IEstadisticas {
    private List<ICliente> clientesRegistrados;
    private List<ICliente> clientesAtendidos;
    private ICola cola;

    public Estadisticas(ICola cola) {
        this.clientesRegistrados = new ArrayList<>();
        this.clientesAtendidos = new ArrayList<>();
        this.cola = cola;
    }

    @Override
    public void registrarLlegadaCliente(ICliente cliente) {
        clientesRegistrados.add(cliente);
    }

    @Override
    public void registrarClienteAtendido(ICliente cliente, ICaja caja) {
        clientesAtendidos.add(cliente);
    }

    @Override
    public double getTiempoPromedioEspera() {
        if (clientesAtendidos.isEmpty()) {
            return 0;
        }

        long tiempoTotal = 0;
        for (ICliente cliente : clientesAtendidos) {
            tiempoTotal += cliente.getTiempoEspera();
        }

        return (double) tiempoTotal / clientesAtendidos.size();
    }

    @Override
    public double getTiempoPromedioAtencion() {
        if (clientesAtendidos.isEmpty()) {
            return 0;
        }

        long tiempoTotal = 0;
        for (ICliente cliente : clientesAtendidos) {
            tiempoTotal += cliente.getTiempoAtencion();
        }

        return (double) tiempoTotal / clientesAtendidos.size();
    }

    @Override
    public int getClientesAtendidos() {
        return clientesAtendidos.size();
    }

    @Override
    public int getClientesEnCola() {
        return cola.getTamanio();
    }
}