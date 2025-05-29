package src.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static int generarEnteroAleatorio(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static double generarDoubleAleatorio(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static boolean generarBooleanAleatorio() {
        return random.nextBoolean();
    }
}