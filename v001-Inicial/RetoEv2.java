class Tema001 {
    public static void main(String[] args) {

        imprimirTitulo("Primitivos");

        imprimirSubtitulo("Enteros");

        int precio = 12;
        int otroPrecio = precio;
        System.out.println(precio == otroPrecio);

        otroPrecio = 24;
        System.out.println(precio == otroPrecio);

        otroPrecio = 12;
        System.out.println(precio == otroPrecio);

        imprimirSubtitulo("Char");

        char unChar = 'a';
        char otroChar = unChar;
        char tercerChar = 'a';

        System.out.println(unChar + " - " + otroChar + " - " + tercerChar);
        System.out.println(unChar == otroChar);
        System.out.println(unChar == tercerChar);

        imprimirTitulo("String");

        String cadenaTexto = "Una cadena";
        String otraCadena = cadenaTexto;
        String terceraCadena = new String("Una cadena");

        System.out.println(cadenaTexto + " - " + otraCadena + " - " + terceraCadena);
        System.out.println(cadenaTexto == otraCadena);
        System.out.println(cadenaTexto == terceraCadena);

        System.out.println(verHashCode(cadenaTexto) + " - " + verHashCode(otraCadena) + " - " + verHashCode(terceraCadena));        

        cadenaTexto = "XYZ";
        System.out.println(cadenaTexto + " - " + otraCadena + " - " + terceraCadena);

        terceraCadena = "ABC";
        System.out.println(cadenaTexto + " - " + otraCadena + " - " + terceraCadena);

        imprimirTitulo("Arrays");

        int[] array1 = {1, 2, 3};
        int[] array2 = array1;
        int[] array3 = new int[]{1, 2, 3};

        System.out.println(array1 + " - " + array2 + " - " + array3);        

        System.out.println("Los arrays tienen el mismo contenido: " + java.util.Arrays.equals(array1, array3));
        System.out.println("Los arrays son el mismo objeto: " + (array1 == array2));
        System.out.println("Los arrays son diferentes objetos: " + (array1 == array3));

        array2[0] = 100;
        System.out.println("Primer elemento de arr1: " + array1[0]);

        imprimirTitulo("Objetos Personalizados");

        Persona persona1 = new Persona("Alicia");
        Persona persona2 = persona1;
        Persona persona3 = new Persona("Alicia");

        System.out.println(persona1 + " - " + persona2 + " - " + persona3);        
        System.out.println("Los nombres son iguales: " + persona1.getNombre().equals(persona3.getNombre()));
        System.out.println("Los objetos son el mismo objeto: " + (persona1 == persona2));
        System.out.println("Los objetos son diferentes objetos: " + (persona1 != persona3));

        persona2.setNombre("Bob");
        System.out.println("Nombre de Persona1: " + persona1.getNombre());
    }

    static void imprimirLinea() {
        System.out.println("=".repeat(40));
    }

    static void imprimirLinea(int longitud) {
        System.out.println("-".repeat(longitud));
    }

    static void imprimirTitulo(String texto) {
        imprimirLinea();
        System.out.println(texto.toUpperCase());
        imprimirLinea(texto.length());
    }

    static void imprimirSubtitulo(String texto) {
        imprimirLinea(0);
        System.out.println(texto);
        imprimirLinea(texto.length());        
    }

    static int verHashCode(String unaCadena){
        return System.identityHashCode(unaCadena);
    }
 
    

    
}

class Persona {
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}