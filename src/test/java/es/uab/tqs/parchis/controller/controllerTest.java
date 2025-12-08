package es.uab.tqs.parchis.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import es.uab.tqs.parchis.model.Juego;
import es.uab.tqs.parchis.model.Jugador;
import es.uab.tqs.parchis.view.JuegoViewSwing;

/**
 * Tests unitarios del controlador de juego (MVC) usando Mockito.
 * Se mockean tanto la vista como el modelo.
 */
public class controllerTest {

    private Juego juegoMock;       // Modelo mockeado
    private JuegoViewSwing vistaMock; // Vista mockeada
    private juegoController controller; // Controlador real
    private Jugador jugadorMock;      // Jugador mockeado

    @BeforeEach
    void setup() {
        // Crear mocks
        juegoMock = mock(Juego.class);
        vistaMock = mock(JuegoViewSwing.class);

        // Instanciar el controlador con mocks
        controller = new juegoController(juegoMock, vistaMock);

        // Mock para el jugador actual
        jugadorMock = mock(Jugador.class);
        when(juegoMock.getJugadorActual()).thenReturn(jugadorMock);
    }

    /**
     * Verifica que iniciarPartida limpie la vista,
     * muestre el mensaje inicial y actualice el estado.
     */
    @Test
    void testIniciarPartida() {
        controller.iniciarPartida();

        verify(vistaMock).clear();
        verify(vistaMock).mostrarMensaje("¡Comienza la partida de Parchís!\n");
        verify(vistaMock).mostrarEstado(juegoMock);
    }

    /**
     * Si el juego ya terminó, el controlador debe mostrar
     * al ganador en la vista.
     */
    @Test
    void testJugarTurno_CuandoJuegoTerminado() {
        when(juegoMock.isTerminado()).thenReturn(true);
        when(juegoMock.getGanador()).thenReturn(jugadorMock);

        controller.jugarTurno();

        verify(vistaMock).mostrarGanador(jugadorMock);
    }

    /**
     * Caso normal: el juego no ha terminado.
     * Se espera que el turno se juegue, se muestre mensaje y estado actualizado.
     */
    @Test
    void testJugarTurno_JuegoNoTerminado_TurnoNormal() {
        when(juegoMock.isTerminado()).thenReturn(false);

        controller.jugarTurno();

        verify(vistaMock).mostrarMensaje(anyString());  // Mensaje de turno
        verify(juegoMock).jugarTurno();                 // Modelo ejecuta el turno
        verify(vistaMock).mostrarEstado(juegoMock);     // Vista actualiza estado
    }

    /**
     * El juego termina durante el turno.
     * Se debe mostrar el mensaje, actualizar estado y finalmente mostrar ganador.
     */
    @Test
    void testJugarTurno_JuegoTerminaDuranteTurno() {
        when(juegoMock.isTerminado())
            .thenReturn(false) // antes del turno
            .thenReturn(true); // después del turno

        when(juegoMock.getGanador()).thenReturn(jugadorMock);

        controller.jugarTurno();

        verify(vistaMock).mostrarMensaje(anyString());
        verify(juegoMock).jugarTurno();
        verify(vistaMock).mostrarEstado(juegoMock);
        verify(vistaMock).mostrarGanador(jugadorMock);
    }

    /**
     * Asegura que si el juego no termina durante el turno,
     * nunca se muestre al ganador.
     */
    @Test
    void testJugarTurno_NoDebeMostrarGanador_IfNoTermina() {
        when(juegoMock.isTerminado()).thenReturn(false);

        controller.jugarTurno();

        verify(vistaMock, never()).mostrarGanador(any());
    }
}
