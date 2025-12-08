package es.uab.tqs.parchis.model;

import java.util.List;

public class Juego {

    private final List<Jugador> jugadores; // Lista de jugadores activos en la partida
    private final Tablero tablero;          // Tablero donde se juega
    private final Dado dado;                // Dado para determinar movimientos

    private int turnoActual = 0;            // Índice del jugador que tiene el turno
    private boolean terminado = false;      // Indica si el juego ha finalizado
    private Jugador ganador;                // Referencia al jugador que ha ganado
    private int tirada;                     // Valor de la última tirada de dado

    /** 
     * INVARIANTES DEL JUEGO:
     * - jugadores no puede ser null ni estar vacío.
     * - tablero y dado nunca pueden ser null.
     * - turnoActual siempre debe estar dentro del rango [0, jugadores.size()-1].
     * - si terminado = true, debe existir un ganador válido y perteneciente al juego.
     * 
     * Estas condiciones se usan para asegurar consistencia interna, especialmente
     * durante testing o depuración. Si falla un assert, indica un error de lógica.
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

    // Constructor principal: inicializa el juego con jugadores, tablero y dado
    public Juego(List<Jugador> jugadores, Tablero tablero, Dado dado) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.dado = dado;

        checkInvariant(); // Comprobamos que los datos iniciales son válidos
    }

    public List<Jugador> getJugadores() {
        checkInvariant();
        return jugadores;
    }
    
    public Jugador getJugadorActual() {
        checkInvariant();

        // PRECONDICIONES: aseguramos que hay jugadores y el índice es válido
        assert !jugadores.isEmpty();
        assert turnoActual >= 0 && turnoActual < jugadores.size();

        Jugador j = jugadores.get(turnoActual);

        // POSTCONDICIÓN: nunca debe devolver null
        assert j != null;

        checkInvariant();
        return j;
    }

    public boolean isTerminado() {
        checkInvariant(); // garantizamos consistencia
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
    
    /**
     * Comprueba si un jugador puede mover alguna de sus fichas con cualquier valor de dado.
     * Esto se hace recorriendo todas las fichas del jugador y todos los posibles valores (1-6).
     * Es útil para testing de "statement coverage" y "decision coverage" porque activa
     * distintas ramas de movimientPosible.
     */
    public boolean puedeMover(Jugador jugador) {
        checkInvariant();

        // PRECONDICIONES
        assert jugadores != null : "No hay jugadores";
        assert jugadores.contains(jugador);

        boolean puede = false;

        // Recorrer todas las fichas del jugador y valores posibles de dado
        for (Ficha f : jugador.getFichas()) {
            for (int dadoValor = 1; dadoValor <= 6; dadoValor++) {
                if (tablero.movimientPosible(f, dadoValor)) puede = true;
            }
        }

        // POSTCONDICIÓN simple: el resultado es boolean
        assert (puede == true || puede == false);

        checkInvariant();
        return puede;
    }

    /**
     * Método auxiliar para mostrar gráficamente el dado en consola.
     * Aunque no afecta la lógica del juego, es útil para visualizar resultados
     * durante pruebas manuales.
     */
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

    // Enum para resultados de un turno, útil para testing y cobertura de decisiones
    public enum ResultadoTurno {
        NO_PUEDE_MOVER,
        MOVIMIENTO_REALIZADO,
        REPITE_TURNO,
        JUGADOR_GANA,
        TURNO_AVANZA,
        JUEGO_TERMINADO
    }

    /**
     * Ejecuta un turno completo para el jugador actual:
     * - Lanza el dado
     * - Comprueba si puede mover
     * - Mueve fichas y aplica reglas de captura, barrera o llegada a meta
     * - Actualiza turno y estado del juego
     *
     * Los asserts y checkInvariant aseguran que durante testing se cumplen todas las condiciones
     * esperadas, permitiendo medir statement coverage y decision coverage.
     */
    public ResultadoTurno jugarTurno() {
        checkInvariant();

        // PRECONDICIONES
        assert !jugadores.isEmpty();
        assert !terminado;
        assert tablero != null;
        assert dado != null;

        ResultadoTurno resultado;

        if (terminado) { // caso juego ya finalizado
            resultado = ResultadoTurno.JUEGO_TERMINADO;
            checkInvariant();
            return resultado;
        }

        int turnoAntes = turnoActual;
        Jugador jugadorAntes = getJugadorActual();
        boolean estabaTerminado = terminado;

        Jugador jugador = getJugadorActual();
        jugador.setMovimientoHecho(false); // reset movimiento antes del turno

        tirada = dado.lanzar(); // tirada del dado
        mostrarDado(tirada);    // visualización para testing/manual

        if (!puedeMover(jugador)) { // jugador no puede mover ninguna ficha
            avanzarTurno();
            resultado = ResultadoTurno.NO_PUEDE_MOVER;
            checkInvariant();
            return resultado;
        }

        jugador.jugar(tirada, tablero); // se realiza movimiento según reglas del jugador

        if (jugador.haGanado()) { // comprobación de victoria
            terminado = true;
            ganador = jugador;
            resultado = ResultadoTurno.JUGADOR_GANA;
            checkInvariant();
            return resultado;
        }

        if (tirada == 6) { // regla: repetir turno al sacar 6
            resultado = ResultadoTurno.REPITE_TURNO;
            checkInvariant();
            return resultado;
        }

        avanzarTurno(); // pasa turno al siguiente jugador

        assert tirada >= 1 && tirada <= 6; // tirada siempre válida

        if (terminado) { // postcondición de juego terminado
            assert ganador != null : "El juego ha terminado pero no hay ganador";
            assert jugadores.contains(ganador) : "el ganador no pertenece al juego";
        }

        // postcondición: si no estaba terminado y no salió 6, el turno avanza
        if (!estabaTerminado && tirada != 6 && puedeMover(jugadorAntes)) {
            assert turnoActual != turnoAntes : "el turno debía avanzar pero no lo hizo";
        }

        resultado = ResultadoTurno.TURNO_AVANZA;
        checkInvariant();
        return resultado;
    }

    // Avanza el turno al siguiente jugador, con wrap-around circular
    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }
}
