package Modelo;
public class Cliente {
    private final int productos;
    
    public Cliente() {
        this.productos = (int)(Math.random() * 11) + 5; 
    }
    
    public int getProductos() {
        return productos;
    }
}