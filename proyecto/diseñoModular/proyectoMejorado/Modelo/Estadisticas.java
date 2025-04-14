package proyecto.diseñoModular.proyectoMejorado.Modelo;

public class Estadisticas {
    private int minutosColaVacia;
    private int clientesAtendidos;
    private int itemsVendidos;
    private int clientesPendientes;
    
    public void registrarMinuto(Cola cola) {
        if (cola.estaVacia()) minutosColaVacia++;
    }
    
    public void clienteAtendido(int items) {
        clientesAtendidos++;
        itemsVendidos += items;
    }
    
    public void setClientesPendientes(int cantidad) {
        clientesPendientes = cantidad;
    }
    
    public void mostrarResumen() {
        System.out.println("\nRESUMEN");
        System.out.println("============================================================");
        System.out.println("Minutos con cola en cero   \t: " + minutosColaVacia);
        System.out.println("Personas en la cola al cierre \t: " + clientesPendientes);
        System.out.println("Personas atendidas en el dia \t: " + clientesAtendidos);
        System.out.println("Artículos vendidos en el dia \t: " + itemsVendidos);
        System.out.println("============================================================");
    }
}
