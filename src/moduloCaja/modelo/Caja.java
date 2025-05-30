package src.moduloCaja.modelo;

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

    public void retirarFondos(double monto) {
        if (this.total >= monto) {
            this.total -= monto;
        } else {
            this.total = 0;
        }
    }

    public double retirarTodo() {
        double monto = this.total;
        this.total = 0;
        return monto;
    }

    public double getTotal() {
        return total;
    }

    public boolean entregarCambio(double monto) {
        if (monto < 0) {
            System.out.println("Error: El monto de cambio no puede ser negativo.");
            return false;
        }
        if (this.total >= monto) {
            this.total -= monto;
            return true;
        } else {
            System.out.println("Error: Fondos insuficientes en la caja para entregar cambio.");
            return false;
        }
    }

    public void retirarPago(double monto) {
        if (this.total >= monto) {
            this.total -= monto;
        } else {
            this.total = 0;
        }
    }
}
