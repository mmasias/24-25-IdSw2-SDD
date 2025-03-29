public class Matriz extends ComponenteHoja {
    private Celda[][] celdas;
    
    public Matriz() {
        celdas = new Celda[TOTAL_FILAS][TOTAL_COLUMNAS];
        inicializarHoja();
    }
    
    private void inicializarHoja() {
        for (int i = 0; i < TOTAL_FILAS; i++) {
            for (int j = 0; j < TOTAL_COLUMNAS; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }
    
    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }
    
    public int getTotalFilas() {
        return TOTAL_FILAS;
    }
    
    public int getTotalColumnas() {
        return TOTAL_COLUMNAS;
    }
}