package src;

import java.util.List;

public class Empleado {
    private int id; 
    private String nombre;

    public Empleado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void abastecerMaquina(List<Celda> celdas, Producto producto) {
        for (Celda celda : celdas) {
            int espacioDisponible = celda.getCapacidad() - celda.getCantidad_disponible();
            if (espacioDisponible > 0) {
                for (int i = 0; i < espacioDisponible; i++) {
                    celda.getProductos().add(producto); 
                }
                celda.setCantidad_disponible(celda.getCapacidad()); 
            }
        }
    }

    public void asignarPrecio(List<Celda> celdas, double precio) {
        for (Celda celda : celdas) {
            celda.setPrecio(precio);
        }
    }
}