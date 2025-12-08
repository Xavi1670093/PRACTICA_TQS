package es.uab.tqs.parchis.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JugadorTest {
    private Jugador jugador;
    private Tablero tablero;
    private Ficha ficha1;
    private Ficha ficha2;
    private Ficha ficha3;
    private Ficha ficha4;

    @BeforeEach
    void setup() {
        jugador = new Jugador("Leidy", Ficha.ColorFicha.COLOR_ROJO);
        tablero = mock(Tablero.class);

        ficha1 = mock(Ficha.class);
        ficha2 = mock(Ficha.class);
        ficha3 = mock(Ficha.class);
        ficha4 = mock(Ficha.class);

        Posicion pos1 = mock(Posicion.class);
        Posicion pos2 = mock(Posicion.class);
        Posicion pos3 = mock(Posicion.class);
        Posicion pos4 = mock(Posicion.class);

        when(pos1.getNumero()).thenReturn(39);
        when(pos2.getNumero()).thenReturn(2);
        when(pos3.getNumero()).thenReturn(10);
        when(pos4.getNumero()).thenReturn(20);

        when(ficha1.getPosicion()).thenReturn(pos1);
        when(ficha2.getPosicion()).thenReturn(pos2);
        when(ficha3.getPosicion()).thenReturn(pos3);
        when(ficha4.getPosicion()).thenReturn(pos4);

        when(ficha1.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha2.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha3.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);
        when(ficha4.getColor()).thenReturn(Ficha.ColorFicha.COLOR_ROJO);

        jugador.añadirFicha(ficha1);
        jugador.añadirFicha(ficha2);
        jugador.añadirFicha(ficha3);
        jugador.añadirFicha(ficha4);

        // tablero devuelve la misma ficha en su casilla
        when(tablero.obtenerIndice(anyInt())).thenReturn(new int[]{0, 0});
        when(tablero.getFicha(0, 0)).thenReturn(ficha1, ficha2, ficha3, ficha4);
    }

    // ---------------------------------------------------------------------

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

    // ---------------------------------------------------------------------

    @Test
    void testNingunaFichaSePuedeMover() {
        when(tablero.movimientPosible(any(), anyInt())).thenReturn(false);

        boolean resultado = jugador.jugar(5, tablero);

        assertFalse(resultado);
        verify(tablero, never()).mouFicha(any(), anyInt());
    }

    // ---------------------------------------------------------------------

    @Test
    void testFicha1EnBarrera() {
        InputStream original = System.in;
        try {
            when(ficha1.isBarrera()).thenReturn(true);
            when(ficha2.isBarrera()).thenReturn(false);

            when(tablero.movimientPosible(ficha1, 3)).thenReturn(false);
            when(tablero.movimientPosible(ficha2, 3)).thenReturn(true);

            System.setIn(new ByteArrayInputStream("1\n2\n".getBytes()));
            boolean res = jugador.jugar(3, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha2, 3);
            verify(tablero, never()).mouFicha(ficha1, 3);
        } finally {
            System.setIn(original);
        }
    }

    // ---------------------------------------------------------------------

    @Test
    void añadirFicha() {
        assertEquals(4, jugador.getFichas().size());
        assertThrows(IllegalArgumentException.class, () -> jugador.añadirFicha(mockFichaColorVerde()));
        assertEquals(4, jugador.getFichas().size());
    }

    private Ficha mockFichaColorVerde() {
        Ficha f = mock(Ficha.class);
        when(f.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);
        return f;
    }

    // ---------------------------------------------------------------------

    @Test
    void haGanadoTest() {
        Jugador jugadorVerde = new Jugador("Xavi", Ficha.ColorFicha.COLOR_VERDE);

        Ficha f1 = mock(Ficha.class);
        Ficha f2 = mock(Ficha.class);
        Ficha f3 = mock(Ficha.class);
        Ficha f4 = mock(Ficha.class);

        Posicion p = mock(Posicion.class);
        when(p.getNumero()).thenReturn(92);

        when(f1.getPosicion()).thenReturn(p);
        when(f2.getPosicion()).thenReturn(p);
        when(f3.getPosicion()).thenReturn(p);
        when(f4.getPosicion()).thenReturn(p);

        when(f1.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);
        when(f2.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);
        when(f3.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);
        when(f4.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);

        jugadorVerde.añadirFicha(f1);
        jugadorVerde.añadirFicha(f2);
        jugadorVerde.añadirFicha(f3);
        jugadorVerde.añadirFicha(f4);

        assertTrue(jugadorVerde.haGanado());
        assertFalse(jugador.haGanado());
    }

    // ---------------------------------------------------------------------

    @Test
    void testFichaConPosicionNullNoSeAgrega() {
        when(ficha1.getPosicion()).thenReturn(null); // se descarta

        when(tablero.movimientPosible(ficha2, 3)).thenReturn(true);

        InputStream original = System.in;
        try {
            System.setIn(new ByteArrayInputStream("1\n2\n".getBytes()));
            boolean res = jugador.jugar(3, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha2, 3);
            verify(tablero, never()).mouFicha(ficha1, 3);
        } finally {
            System.setIn(original);
        }
    }

    // ---------------------------------------------------------------------

    @Test
    void testFichaColorDistintoNoSeAgrega() {
        // NO añadimos esta ficha al jugador → así como exige tu código
        Ficha fichaDistinta = mock(Ficha.class);
        when(fichaDistinta.getColor()).thenReturn(Ficha.ColorFicha.COLOR_VERDE);
        when(fichaDistinta.getPosicion()).thenReturn(mock(Posicion.class));

        when(tablero.movimientPosible(ficha1, 3)).thenReturn(true);

        InputStream original = System.in;
        try {
            System.setIn(new ByteArrayInputStream("1\n".getBytes()));
            boolean res = jugador.jugar(3, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha1, 3);
            verify(tablero, never()).mouFicha(fichaDistinta, 3);
        } finally {
            System.setIn(original);
        }
    }

    // ---------------------------------------------------------------------

    @Test
    void testInputFueraDeRango() {
        when(tablero.movimientPosible(any(), anyInt())).thenReturn(true);

        InputStream original = System.in;
        try {
            System.setIn(new ByteArrayInputStream("0\n2\n".getBytes()));
            boolean res = jugador.jugar(3, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha2, 3);
        } finally {
            System.setIn(original);
        }
    }

    // ---------------------------------------------------------------------
    // LOOP TESTING
    // ---------------------------------------------------------------------

    @Test
    void loopTest_zeroIterations() {
        // movible solo ficha1
        when(tablero.movimientPosible(ficha1, 4)).thenReturn(true);

        InputStream original = System.in;
        try {
            // Input válido a la primera → el loop NO repite
            System.setIn(new ByteArrayInputStream("1\n".getBytes()));

            boolean res = jugador.jugar(4, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha1, 4);
        } finally {
            System.setIn(original);
        }
    }

    @Test
    void loopTest_oneIteration() {
        when(tablero.movimientPosible(ficha1, 4)).thenReturn(true);

        InputStream original = System.in;
        try {
            // Primer input inválido (0), segundo válido (1)
            System.setIn(new ByteArrayInputStream("0\n1\n".getBytes()));

            boolean res = jugador.jugar(4, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha1, 4);
        } finally {
            System.setIn(original);
        }
    }

    @Test
    void loopTest_twoIterations() {
        when(tablero.movimientPosible(ficha1, 4)).thenReturn(true);

        InputStream original = System.in;
        try {
            // Dos entradas inválidas -> la tercera válida
            System.setIn(new ByteArrayInputStream("abc\n0\n1\n".getBytes()));

            boolean res = jugador.jugar(4, tablero);

            assertTrue(res);
            verify(tablero).mouFicha(ficha1, 4);
        } finally {
            System.setIn(original);
        }
    }
}


