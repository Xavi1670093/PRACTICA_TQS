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

    /** INVARIANTES DEL JUEGO:
     *  - jugadores no puede ser null ni estar vacío.
     *  - tablero y dado nunca pueden ser null.
     *  - turnoActual siempre debe estar dentro del rango [0, jugadores.size()-1].
     *  - si terminado = true, debe existir un ganador válido y perteneciente al juego.
     */
    private void checkInvariant() {
        assert jugadores != null : "La lista de jugadores no puede ser null";
        assert !jugadores.isEmpty() : "Debe haber al menos un jugador";

        assert tablero != null : "El tablero no puede ser null";
        assert dado != null : "El dado no puede ser null";

        assert turnoActual >= 0 && turnoActual < jugadores.size() :
                "turnoActual fuera de rango";

        if (terminado) {
            assert ganador != null : "El juego terminó pero no hay ganador";
            assert jugadores.contains(ganador) :
                    "El ganador no pertenece al juego";
        }
    }

    public Juego(List<Jugador> jugadores, Tablero tablero, Dado dado) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.dado = dado;

        checkInvariant();
    }

    public List<Jugador> getJugadores() {
        checkInvariant();
        return jugadores;
    }
    
    public Jugador getJugadorActual() {
        checkInvariant();

        //PRECONDICIONES
        assert !jugadores.isEmpty();
        assert turnoActual>= 0 && turnoActual < jugadores.size();

        Jugador j = jugadores.get(turnoActual);

        //POSTCONDICIÓN
        assert j != null;

        checkInvariant();
        return j;
    }

    public boolean isTerminado() {
        checkInvariant();
        return terminado;
    }

    public Jugador getGanador() {
        checkInvariant();
        return ganador;
    }

    public Tablero getTablero() {
        checkInvariant();
        return tablero;
    }

    public int getTirada() {
        checkInvariant();
        return tirada;
    }
    
    public boolean puedeMover(Jugador jugador) {
        checkInvariant();

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

        checkInvariant();
        return puede;
    }

    public void mostrarDado(int valor) {
        // método no afectado por el invariante
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

    public enum ResultadoTurno {
        NO_PUEDE_MOVER,
        MOVIMIENTO_REALIZADO,
        REPITE_TURNO,
        JUGADOR_GANA,
        TURNO_AVANZA,
        JUEGO_TERMINADO
    }

    public ResultadoTurno jugarTurno() {
        checkInvariant();

        // PRECONDICIONES
        assert !jugadores.isEmpty();
        assert !terminado;
        assert tablero != null;
        assert dado != null;

        ResultadoTurno resultado;

        if (terminado) {
            resultado = ResultadoTurno.JUEGO_TERMINADO;
            checkInvariant();
            return resultado;
        }

        int turnoAntes = turnoActual;
        Jugador jugadorAntes = getJugadorActual();
        boolean estabaTerminado = terminado;

        Jugador jugador = getJugadorActual();
        jugador.setMovimientoHecho(false);

        tirada = dado.lanzar();
        mostrarDado(tirada);

        if (!puedeMover(jugador)) {
            avanzarTurno();
            resultado = ResultadoTurno.NO_PUEDE_MOVER;
            checkInvariant();
            return resultado;
        }

        jugador.jugar(tirada, tablero);

        if (jugador.haGanado()) {
            terminado = true;
            ganador = jugador;
            resultado = ResultadoTurno.JUGADOR_GANA;
            checkInvariant();
            return resultado;
        }

        if (tirada == 6) {
            resultado = ResultadoTurno.REPITE_TURNO;
            checkInvariant();
            return resultado;
        }

        avanzarTurno();

        assert tirada >= 1 && tirada <= 6;

        if (terminado) {
            assert ganador != null : "El juego ha terminado pero no hay ganador";
            assert jugadores.contains(ganador) : "el ganador no pertenece al juego";
        }

        if (!estabaTerminado && tirada != 6 && puedeMover(jugadorAntes)) {
            assert turnoActual != turnoAntes : "el turno debía avanzar pero no lo hizo";
        }

        resultado = ResultadoTurno.TURNO_AVANZA;
        checkInvariant();
        return resultado;
    }

    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }
}
