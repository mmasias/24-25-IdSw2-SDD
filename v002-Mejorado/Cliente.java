package proyecto.proyectoMejoradoAvance1;

class Cliente {
    private int productos;
    
    public Cliente() {
        this.productos = (int)(Math.random() * 11) + 5; 
    }
    
    public int getProductos() {
        return productos;
    }
}
