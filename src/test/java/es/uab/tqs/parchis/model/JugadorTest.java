package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JugadorTest{
    private Jugador jugador;
    private Tablero tablero;

    @BeforeEach
    void setup() {
        jugador = new Jugador("Leidy");
        tablero = mock(Tablero.class);
    }
}