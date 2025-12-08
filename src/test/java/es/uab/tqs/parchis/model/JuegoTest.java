package es.uab.tqs.parchis.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class JuegoTest {

    private Tablero tablero;
    private Dado dado;
    private Jugador j1, j2;
    private Juego juego;

    @BeforeEach
    void setup() {
        tablero = new Tablero();
        tablero.inicializa();

        // mock del dado
        dado = mock(Dado.class);

        j1 = new Jugador("Alice", Ficha.ColorFicha.COLOR_ROJO);
        j2 = new Jugador("Bob", Ficha.ColorFicha.COLOR_AZUL);

        // Añadimos fichas
        j1.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-1, false), false));
        j1.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-2, false), false));
        j1.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-3, false), false));
        j1.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-4, false), false));

        j2.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-9, false), false));
        j2.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-10, false), false));
        j2.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-11, false), false));
        j2.añadirFicha(new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-12, false), false));

        juego = new Juego(List.of(j1, j2), tablero, dado);
    }

    @Test
    void testPrimerTurnoEsPrimerJugador() {
        assertEquals(j1, juego.getJugadorActual());
    }

    @Test
    void testCambioDeTurnoConTiradaNormal() {
        when(dado.lanzar()).thenReturn(3);
        juego.jugarTurno();
        assertEquals(j2, juego.getJugadorActual());
    }

    @Test
    void testJugadorSaca6YRepiteTurno() {
        when(dado.lanzar()).thenReturn(6).thenReturn(3);

        juego.jugarTurno(); // 6 → repite
        assertEquals(j1, juego.getJugadorActual());

        juego.jugarTurno(); // ahora sí cambia
        assertEquals(j2, juego.getJugadorActual());
    }

    @Test
    void testJugadorNoPuedeMoverYPasaTurno() {
        // Mockeamos que ninguna ficha puede moverse
        Juego juegoEspia = spy(juego);
        doReturn(false).when(juegoEspia).puedeMover(j1);

        when(dado.lanzar()).thenReturn(3);

        juegoEspia.jugarTurno();
        assertEquals(j2, juegoEspia.getJugadorActual());
    }

    @Test
    void testVictoriaJugador() {
        // Mockeamos ganar
        Jugador spyJ1 = spy(j1);
        when(spyJ1.haGanado()).thenReturn(true);

        Juego juego2 = new Juego(List.of(spyJ1, j2), tablero, dado);

        when(dado.lanzar()).thenReturn(3);

        juego2.jugarTurno();
        assertTrue(juego2.isTerminado());
        assertEquals(spyJ1, juego2.getGanador());
    }

    void testJuegoTerminadoDevuelveJuegoTerminado() throws Exception {
        // Forzamos juego terminado antes de llamar al método
        var field = Juego.class.getDeclaredField("terminado");
        field.setAccessible(true);
        field.setBoolean(juego, true);

        // Mock del dado para que no falle
        when(dado.lanzar()).thenReturn(1);

        // Ahora cualquier llamada debe devolver JUEGO_TERMINADO
        assertEquals(Juego.ResultadoTurno.JUEGO_TERMINADO, juego.jugarTurno());
    }
}
