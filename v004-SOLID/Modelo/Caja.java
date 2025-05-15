
public class Caja {
    private Cliente clienteActual;
    private int tiempoRestante;
    
    public boolean estaLibre() {
        return clienteActual == null;
    }
    
    public void asignarCliente(Cliente cliente) {
        this.clienteActual = cliente;
        this.tiempoRestante = cliente.getItems();
    }
    
    public void procesarMinuto() {
        if(clienteActual != null) {
            tiempoRestante--;
            if(tiempoRestante <= 0) {
                clienteActual = null;
            }
        }
    }
    
    public String getEstado() {
        return clienteActual != null ? "[" + tiempoRestante + "]" : "[0]";
    }
}
