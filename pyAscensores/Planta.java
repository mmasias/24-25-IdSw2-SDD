package pyAscensores;

public class Planta {
    private int numero;
    private int cantidadPersonas;
    
    public Planta(int numero) {
        this.numero = numero;
        this.cantidadPersonas = 0;
    }

    public int getNumero() {
        return numero;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void agregarPersona() {
        cantidadPersonas++;
    }

    public void quitarPersona() {
        cantidadPersonas--;
    }
    
}
