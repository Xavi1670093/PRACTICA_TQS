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
        jugador = new Jugador("Leidy", Ficha.ColorFicha.COLOR_ROJO);
        tablero = mock(Tablero.class);

        ficha1 = mock(Ficha.class);
        ficha2 = mock(Ficha.class);
        
        jugador.getFichas().add(ficha1);
        jugador.getFichas().add(ficha2);
    }

    @Test
    
    void testJugaElegirFicha() {
        // TEST MOVER FICHA ELEGIDA
        when(tablero.movimientPosible(ficha1, 5)).thenReturn(true);
        when(tablero.movimientPosible(ficha2, 5)).thenReturn(true);

        String input1 = "1\n";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));

        boolean resultado1 = jugador.jugar(5, tablero);

        assertTrue(resultado1);
        verify(tablero).mouFicha(ficha2, 5);
        verify(tablero, never()).mouFicha(ficha1, 5);

        // TEST NINGUNA FICHA SE PUEDE MOVER
        when(tablero.movimientPosible(ficha1, 5)).thenReturn(false);
        when(tablero.movimientPosible(ficha2, 5)).thenReturn(false);

        boolean resultado2 = jugador.jugar(5, tablero);

        assertFalse(resultado2);
        verify(tablero, never()).mouFicha(any(), anyInt());

        // TEST FICHA1 EN BARRERA
        when(ficha1.isBarrera()).thenReturn(true);
        when(ficha2.isBarrera()).thenReturn(false);

        when(tablero.movimientPosible(ficha1, 3)).thenReturn(false);
        when(tablero.movimientPosible(ficha2, 3)).thenReturn(true);

        String input3 = "0\n";
        System.setIn(new ByteArrayInputStream(input3.getBytes()));

        boolean resultado3 = jugador.jugar(3, tablero);

        assertTrue(resultado3);
        verify(tablero).mouFicha(ficha2, 3);
        verify(tablero, never()).mouFicha(ficha1, 3);
    }

}