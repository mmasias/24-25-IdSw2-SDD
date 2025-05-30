package Modelo;

public class Estadisticas {
    private int minutosColaVacia;
    private int clientesAtendidos;
    private int productosVendidos;
    private int clientesPendientes;

    public void registrarMinuto(Cola cola) {
        if (cola.estaVacia())
            minutosColaVacia++;
    }

    public void clienteAtendido(int productos) {
        clientesAtendidos++;
        productosVendidos += productos;
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

    public int getproductosVendidos() {
        return productosVendidos;
    }

    public int getClientesPendientes() {
        return clientesPendientes;
    }

}