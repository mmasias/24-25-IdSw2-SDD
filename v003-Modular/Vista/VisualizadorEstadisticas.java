package Vista;
import Modelo.Estadisticas;

public class VisualizadorEstadisticas {
    public void mostrarResumen(Estadisticas estadisticas) {
        System.out.println("\nRESUMEN");
        System.out.println("============================================================");
        System.out.println("Minutos con cola en cero   \t: " + estadisticas.getMinutosColaVacia());
        System.out.println("Personas en la cola al cierre \t: " + estadisticas.getClientesPendientes());
        System.out.println("Personas atendidas en el dia \t: " + estadisticas.getClientesAtendidos());
        System.out.println("Artículos vendidos en el dia \t: " + estadisticas.getproductosVendidos());
        System.out.println("============================================================");
    }
}