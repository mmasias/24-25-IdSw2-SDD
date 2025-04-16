package src;

public class Producto {
    private String nombre;
    private String fechaExpiracion;

    public Producto(String nombre, String fechaExpiracion) {
        this.nombre = nombre;
        this.fechaExpiracion = null;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }
    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
