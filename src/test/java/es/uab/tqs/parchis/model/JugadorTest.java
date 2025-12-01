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

    
    @BeforeEach
    void setup() {
        jugador = new Jugador("Leidy", Ficha.ColorFicha.COLOR_ROJO);
        tablero = mock(Tablero.class);

        ficha1 = mock(Ficha.class);
        ficha2 = mock(Ficha.class);

        Posicion pos1 = mock(Posicion.class);
        Posicion pos2 = mock(Posicion.class);

        when(ficha1.getPosicion()).thenReturn(pos1);
        when(ficha2.getPosicion()).thenReturn(pos2);

        when(pos1.getNumero()).thenReturn(1);
        when(pos2.getNumero()).thenReturn(2);

        jugador.getFichas().add(ficha1);
        jugador.getFichas().add(ficha2);
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


}