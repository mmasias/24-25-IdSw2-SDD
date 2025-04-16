package src;

public enum BILLETE {
    CINCO(5),
    DIEZ(10);

    private final int valor;
    BILLETE(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
