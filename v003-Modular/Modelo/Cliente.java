package Modelo;
public class Cliente {
    private final int items;
    
    public Cliente() {
        this.items = (int)(Math.random() * 11) + 5; 
    }
    
    public int getItems() {
        return items;
    }
}