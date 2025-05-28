package src.views;

import java.util.Scanner;

import src.utils.ScannerUtils;

public class MenuView {

    public int mostrarMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("       Menú Principal");
        System.out.println("========================================");
        System.out.println("1. Mostrar máquinas disponibles");
        System.out.println("2. Mostrar productos disponibles");
        System.out.println("3. Realizar una compra");
        System.out.println("4. Salir");
        return ScannerUtils.leerEntero("Seleccione una opción: ");
    }

    public int mostrarMenuOpciones(String titulo, String[] opciones) {
        System.out.println(titulo);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        System.out.print("Seleccione una opción: ");
        System.out.println("\n========================================");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String solicitarMaquina() {
        return ScannerUtils.leerTexto("Ingrese el nombre de la máquina: ");
    }

    public String solicitarProducto() {
        return ScannerUtils.leerTexto("Ingrese el nombre del producto: ");
    }

    public String solicitarMetodoPago() {
        return ScannerUtils.leerTexto("Seleccione el método de pago (EFECTIVO/MONEDERO/BANCARIA): ");
    }
}