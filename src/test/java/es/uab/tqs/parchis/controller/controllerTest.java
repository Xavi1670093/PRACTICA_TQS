package es.uab.tqs.parchis.controller;

import es.uab.tqs.parchis.model.*;
import es.uab.tqs.parchis.view.JuegoViewSwing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class controllerTest {

    private Juego juegoMock;
    private JuegoViewSwing vistaMock;
    private juegoController controller;
    private Jugador jugadorMock;

    @BeforeEach
    void setup() {
        juegoMock = mock(Juego.class);
        vistaMock = mock(JuegoViewSwing.class);
        controller = new juegoController(juegoMock, vistaMock);

        jugadorMock = mock(Jugador.class);
        when(juegoMock.getJugadorActual()).thenReturn(jugadorMock);
    }


    @Test
    void testIniciarPartida() {
        controller.iniciarPartida();

        verify(vistaMock).clear();
        verify(vistaMock).mostrarMensaje("¡Comienza la partida de Parchís!\n");
        verify(vistaMock).mostrarEstado(juegoMock);
    }


    @Test
    void testJugarTurno_CuandoJuegoTerminado() {
        when(juegoMock.isTerminado()).thenReturn(true);
        when(juegoMock.getGanador()).thenReturn(jugadorMock);

        controller.jugarTurno();

        verify(vistaMock).mostrarGanador(jugadorMock);
    }

    @Test
    void testJugarTurno_JuegoNoTerminado_TurnoNormal() {
        when(juegoMock.isTerminado()).thenReturn(false);

        controller.jugarTurno();

        verify(vistaMock).mostrarMensaje(anyString());
        verify(juegoMock).jugarTurno();
        verify(vistaMock).mostrarEstado(juegoMock);

    }

    @Test
    void testJugarTurno_JuegoTerminaDuranteTurno() {
        when(juegoMock.isTerminado()).thenReturn(false) // antes del turno
        .thenReturn(true);        // después de jugar turno

        when(juegoMock.getGanador()).thenReturn(jugadorMock);

        controller.jugarTurno();

        verify(vistaMock).mostrarMensaje(anyString());
        verify(juegoMock).jugarTurno();
        verify(vistaMock).mostrarEstado(juegoMock);
        verify(vistaMock).mostrarGanador(jugadorMock);
    }

    @Test
    void testJugarTurno_NoDebeMostrarGanador_IfNoTermina() {
        when(juegoMock.isTerminado()).thenReturn(false);

        controller.jugarTurno();

        verify(vistaMock, never()).mostrarGanador(any());
    }

}
