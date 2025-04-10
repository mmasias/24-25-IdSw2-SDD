package src;

public class Producto {
    private String nombre;
    private double precio;
    private String fecha_expiracion;

    public Producto(String nombre, double precio, String fecha_expiracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.fecha_expiracion = null;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getFecha_expiracion() {
        return fecha_expiracion;
    }
    public void setFecha_expiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }
}
