package src;

public enum MONEDA {
    CINCO(0.05),
    DIEZ(0.10),
    VEINTE(0.20),
    CINCUENTA(0.50),
    UNO(1.00),
    DOS(2.00);

    private final double valor;

    MONEDA(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

}
