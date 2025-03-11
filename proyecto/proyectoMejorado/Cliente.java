package proyecto.proyectoMejorado;

class Cliente {
    private final int items;
    
    public Cliente() {
        this.items = (int)(Math.random() * 11) + 5; // 5-15 items
    }
    
    public int getItems() {
        return items;
    }
}
