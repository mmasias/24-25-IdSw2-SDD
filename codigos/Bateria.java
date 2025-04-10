public class Bateria {
    private int carga;
    private int MAX_CARGA;
    private VistaConsola vista;

    public Bateria(int maxCarga, VistaConsola vista) {
        this.MAX_CARGA = maxCarga;
        this.vista = vista;
        this.carga = maxCarga;
    }

    public Bateria(VistaConsola vista) {
        this(50, vista);
    }

    public void consumir() {
        if (carga > 0) {
            carga--;
        }
    }

    public void recargar() {
        if (carga < MAX_CARGA) {
            carga = MAX_CARGA;
            vista.mostrarBateriaRecargada(carga);
        } else {
            vista.mostrarBateriaYaLlena();
        }
    }

    public int getCarga() {
        return carga;
    }

    public int getMAX_CARGA() {
        return MAX_CARGA;
    }
}
