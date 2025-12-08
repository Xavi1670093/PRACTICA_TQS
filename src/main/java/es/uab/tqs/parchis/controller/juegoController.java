package es.uab.tqs.parchis.controller;

import es.uab.tqs.parchis.model.Juego;
import es.uab.tqs.parchis.model.Jugador;
import es.uab.tqs.parchis.view.JuegoViewSwing;

public class juegoController {

    private final Juego juego;
    private final JuegoViewSwing vista;

    public juegoController(Juego juego, JuegoViewSwing vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void iniciarPartida() {
        vista.clear();
        vista.mostrarMensaje("¡Comienza la partida de Parchís!\n");
        vista.mostrarEstado(juego);
    }

    public void jugarTurno() {

        if (juego.isTerminado()) {
            vista.mostrarGanador(juego.getGanador());
            return;
        }

        Jugador jugador = juego.getJugadorActual();
        vista.mostrarMensaje("\nTurno de: " + jugador.getNombre() + " (" + jugador.getColor() + ")");

        // --- LA LÓGICA DEL TURNO (con Scanner) ---
        juego.jugarTurno();
        // Actualizar el tablero siempre
        vista.mostrarEstado(juego);

        if (juego.isTerminado()) {
            vista.mostrarGanador(juego.getGanador());
        }
    }
}
