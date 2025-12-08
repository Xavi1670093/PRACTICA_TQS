package es.uab.tqs.parchis.model;

import java.util.List;

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
        //PRECONDICIONES
        assert !jugadores.isEmpty();
        assert turnoActual>= 0 && turnoActual < jugadores.size();

        Jugador j = jugadores.get(turnoActual);

        //POSTCONDICIÓN
        assert j != null;

        return j;

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
        //PRECONDICIONES
        assert jugadores != null : "No hay jugadores";
        assert jugadores.contains(jugador);
        
        boolean puede = false;
        for (Ficha f : jugador.getFichas()) {
            for (int dadoValor = 1; dadoValor <= 6; dadoValor++) {
                if (tablero.movimientPosible(f, dadoValor)) puede = true;
            }
        }

        //POSTCONDICIÓN
        assert (puede == true || puede == false);

        return puede;
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
        //PRECONDICIONES
        assert !jugadores.isEmpty();
        assert !terminado;
        assert tablero != null;
        assert dado != null;

        if (terminado) return;

        int turnoAntes = turnoActual;
        Jugador jugadorAntes = getJugadorActual();
        boolean estabaTerminado = terminado;

        Jugador jugador = getJugadorActual();
        jugador.setMovimientoHecho(false);

        // SOLO calcula la tirada
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

        //POSTCONDICIONES
        assert tirada >= 1 && tirada <= 6;

        if(terminado){
            assert ganador != null:"El juego ha terminado pero no hay ganador";
            assert jugadores.contains(ganador): "el ganador no pertenece al juego";
        }
        
        if (!estabaTerminado && tirada != 6 && puedeMover(jugadorAntes)) {
            assert turnoActual != turnoAntes : "el turno debía avanzar pero no lo hizo";
        }

        if(tirada == 6 && !terminado){
            assert turnoActual == turnoAntes : "Ha sacado 6 y no ha vuelto a tirar";
        }
    }

    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }
}
