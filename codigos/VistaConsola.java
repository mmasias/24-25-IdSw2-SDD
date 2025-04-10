public class VistaConsola {

    public void mostrarBolsaLlena() {
        System.out.println(
                "La bolsa está llena. Dirigiéndose a zona de recarga para vaciarse y recargarse si es necesario.");
    }

    public void mostrarBateriaBaja() {
        System.out.println("Batería baja. Buscando zona de recarga...");
    }

    public void mostrarZonaSuciaInaccesible() {
        System.out.println("No se encontró ruta hacia la zona sucia.");
    }

    public void mostrarZonaRecargaNoEncontrada() {
        System.out.println("No se encontró una zona de recarga en la superficie.");
    }

    public void mostrarZonaRecargaInaccesible() {
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
        System.out.println("Aspiradora en (" + aspiradora.getPosX() + ", " + aspiradora.getPosY() + ") - Bolsa: "
                + aspiradora.obtenerBolsa() + "/100 - Batería: " + aspiradora.obtenerCargaBateria() + "/"
                + bateria.getMAX_CARGA());
    }

    public void mostrarPaso(int paso) {
        System.out.println("\nPaso #" + paso);
    }

    public void mostrarSuperficie(Superficie superficie, int posX, int posY) {
        for (int i = 0; i < superficie.getAlto(); i++) {
            for (int j = 0; j < superficie.getAncho(); j++) {
                Zona zona = superficie.obtenerZona(j, i);
                if (i == posY && j == posX) {
                    System.out.print("( O ) ");
                } else if (zona != null && zona.tieneMueble()) {
                    System.out.print("[ # ] ");
                } else {
                    int nivel = zona != null ? zona.getNivelSuciedad() : 0;
                    switch (nivel) {
                        case 0:
                            System.out.print("[ . ] ");
                            break;
                        case 1:
                            System.out.print("[...] ");
                            break;
                        case 2:
                            System.out.print("[ooo] ");
                            break;
                        case 3:
                            System.out.print("[OOO] ");
                            break;
                        case 4:
                            System.out.print("[***] ");
                            break;
                    }
                }
            }
            System.out.println();
        }
    }
}
