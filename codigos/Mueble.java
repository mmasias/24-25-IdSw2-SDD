public class Mueble {
    private int tamaño;

    public Mueble(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public String toString() {
        return "Mueble de tamaño: " + tamaño;
    }
}
