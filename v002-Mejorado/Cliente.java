package proyecto.proyectoMejoradoAvance1;

class Cliente {
    private int items;
    
    public Cliente() {
        this.items = (int)(Math.random() * 11) + 5; 
    }
    
    public int getItems() {
        return items;
    }
}
