package src.moduloCaja;

public class Caja {
    private double total;

    public Caja(double totalInicial) {
        this.total = totalInicial;
    }

    public void recibirPago(double monto) {
        this.total += monto;
    }

    public void agregarFondos(double monto) {
        this.total += monto;
    }

    public double retirarTodo() {
        double monto = this.total;
        this.total = 0;
        return monto;
    }

    public double getTotal() {
        return total;
    }
}
