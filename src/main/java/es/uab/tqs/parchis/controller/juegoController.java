package es.uab.tqs.parchis.controller;

import es.uab.tqs.parchis.model.*;
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

        // Asignamos el primer click.
        vista.esperarClickDado(() -> jugarTurno());
    }

    private void jugarTurno() {

        if (juego.isTerminado()) {
            vista.mostrarGanador(juego.getGanador());
            return;
        }

        Jugador jugador = juego.getJugadorActual();
        vista.mostrarMensaje("\nTurno de: " + jugador.getNombre() + " (" + jugador.getColor() + ")");

        // Acción del juego
        juego.jugarTurno();
        vista.mostrarDado(juego.getTirada());

        Jugador jugadorDespues = juego.getJugadorActual();

        if (jugadorDespues.getMovimientoHecho()) {
            vista.mostrarEstado(juego);
        }

        // Si no ha terminado, volvemos a esperar otro clic
        if (!juego.isTerminado()) {
            vista.esperarClickDado(() -> jugarTurno());
        } else {
            vista.mostrarGanador(juego.getGanador());
        }
    }
}
