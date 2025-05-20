public class RetoEv2 {

    static int cola = 0;
    static int caja1 = 0, caja2 = 0, caja3 = 0, caja4 = 0;
    static int atencion = 0;
    static int items = 0;
    static int MinutosSinCola = 0;

    public static void main(String[] args) {
        int jornada = 12 * 60;

        for (int minuto = 1; minuto <= jornada; minuto++) {
            System.out.println(">> MINUTO " + minuto);

            llegadaPersona();
            mostrarCola();
            atenderCajas();
            actualizarCajas();
        }

        resumenFinal();
    }

    public static void llegadaPersona() {
        double probabilidadLlegada = Math.random();
        if (probabilidadLlegada <= 0.4) {
            System.out.println("- Llegó una persona");
            cola++;
        } else {
            System.out.println("- No llegó nadie");
        }
    }

    public static void mostrarCola() {
        System.out.println("> Personas en cola: " + cola);
    }

    public static void atenderCajas() {
        if (caja1 <= 0 && cola > 0) {
            cola--;
            caja1 = generarItems();
            atencion++;
            System.out.println("Pasa una persona a la caja 1 llevando " + caja1 + " items");
        } else if (caja2 <= 0 && cola > 0) {
            cola--;
            caja2 = generarItems();
            atencion++;
            System.out.println("Pasa una persona a la caja 2 llevando " + caja2 + " items");
        } else if (caja3 <= 0 && cola > 0) {
            cola--;
            caja3 = generarItems();
            atencion++;
            System.out.println("Pasa una persona a la caja 3 llevando " + caja3 + " items");
        } else if (caja4 <= 0 && cola > 0) {
            cola--;
            caja4 = generarItems();
            atencion++;
            System.out.println("Pasa una persona a la caja 4 llevando " + caja4 + " items");
        }

        if (cola <= 0) {
            MinutosSinCola++;
        }
    }

    public static void actualizarCajas() {
        if (caja1 > 0) {
            caja1--;
            items++;
            System.out.println("> La caja 1 atiende un item, le quedan " + caja1 + " items");
        }
        if (caja2 > 0) {
            caja2--;
            items++;
            System.out.println("> La caja 2 atiende un item, le quedan " + caja2 + " items");
        }
        if (caja3 > 0) {
            caja3--;
            items++;
            System.out.println("> La caja 3 atiende un item, le quedan " + caja3 + " items");
        }
        if (caja4 > 0) {
            caja4--;
            items++;
            System.out.println("> La caja 4 atiende un item, le quedan " + caja4 + " items");
        }
    }

    public static int generarItems() {
        return (int) (Math.random() * 11) + 5;
    }

    public static void resumenFinal() {
        System.out.println("Minutos totales sin cola: " + MinutosSinCola);
        System.out.println("Al final del día se vendieron un total de " + items + " items");
        System.out.println("Personas atendidas durante la jornada: " + atencion);
    }
}
