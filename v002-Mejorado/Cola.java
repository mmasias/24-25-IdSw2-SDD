package proyecto.proyectoMejoradoAvance1;

import java.util.LinkedList;
import java.util.Queue;

class Cola {
    private final Queue<Cliente> clientes = new LinkedList<>();
    
    public void agregar(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public Cliente siguiente() {
        return clientes.poll();
    }
    
    public int cantidad() {
        return clientes.size();
    }
    
    public boolean estaVacia() {
        return clientes.isEmpty();
    }
}
