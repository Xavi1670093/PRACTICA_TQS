package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class JugadorTest{
    private Jugador jugador;
    private Tablero tablero;
    private Ficha ficha1;
    private Ficha ficha2;
    private Ficha ficha3;
    private Ficha ficha4;
    private Ficha ficha5;

    
    @BeforeEach
    void setup() {
        jugador = new Jugador("Leidy", Ficha.ColorFicha.COLOR_ROJO);
        tablero = mock(Tablero.class);

        ficha1 = mock(Ficha.class);
        ficha2 = mock(Ficha.class);
        ficha3 = mock(Ficha.class);
        ficha4 = mock (Ficha.class);
        ficha5 = mock(Ficha.class);

        Posicion pos1 = mock(Posicion.class);
        Posicion pos2 = mock(Posicion.class);

        when(ficha1.getPosicion()).thenReturn(pos1);
        when(ficha2.getPosicion()).thenReturn(pos2);
        when(pos1.getNumero()).thenReturn(1);
        when(pos2.getNumero()).thenReturn(2);

        when(ficha1.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha2.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha3.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha4.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha1.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);

        jugador.añadirFicha(ficha1);
        jugador.añadirFicha(ficha2);
        jugador.añadirFicha(ficha3);
        jugador.añadirFicha(ficha4);
    
    }

    @Test
    void testMoverFichaElegida() {
        InputStream original = System.in;

        try {
            when(tablero.movimientPosible(ficha1, 5)).thenReturn(true);
            when(tablero.movimientPosible(ficha2, 5)).thenReturn(true);

            System.setIn(new ByteArrayInputStream("2\n".getBytes()));

            boolean resultado = jugador.jugar(5, tablero);

            assertTrue(resultado);
            verify(tablero).mouFicha(ficha2, 5);
            verify(tablero, never()).mouFicha(ficha1, 5);

        } finally {
            System.setIn(original);
        }
    }            

    @Test
    void testNingunaFichaSePuedeMover() {
        
        when(tablero.movimientPosible(any(), anyInt())).thenReturn(false);

        boolean resultado = jugador.jugar(5, tablero);

        assertFalse(resultado);
        verify(tablero, never()).mouFicha(any(), anyInt());
    }

    @Test
    void testFicha1EnBarrera() {
        InputStream original = System.in;

        try {
            when(ficha1.isBarrera()).thenReturn(true);
            when(ficha2.isBarrera()).thenReturn(false);

            when(tablero.movimientPosible(ficha1, 3)).thenReturn(false);
            when(tablero.movimientPosible(ficha2, 3)).thenReturn(true);

            System.setIn(new ByteArrayInputStream("1\n".getBytes()));

            boolean resultado = jugador.jugar(3, tablero);

            assertTrue(resultado);
            verify(tablero).mouFicha(ficha2, 3);
            verify(tablero, never()).mouFicha(ficha1, 3);

        } finally {
            System.setIn(original);
        }
    }

    @Test
    void añadirFicha() {
        assertEquals(4, jugador.getFichas().size());
        assertTrue(jugador.getFichas().contains(ficha1));
        assertTrue(jugador.getFichas().contains(ficha2));
        assertTrue(jugador.getFichas().contains(ficha3));
        assertTrue(jugador.getFichas().contains(ficha4));

        jugador.añadirFicha(ficha5);

        assertEquals(4, jugador.getFichas().size());
        assertFalse(jugador.getFichas().contains(ficha5));

    }


}