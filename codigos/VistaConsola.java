public class VistaConsola {

    public void mostrarBolsaLlena() {
        System.out.println("La bolsa está llena. Dirigiéndose a zona de recarga para vaciarse y recargarse si es necesario.");
    }

    public void mostrarBateriaBaja() {
        System.out.println("Batería baja. Buscando zona de recarga...");
    }

    public void mostrarZonaSuciaInaccesible() {
        System.out.println("No se encontró ruta hacia la zona sucia.");
    }

    public void mostrarZonaDeRecargaNoEncontrada() {
        System.out.println("No se encontró una zona de recarga en el Habitacion.");
    }

    public void mostrarZonaDeRecargaInaccesible() {
        System.out.println("No se puede llegar a la zona de recarga.");
    }

    public void mostrarVaciadoBolsa() {
        System.out.println("Vaciar la bolsa de basura...");
    }

    public void mostrarBolsaLlenaDuranteLimpieza() {
        System.out.println("La bolsa está llena. Dirigiéndose a la zona de recarga para vaciarse.");
    }

    public void mostrarBateriaAgotada() {
        System.out.println("La aspiradora se quedó sin batería. Necesita recargarse.");
    }

    public void mostrarZonaLimpia() {
        System.out.println("La zona ya está limpia, no se ha realizado ninguna acción.");
    }

    public void mostrarBateriaRecargada(int cargaActual) {
        System.out.println("Batería recargada. Carga actual: " + cargaActual);
    }

    public void mostrarBateriaYaLlena() {
        System.out.println("La batería ya está al máximo.");
    }

    public void mostrarRecargandoBateria() {
        System.out.println("Recargando la aspiradora...");
    }

    public void mostrarEstadoAspiradora(Aspiradora aspiradora, Bateria bateria) {
        System.out.println("Aspiradora en (" + aspiradora.getPosicionX() + ", " + aspiradora.getPosicionY() + ") - Bolsa: "
                + aspiradora.getBolsa() + "/" + aspiradora.getCapacidadMaximaBolsa() + " - Batería: "
                + aspiradora.getCargaBateria() + "/" + bateria.getcapacidadMaximaBateria());
    }

    public void mostrarPaso(int paso) {
        System.out.println("\nPaso #" + paso);
    }

    public void mostrarHabitacion(Habitacion habitacion, int posicionX, int posicionY, Gato gato) {
        System.out.print("+");
        for (int j = 0; j < habitacion.getAnchoHabitacion(); j++) {
            System.out.print("-----");
        }
        System.out.println("+");

        for (int i = 0; i < habitacion.getAltoHabitacion(); i++) {
            System.out.print("|");
            for (int j = 0; j < habitacion.getAnchoHabitacion(); j++) {
                Zona zona = habitacion.getZona(j, i);

                if (i == posicionY && j == posicionX) {
                    System.out.print(" (O) ");
                } else if (i == gato.getPosicionY() && j == gato.getPosicionX()) {
                    System.out.print(" >^< ");
                } else if (zona != null && zona.tieneMueble()) {
                    System.out.print("  #  ");
                } else {
                    int nivel = zona != null ? zona.getNivelSuciedad() : 0;
                    switch (nivel) {
                        case 0:
                            System.out.print("  .  ");
                            break;
                        case 1:
                            System.out.print(" ... ");
                            break;
                        case 2:
                            System.out.print(" ooo ");
                            break;
                        case 3:
                            System.out.print(" OOO ");
                            break;
                        case 4:
                            System.out.print(" *** ");
                            break;
                    }
                }
            }
            System.out.println("|");
        }

        System.out.print("+");
        for (int j = 0; j < habitacion.getAnchoHabitacion(); j++) {
            System.out.print("-----");
        }
        System.out.println("+");
    }
}
