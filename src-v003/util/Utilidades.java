package util;

public class Utilidades {
    private Utilidades() {
        // clase utilitaria, no instanciable
    }

    public static boolean esNumero(String input) {
        if (input == null) return false;
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
