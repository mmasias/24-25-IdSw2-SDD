package proyecto.proyectoMejoradoAvance1;

class GestorCajas {
    private final Caja[] cajas;
    
    public GestorCajas(int productos) {
        cajas = new Caja[productos];
        for(int i = 0; i < productos; i++) {
            cajas[i] = new Caja();
        }
    }
    
    public void procesar(Cola cola, Estadisticas stats) {
        for(Caja caja : cajas) {
            if(caja.estaLibre() && !cola.estaVacia()) {
                Cliente cliente = cola.siguiente();
                caja.asignarCliente(cliente);
                stats.clienteAtendido(cliente.getItems());
            }
            caja.procesarMinuto();
        }
    }
    
    public void mostrarEstado() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cajas.length; i++) {
            sb.append("Caja").append(i+1).append(cajas[i].getEstado()).append(" | ");
        }
        System.out.println(sb.toString());
    }
}