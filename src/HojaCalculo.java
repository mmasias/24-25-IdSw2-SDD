import java.util.Scanner;

abstract class ComponenteHoja {
    protected static final int ANCHO_CELDA = 7;
    protected static final String VACIO = "       ";
    
    public abstract void inicializar();
}

class Posicion {
    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public void moverArriba() {
        if (fila > 0) fila--;
    }
    
    public void moverAbajo(int maxFilas) {
        if (fila < maxFilas - 1) fila++;
    }
    
    public void moverIzquierda() {
        if (columna > 0) columna--;
    }
    
    public void moverDerecha(int maxColumnas) {
        if (columna < maxColumnas - 1) columna++;
    }
}

class Celda {
    private String contenidoCompleto;
    private String contenidoVisible;

    public Celda() {
        this.contenidoCompleto = "";
        this.contenidoVisible = ComponenteHoja.VACIO;
    }

    public String getContenidoCompleto() {
        return contenidoCompleto;
    }

    public String getContenidoVisible() {
        return contenidoVisible;
    }

    public void setContenido(String contenido) {
        this.contenidoCompleto = contenido;
        this.contenidoVisible = (contenido.length() > ComponenteHoja.ANCHO_CELDA) 
            ? contenido.substring(0, ComponenteHoja.ANCHO_CELDA) 
            : String.format("%-7s", contenido);
    }
}

class Matriz extends ComponenteHoja {
    private final int TOTAL_FILAS;
    private final int TOTAL_COLUMNAS;
    private Celda[][] celdas;
    
    public Matriz(int filas, int columnas) {
        this.TOTAL_FILAS = filas;
        this.TOTAL_COLUMNAS = columnas;
        this.celdas = new Celda[TOTAL_FILAS][TOTAL_COLUMNAS];
    }
    
    @Override
    public void inicializar() {
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

class Teclado {
    private Scanner scanner;
    
    public Teclado() {
        this.scanner = new Scanner(System.in);
    }
    
    public char leerComando() {
        return scanner.next().charAt(0);
    }
    
    public String leerLinea() {
        scanner.nextLine();
        return scanner.nextLine();
    }
    
    public void modificarCelda(Celda celda) {
        System.out.print("Ingrese valor para la celda: ");
        String entrada = leerLinea();
        celda.setContenido(entrada);
    }
    
    public void cerrar() {
        scanner.close();
    }
}

class InterfazUsuario {
    private static final int VISIBLE_FILAS = 15;
    private static final int VISIBLE_COLUMNAS = 9;
    private Teclado teclado;
    
    public InterfazUsuario() {
        this.teclado = new Teclado();
    }
    
    public void mostrarHoja(Matriz matriz, Posicion posicionActual) {
        System.out.println("\033[H\033[2J"); 
        System.out.flush();
        
        int filaActual = posicionActual.getFila();
        int columnaActual = posicionActual.getColumna();

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
    
    public void mostrarContenidoCelda(Celda celda) {
        System.out.println("\nContenido completo de la celda seleccionada: " + celda.getContenidoCompleto());
    }
    
    public void mostrarInstrucciones() {
        System.out.println("Usa W/A/S/D para mover, 'e' para editar, 'q' para salir:");
    }
    
    public char leerComando() {
        return teclado.leerComando();
    }
    
    public void editarCelda(Celda celda) {
        teclado.modificarCelda(celda);
    }
    
    public void mostrarMensajeFinal() {
        System.out.println("Programa finalizado.");
    }
    
    public void cerrar() {
        teclado.cerrar();
    }
}

public class HojaCalculo {
    private static final int TOTAL_FILAS = 100;
    private static final int TOTAL_COLUMNAS = 28;
    
    private Matriz matriz;
    private Posicion posicionActual;
    private InterfazUsuario interfaz;

    public HojaCalculo() {
        this.matriz = new Matriz(TOTAL_FILAS, TOTAL_COLUMNAS);
        this.posicionActual = new Posicion(0, 0);
        this.interfaz = new InterfazUsuario();
    }
    
    public void iniciar() {
        matriz.inicializar();
        
        boolean ejecutando = true;
        while (ejecutando) {
            interfaz.mostrarHoja(matriz, posicionActual);
            interfaz.mostrarContenidoCelda(matriz.getCelda(posicionActual.getFila(), posicionActual.getColumna()));
            interfaz.mostrarInstrucciones();
            
            char comando = interfaz.leerComando();
            ejecutando = procesarComando(comando);
        }
        
        interfaz.cerrar();
        interfaz.mostrarMensajeFinal();
    }
    
    private boolean procesarComando(char comando) {
        switch (comando) {
            case 'w': 
                posicionActual.moverArriba();
                break;
            case 's': 
                posicionActual.moverAbajo(matriz.getTotalFilas());
                break;
            case 'a': 
                posicionActual.moverIzquierda();
                break;
            case 'd': 
                posicionActual.moverDerecha(matriz.getTotalColumnas());
                break;
            case 'e': 
                interfaz.editarCelda(matriz.getCelda(posicionActual.getFila(), posicionActual.getColumna()));
                break;
            case 'q': 
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HojaCalculo hojaCalculo = new HojaCalculo();
        hojaCalculo.iniciar();
    }
}