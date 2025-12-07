package es.uab.tqs.parchis.model;

import java.util.List;

import es.uab.tqs.parchis.view.JuegoViewSwing;

public class Juego {

    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final Dado dado;

    private int turnoActual = 0;
    private boolean terminado = false;
    private Jugador ganador;
    private int tirada;

    public Juego(List<Jugador> jugadores, Tablero tablero, Dado dado) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.dado = dado;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public boolean isTerminado() {
        return terminado;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getTirada() {
        return tirada;
    }
    
    /** Decide si el jugador tiene al menos una ficha movible */
    public boolean puedeMover(Jugador jugador) {
        for (Ficha f : jugador.getFichas()) {
            for (int dadoValor = 1; dadoValor <= 6; dadoValor++) {
                if (tablero.movimientPosible(f, dadoValor)) return true;
            }
        }
        return false;
    }

    public void mostrarDado(int valor) {
        String dado = switch (valor) {
            case 1 ->  
                """
                ┌───────┐
                │       │
                │   O   │
                │       │
                └───────┘
                """;
            case 2 ->
                """
                ┌───────┐
                │ O     │
                │       │
                │     O │
                └───────┘
                """;
            case 3 ->
                """
                ┌───────┐
                │ O     │
                │   O   │
                │     O │
                └───────┘
                """;
            case 4 ->
                """
                ┌───────┐
                │ O   O │
                │       │
                │ O   O │
                └───────┘
                """;
            case 5 ->
                """
                ┌───────┐
                │ O   O │
                │   O   │
                │ O   O │
                └───────┘
                """;
            case 6 ->
                """
                ┌───────┐
                │ O   O │
                │ O   O │
                │ O   O │
                └───────┘
                """;
            default -> "ERROR: dado inválido";
        };
        System.out.println(dado);
    }

    public void jugarTurno() {
        if (terminado) return;

        Jugador jugador = getJugadorActual();
        jugador.setMovimientoHecho(false);

        // SOLO calcula la tirada, NO llama a vista
        tirada = dado.lanzar();
        
        mostrarDado(tirada);

        // Si no puede mover -> pasa turno
        if (!puedeMover(jugador)) {
            avanzarTurno();
            return;
        }

        jugador.jugar(tirada, tablero);

        // ¿Victoria?
        if (jugador.haGanado()) {
            terminado = true;
            ganador = jugador;
            return;
        }

        // Si saca 6 -> repite turno
        if (tirada == 6) return;

        // Turno normal
        avanzarTurno();
    }

    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }
}
