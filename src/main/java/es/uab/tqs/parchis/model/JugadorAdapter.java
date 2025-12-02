package es.uab.tqs.parchis.model;

import java.util.Objects;

public class JugadorAdapter implements IJugador {
    private final Jugador jugador;
    private final Ficha.ColorFicha color;

    public JugadorAdapter(Jugador jugador, Ficha.ColorFicha color) {
        this.jugador = Objects.requireNonNull(jugador);
        this.color = Objects.requireNonNull(color);
    }

    @Override
    public boolean jugar(int numDado, Tablero tablero, Ficha.ColorFicha color) {
        // delega en la implementación existente
        return jugador.jugar(numDado, tablero, color);
    }

    public Ficha.ColorFicha getColor() {
        return color;
    }
}
