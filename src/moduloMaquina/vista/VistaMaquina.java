package src.moduloMaquina.vista;

import java.util.List;
import java.util.Scanner;

import src.moduloInventario.Celda;
import src.moduloMaquina.modelo.Maquina;

public class VistaMaquina {

    Scanner scanner;

    public VistaMaquina() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMaquinas(List<Maquina> maquinas) {
        if (maquinas == null || maquinas.size() == 0) {
            System.out.println("[INFO] No hay máquinas disponibles.");
            return;
        }
        System.out.println("Máquinas expendedoras disponibles:");
        for (int i = 0; i < maquinas.size(); i++) {
            System.out.println(i + ": Máquina " + (i + 1));
        }
    }

    public int seleccionarMaquina(List<Maquina> maquinas) {
        int numeroMaquina = -1;
        boolean esValido = false;
        while (esValido == false) {
            System.out.println("=== Selección de Máquina ===");
            mostrarMaquinas(maquinas);
            System.out.print("Ingrese el número de la máquina que desea seleccionar (0-" + (maquinas.size() - 1) + "): ");
            numeroMaquina = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            if (numeroMaquina >= 0 && numeroMaquina < maquinas.size()) {
                esValido = true;
                mostrarProductos(maquinas.get(numeroMaquina));
                return numeroMaquina;
            } else {
                System.out.println("Número de máquina inválido. Intente nuevamente.");
            }
        }

        return numeroMaquina;
    }

    public void mostrarProductos(Maquina maquina) {
        List<Celda> celdas = maquina.getCeldas();
        if (celdas == null || celdas.size() == 0) {
            System.out.println("[INFO] No hay productos disponibles en esta máquina.");
            return;
        }
        System.out.println("Productos disponibles en la máquina:");
        for (int i = 0; i < celdas.size(); i++) {
            Celda celda = celdas.get(i);
            if (celda.getCantidad() > 0) {
                System.out.println("[" + i + "]: " + celda.getProducto().getNombre() + "\t\t Precio: €"
                        + celda.getProducto().getPrecio() + " (Cantidad: " + celda.getCantidad() + ")");
            } else {
                System.out.println("[" + i + "]: " + celda.getProducto().getNombre() + "\t Agotado");
            }
        }
    }

}
