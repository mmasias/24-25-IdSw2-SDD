public class Posicion extends ComponenteHoja {
    private int fila;
    private int columna;
    
    public Posicion() {
        this.fila = 0;
        this.columna = 0;
    }
    
    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }
    
    public void moverArriba() {
        if (fila > 0) {
            fila--;
        }
    }
    
    public void moverAbajo() {
        if (fila < TOTAL_FILAS - 1) {
            fila++;
        }
    }
    
    public void moverIzquierda() {
        if (columna > 0) {
            columna--;
        }
    }
    
    public void moverDerecha() {
        if (columna < TOTAL_COLUMNAS - 1) {
            columna++;
        }
    }
}