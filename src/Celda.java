public class Celda extends ComponenteHoja {
    private String contenidoCompleto;
    private String contenidoVisible;
    
    public Celda() {
        this.contenidoCompleto = "";
        this.contenidoVisible = VACIO;
    }
    
    public String getContenidoCompleto() {
        return contenidoCompleto;
    }
    
    public String getContenidoVisible() {
        return contenidoVisible;
    }
    
    public void setContenido(String contenido) {
        this.contenidoCompleto = contenido;
        this.contenidoVisible = formatearContenido(contenido);
    }
}