public class Bateria {
    private int carga;
    private int MAX_CARGA;

    public Bateria(int maxCarga) {
        this.MAX_CARGA = maxCarga;
        this.carga = maxCarga;
    }

    public Bateria() {
        this(50);
    }

    public void consumir() {
        if (carga > 0) {
            carga--;
        }
    }

    public void recargar() {
        if (carga < MAX_CARGA) {
            carga = MAX_CARGA;
            System.out.println("Batería recargada. Carga actual: " + carga);
        } else {
            System.out.println("La batería ya está al máximo.");
        }
    }

    public int getCarga() {
        return carga;
    }

    public int getMAX_CARGA(){
        return MAX_CARGA;
    }
}
