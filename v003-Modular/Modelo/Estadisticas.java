package Modelo;

public class Estadisticas {
    private int minutosColaVacia;
    private int clientesAtendidos;
    private int itemsVendidos;
    private int clientesPendientes;

    public void registrarMinuto(Cola cola) {
        if (cola.estaVacia())
            minutosColaVacia++;
    }

    public void clienteAtendido(int items) {
        clientesAtendidos++;
        itemsVendidos += items;
    }

    public void setClientesPendientes(int cantidad) {
        clientesPendientes = cantidad;
    }

    public int getMinutosColaVacia() {
        return minutosColaVacia;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public int getItemsVendidos() {
        return itemsVendidos;
    }

    public int getClientesPendientes() {
        return clientesPendientes;
    }

}