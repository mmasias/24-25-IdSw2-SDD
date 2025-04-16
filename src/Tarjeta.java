package src;

public class Tarjeta {
    private String numeroTarjeta;
    private String fechaExpiracion;
    private String cvv;
    private double saldo;
    private String tipoTarjeta;

    public Tarjeta(String numeroTarjeta, String fechaExpiracion, String cvv, double saldo) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.saldo = saldo;
        this.tipoTarjeta = "Bancaria";
    }

    public Tarjeta(String numeroTarjeta, String fechaExpiracion, double saldo) {
        this.numeroTarjeta = null;
        this.fechaExpiracion = null;
        this.cvv = null;
        this.saldo = 0.0;
        this.tipoTarjeta = "Monedero";
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String getCvv() {
        if (cvv == null) {
            return "null";
        }
        return cvv;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }
}
