package proyecto.proyectoMejorado;

public class Jornada {
    private static final int DURACION_JORNADA = 720;
    private static final double PROB_LLEGADA = 0.6;
    
    public static void main(String[] args) {
        Cola cola = new Cola();
        GestorCajas cajas = new GestorCajas(4);
        Estadisticas stats = new Estadisticas();
        
        for(int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            System.out.println("\n>> MINUTO " + minuto);
            
            // Llegada de clientes
            if(Math.random() <= PROB_LLEGADA) {
                cola.agregar(new Cliente());
                System.out.println(" - Llegó una persona");
            } else {
                System.out.println(" - No llegó nadie");
            }
            
            System.out.println("> Personas en cola: " + cola.cantidad());
            
            // Procesar cajas
            cajas.procesar(cola, stats);
            stats.registrarMinuto(cola);
            
            // Mostrar estado
            cajas.mostrarEstado();
        }
        
        // Finalizar
        stats.setClientesPendientes(cola.cantidad());
        stats.mostrarResumen();
    }
}
