public abstract class ComponenteHoja {
    protected static final int TOTAL_FILAS = 100;
    protected static final int TOTAL_COLUMNAS = 28;
    protected static final int VISIBLE_FILAS = 15;
    protected static final int VISIBLE_COLUMNAS = 9;
    protected static final int ANCHO_CELDA = 7;
    protected static final String VACIO = "       ";
    
    protected String formatearContenido(String contenido) {
        return (contenido.length() > ANCHO_CELDA) 
            ? contenido.substring(0, ANCHO_CELDA) 
            : String.format("%-" + ANCHO_CELDA + "s", contenido);
    }
    
    protected void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}