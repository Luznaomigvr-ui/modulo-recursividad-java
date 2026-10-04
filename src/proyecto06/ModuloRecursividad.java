
package proyecto06;

public class ModuloRecursividad {

    // ===== Contadores de llamadas recursivas =====
    public static long llamadasFactorialRec = 0;
    public static long llamadasFibonacciRec = 0;
    public static long movimientosHanoi = 0;

    // ================================================================
    // FACTORIAL
    // ================================================================

    /**
     * Factorial recursivo.
     * Condición de paro: n == 0 o n == 1 -> devuelve 1.
     */
    public static long factorialRecursivo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo");
        }
        llamadasFactorialRec++;
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursivo(n - 1);
    }

    /**
     * Factorial iterativo.
     */
    public static long factorialIterativo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // ================================================================
    // FIBONACCI
    // ================================================================

    /**
     * Fibonacci recursivo (ineficiente, exponencial).
     * Condición de paro: n == 0 -> 0 ; n == 1 -> 1.
     */
    public static long fibonacciRecursivo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo");
        }
        llamadasFibonacciRec++;
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
    }

    /**
     * Fibonacci iterativo (eficiente, lineal).
     */
    public static long fibonacciIterativo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;
        long a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // ================================================================
    // TORRES DE HANOI
    // ================================================================

    /**
     * Torres de Hanoi recursivo.
     * Condición de paro: n == 1 -> mueve directamente de origen a destino.
     */
    public static void hanoiRecursivo(int n, char origen, char auxiliar, char destino) {
        if (n < 1) {
            throw new IllegalArgumentException("n debe ser >= 1");
        }
        hanoiRecursivoInterno(n, origen, auxiliar, destino);
    }

    private static void hanoiRecursivoInterno(int n, char origen, char auxiliar, char destino) {
        if (n == 1) {
            movimientosHanoi++;
            System.out.println("Mover disco 1 de " + origen + " a " + destino);
            return;
        }
        hanoiRecursivoInterno(n - 1, origen, destino, auxiliar);
        movimientosHanoi++;
        System.out.println("Mover disco " + n + " de " + origen + " a " + destino);
        hanoiRecursivoInterno(n - 1, auxiliar, origen, destino);
    }

    // ================================================================
    // PRUEBA DE SATURACIÓN DE PILA
    // ================================================================

    /**
     * Incrementa recursivamente hasta provocar StackOverflowError.
     * @return la profundidad alcanzada antes de la excepción.
     */
    public static int probarStackOverflow() {
        try {
            profundidadRecursiva(0);
            return -1; // no debería llegar aquí
        } catch (StackOverflowError e) {
            return profundidadActual;
        }
    }

    private static int profundidadActual = 0;

    private static void profundidadRecursiva(int nivel) {
        profundidadActual = nivel;
        profundidadRecursiva(nivel + 1);
    }
}
