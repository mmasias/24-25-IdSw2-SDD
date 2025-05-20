package implementacion.modelo;

import interfaces.modelo.ICliente;
import interfaces.modelo.ICola;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cola implements ICola {
    private Queue<ICliente> clientesEnCola;

    public Cola() {
        this.clientesEnCola = new LinkedList<>();
    }

    @Override
    public void agregarCliente(ICliente cliente) {
        clientesEnCola.add(cliente);
    }

    @Override
    public ICliente siguienteCliente() {
        return clientesEnCola.poll();
    }

    @Override
    public boolean estaVacia() {
        return clientesEnCola.isEmpty();
    }

    @Override
    public int getTamanio() {
        return clientesEnCola.size();
    }

    @Override
    public List<ICliente> getClientes() {
        return new ArrayList<>(clientesEnCola);
    }
}