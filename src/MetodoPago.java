package src;

public class MetodoPago {
    private int id;
    private String tipo; 

    
    public MetodoPago(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
