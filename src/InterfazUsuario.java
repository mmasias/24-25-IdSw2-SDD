public class InterfazUsuario extends ComponenteHoja {
    private Teclado teclado;
    
    public InterfazUsuario() {
        this.teclado = new Teclado();
    }
    
    public Teclado getTeclado() {
        return teclado;
    }
    
    public void mostrarHoja(Matriz matriz, Posicion posicion) {
        limpiarPantalla();
        
        int filaActual = posicion.getFila();
        int columnaActual = posicion.getColumna();
        
        System.out.print("      ");
        for (int j = columnaActual; j < columnaActual + VISIBLE_COLUMNAS && j < matriz.getTotalColumnas(); j++) {
            System.out.printf(" %-7s ", (char) ('A' + j));
        }
        System.out.println();
        
        for (int i = filaActual; i < filaActual + VISIBLE_FILAS && i < matriz.getTotalFilas(); i++) {
            System.out.printf("%4d  ", i + 1);
            for (int j = columnaActual; j < columnaActual + VISIBLE_COLUMNAS && j < matriz.getTotalColumnas(); j++) {
                String contenidoVisible = matriz.getCelda(i, j).getContenidoVisible();
                if (i == filaActual && j == columnaActual) {
                    System.out.print("[" + contenidoVisible + "] ");
                } else {
                    System.out.print(" " + contenidoVisible + "  ");
                }
            }
            System.out.println();
        }
    }
    
    public void mostrarInfoCelda(Matriz matriz, Posicion posicion) {
        System.out.println("\nContenido completo de la celda seleccionada: " + 
            matriz.getCelda(posicion.getFila(), posicion.getColumna()).getContenidoCompleto());
        System.out.println("Usa W/A/S/D para mover, 'e' para editar, 'q' para salir:");
    }
}