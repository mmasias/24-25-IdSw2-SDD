public class HojaCalculo extends ComponenteHoja {
    private Matriz matriz;
    private Posicion posicion;
    private InterfazUsuario interfaz;
    
    public HojaCalculo() {
        this.matriz = new Matriz();
        this.posicion = new Posicion();
        this.interfaz = new InterfazUsuario();
    }
    
    public static void main(String[] args) {
        HojaCalculo hoja = new HojaCalculo();
        hoja.iniciar();
    }
    
    public void iniciar() {
        Teclado teclado = interfaz.getTeclado();
        
        boolean ejecutando = true;
        while (ejecutando) {
            interfaz.mostrarHoja(matriz, posicion);
            interfaz.mostrarInfoCelda(matriz, posicion);
            
            char comando = teclado.leerComando();
            
            switch (comando) {
                case 'w': posicion.moverArriba(); break;
                case 's': posicion.moverAbajo(); break;
                case 'a': posicion.moverIzquierda(); break;
                case 'd': posicion.moverDerecha(); break;
                case 'e': 
                    Celda celdaActual = matriz.getCelda(posicion.getFila(), posicion.getColumna());
                    teclado.editarCelda(celdaActual);
                    break;
                case 'q': ejecutando = false; break;
            }
        }
        
        teclado.cerrar();
        System.out.println("Programa finalizado.");
    }
}