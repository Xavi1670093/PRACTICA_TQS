package es.uab.tqs.parchis.model;
import java.util.Random;

public class Dado {
    private static final int MIN = 1;
    private static final int MAX = 6;
    
    private final Random random;

    public Dado(Random random) {
        this.random = random;
    }

    public Dado() {
        this(new Random());
    }

    public int lanzar() {
        return random.nextInt(MAX - MIN + 1) + MIN;
    }

    public int getMin() {
        return MIN;
    }

    public int getMax() {
        return MAX;
    }
}
