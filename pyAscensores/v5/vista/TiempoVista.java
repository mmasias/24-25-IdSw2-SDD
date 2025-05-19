package vista;

import modelo.ITiempo;

public class TiempoVista implements IVista {
    private final ITiempo tiempo;

    
    public TiempoVista(ITiempo tiempo) {
        this.tiempo = tiempo;
    }

   
    @Override
    public void mostrar() {
        System.out.println("Hora actual: " + tiempo.darLaHora());
        
        String estadoHorario = tiempo.esHorarioComercial() ? 
            "Dentro de horario comercial" : "Fuera de horario comercial";
        
        System.out.println(estadoHorario);
    }
    
   
    public void mostrarDetalle() {
        mostrar();
        
        System.out.println("Detalles del tiempo:");
        System.out.println("- Día: " + tiempo.getDia());
        System.out.println("- Hora: " + tiempo.getHora());
        System.out.println("- Minuto: " + tiempo.getMinuto());
        System.out.println("- Es fin de semana: " + (tiempo.esFinDeSemana() ? "Sí" : "No"));
        System.out.println("- Es festivo: " + (tiempo.esFestivo() ? "Sí" : "No"));
    }
}