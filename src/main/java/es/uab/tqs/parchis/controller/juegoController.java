package es.uab.tqs.parchis.controller;

import es.uab.tqs.parchis.model.*;
import es.uab.tqs.parchis.view.JuegoView;

public class juegoController {

    private final Juego juego;
    private final JuegoView vista;

    public juegoController(Juego juego, JuegoView vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void iniciarPartida() {
        vista.clear();

        vista.mostrarMensaje("¡Comienza la partida de Parchís!\n");
        vista.mostrarEstado(juego);

        while (!juego.isTerminado()) {
            Jugador jugador = juego.getJugadorActual();

            vista.mostrarMensaje("\nTurno de: " + jugador.getNombre() + " (" + jugador.getColor() + ")");
            vista.esperarEnter();
            juego.jugarTurno();
            if (jugador.getMovimientoHecho()) {
                vista.mostrarEstado(juego);
            } 

        }

        vista.mostrarGanador(juego.getGanador());
    }
}
