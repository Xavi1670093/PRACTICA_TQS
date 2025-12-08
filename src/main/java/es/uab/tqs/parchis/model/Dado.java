package es.uab.tqs.parchis.model;

import java.util.Objects;
import java.util.Random;

public class Dado {

    private static final int MIN = 1;
    private static final int MAX = 6;

    private final Random random;

    /** INVARIANTE DEL DADO:
     *  - El rango debe ser siempre válido: MIN < MAX
     *  - MIN debe ser 1 y MAX debe ser 6 (dado clásico)
     *  - El generador Random nunca debe ser null
     */
    private void checkInvariant() {
        assert MIN == 1 : "El valor mínimo del dado debe ser 1";
        assert MAX == 6 : "El valor máximo del dado debe ser 6";
        assert MIN < MAX : "El rango del dado es inválido";
        assert random != null : "El generador Random no puede ser null";
    }

    public Dado(Random random) {
        //PRECONDICIONES
        Objects.requireNonNull(random, "El Random no puede ser null");
        this.random = random;

        checkInvariant(); // Verificación tras construcción
    }

    public Dado() {
        this(new Random());
    }

    public int lanzar() {
        //PRECONDICIONES
        Objects.requireNonNull(random, "El Random debe estar inicializado");

        checkInvariant(); // Invariante antes del cálculo

        int resultado = random.nextInt(MAX - MIN + 1) + MIN;

        //POSTCONDICIONES
        assert resultado >= MIN && resultado <= MAX :
            "El resultado debe estar en el rango del dado";

        checkInvariant(); // Debe seguir siendo válido

        return resultado;
    }

    public int getMin() {
        return MIN;
    }

    public int getMax() {
        return MAX;
    }
}
