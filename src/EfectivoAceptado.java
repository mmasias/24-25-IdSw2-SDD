package src;

public enum EfectivoAceptado {
    BILLETE_VEINTE(20),
    BILLETE_DIEZ(10),
    BILLETE_CINCO(5),
    MONEDA_DOS(2.00),
    MONEDA_UNO(1.00),
    MONEDA_CINCUENTA(0.50),
    MONEDA_VEINTE(0.20),
    MONEDA_DIEZ(0.10),
    MONEDA_CINCO(0.05);

    private final double valor;
    EfectivoAceptado(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
