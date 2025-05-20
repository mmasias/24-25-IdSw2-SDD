package proyecto.proyectoMejoradoAvance1;

class Estadisticas {
    private int minutosColaVacia;
    private int clientesAtendidos;
    private int productosVendidos;
    private int clientesPendientes;
    
    public void registrarMinuto(Cola cola) {
        if(cola.estaVacia()) minutosColaVacia++;
    }
    
    public void clienteAtendido(int items) {
        clientesAtendidos++;
        productosVendidos += items;
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
        System.out.println("Artículos vendidos en el dia \t: " + productosVendidos);
        System.out.println("============================================================");
    }
}