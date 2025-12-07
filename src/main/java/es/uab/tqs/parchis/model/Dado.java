package es.uab.tqs.parchis.model;

import java.util.Objects;
import java.util.Random;

public class Dado {

    private static final int MIN = 1;
    private static final int MAX = 6;

    private final Random random;

    
    public Dado(Random random) {
        Objects.requireNonNull(random, "El Random no puede ser null");
        assert MIN < MAX;
        this.random = random;
    }

   
    public Dado() {
        this(new Random());
    }

    public int lanzar() {
        Objects.requireNonNull(random, "El Random debe estar inicializado");

        int resultado = random.nextInt(MAX - MIN + 1) + MIN;

        assert resultado >= MIN && resultado <= MAX :
            "El resultado debe estar en el rango del dado";

        return resultado;
    }

    public int getMin() {
        return MIN;
    }

    public int getMax() {
        return MAX;
    }
}
