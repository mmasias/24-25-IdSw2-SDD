import java.util.Scanner;

public class Teclado extends ComponenteHoja {
    private Scanner scanner;
    
    public Teclado() {
        this.scanner = new Scanner(System.in);
    }
    
    public char leerComando() {
        return scanner.next().charAt(0);
    }
    
    public String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        scanner.nextLine();
        return scanner.nextLine();
    }
    
    public void editarCelda(Celda celda) {
        String entrada = leerEntrada("Ingrese valor para la celda: ");
        celda.setContenido(entrada);
    }
    
    public void cerrar() {
        scanner.close();
    }
}