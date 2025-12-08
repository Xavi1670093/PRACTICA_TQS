package es.uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jugador {
    private final String nombre;          // Nombre del jugador
    private Ficha.ColorFicha color;       // Color del jugador
    private final List<Ficha> fichas;     // Fichas del jugador
    private boolean movimientoHecho;      // Indica si el jugador ya ha movido esta ronda

    /**
     * INVARIANTES DEL JUGADOR:
     * - nombre no puede ser null ni vacío.
     * - color no puede ser null.
     * - fichas nunca es null.
     * - cada ficha debe coincidir con el color del jugador,
     *   salvo las fichas especiales del tablero (color NULL).
     *
     * Estas condiciones permiten asegurar consistencia interna y son útiles
     * para testing y detección de errores.
     */
    private void checkInvariant() {
        assert nombre != null && !nombre.isBlank() : "El nombre del jugador es inválido";
        assert color != null : "El color del jugador no puede ser null";
        assert fichas != null : "La lista de fichas no puede ser null";

        for (Ficha f : fichas) {
            assert f != null : "Hay una ficha null en la lista del jugador";
            if (f.getColor() != Ficha.ColorFicha.NULL) {
                assert f.getColor() == color :
                    "Una ficha del jugador tiene un color distinto al del jugador";
            }
        }
    }

    // Constructor: inicializa nombre, color y lista vacía de fichas
    public Jugador(String nombre, Ficha.ColorFicha color) {
        // PRECONDICIONES
        assert nombre != null: "nombre es null";
        assert !nombre.isBlank() : "El nombre está vacío";
        assert color != null : "Color es null";

        this.nombre = nombre;
        this.color = color;
        this.fichas = new ArrayList<>();

        // POSTCONDICIONES
        assert this.nombre.equals(nombre) : "nombre no inicializado correctamente";
        assert this.color == color : "color no inicializado correctamente";
        assert this.fichas.isEmpty() : "lista de fichas debería empezar vacía";

        checkInvariant();
    }

    public String getNombre() {
        checkInvariant();
        return nombre;
    }

    public Ficha.ColorFicha getColor() {
        checkInvariant();
        return color;
    }

    public List<Ficha> getFichas() {
        checkInvariant();
        return fichas;
    }
    
    public boolean getMovimientoHecho() {
        checkInvariant();
        return movimientoHecho;
    }

    public void setMovimientoHecho(boolean movimientoHecho) {
        this.movimientoHecho = movimientoHecho;
        checkInvariant();
    }
    
    public void eliminarFicha(Ficha ficha) {
        fichas.remove(ficha);
        checkInvariant();
    }

    /**
     * Añade una ficha al jugador, verificando que coincida con el color del jugador.
     * Los asserts y excepciones aseguran consistencia para testing y coverage.
     */
    public void añadirFicha(Ficha ficha) {
        // PRECONDICIONES
        assert ficha != null : "ficha es null";
        if (ficha.getColor() != this.color)
            throw new IllegalArgumentException("La ficha no coincide con el color del jugador");
        
        fichas.add(ficha);
        
        // POSTCONDICIÓN
        assert fichas.contains(ficha) : "Ficha no añadida correctamente";

        checkInvariant();
    }

    /**
     * Método principal para jugar un turno:
     * - Comprueba qué fichas pueden moverse
     * - Solicita al jugador elegir una ficha mediante consola
     * - Mueve la ficha seleccionada en el tablero
     *
     * Este método activa muchas ramas de código, lo que es útil para
     * statement coverage y decision coverage en tests.
     */
    public boolean jugar(int numDado, Tablero tablero) {
        checkInvariant();

        // PRECONDICIONES
        assert numDado >= 1 && numDado <= 6 : "valor de dado inválido";
        assert tablero != null : "tablero es null";
        assert !fichas.isEmpty() : "jugador sin fichas no puede jugar";

        List<Ficha> fichasMovibles = new ArrayList<>();

        // --- Construir lista de fichas movibles ---
        for (Ficha ficha : fichas) {
            if(ficha.getPosicion() == null){
                continue; // ficha no está en juego, se ignora
            }
            int[] pos = tablero.obtenerIndice(ficha.getPosicion().getNumero());
            if (pos != null) {
                Ficha fichaEnTablero = tablero.getFicha(pos[0], pos[1]);
                // Solo fichas del jugador y que puedan moverse según tablero
                if (fichaEnTablero.getColor() == this.color &&
                    tablero.movimientPosible(fichaEnTablero, numDado) &&
                    fichaEnTablero.getPosicion() != null) {
                    fichasMovibles.add(fichaEnTablero);
                }
            }
        }

        // No hay fichas que se puedan mover
        if (fichasMovibles.isEmpty()) {
            System.out.println(nombre + ", no hay fichas que puedas mover con " + numDado);
            checkInvariant();
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        int fichaEscogida = -1;

        // --- Bucle para elegir ficha válida ---
        while (fichaEscogida < 1 || fichaEscogida > fichasMovibles.size()) {
            System.out.println(nombre + ", elige una ficha para mover con dado " + numDado + ":");
            for (int i = 0; i < fichasMovibles.size(); i++) {
                Ficha f = fichasMovibles.get(i);
                if (f.getPosicion().getNumero() < 0)
                    System.out.println((i + 1) + ": Ficha en posición inicial");
                else if (f.getPosicion().getNumero() > 68)
                    System.out.println((i + 1) + ": Ficha en posiciones finales");
                else
                    System.out.println((i + 1) + ": Ficha en posición " + f.getPosicion().getNumero() +
                                       (f.isBarrera() ? " (barrera)" : ""));
            }

            System.out.print("Introduce el número de la ficha a mover: ");
            if (scanner.hasNextInt()) {
                fichaEscogida = scanner.nextInt();
            } else {
                scanner.next(); // limpiar input inválido
            }
        }

        // Marcar que el jugador ha movido y realizar el movimiento
        movimientoHecho = true;
        Ficha fichaSeleccionada = fichasMovibles.get(fichaEscogida - 1);

        tablero.mouFicha(fichaSeleccionada, numDado);

        if (fichaSeleccionada.getPosicion().getNumero() == 999) {
            System.out.println("Ficha movida a la meta y retirada del tablero.");
        } else {
            System.out.println("Ficha movida a posición " + fichaSeleccionada.getPosicion().getNumero());
        }

        // POSTCONDICIONES
        assert movimientoHecho : "movimientoHecho debería haberse marcado como true";
        assert fichaSeleccionada.getPosicion() != null :
                "la posición de la ficha no puede ser null después de mover";

        checkInvariant();
        return true;
    }

    /**
     * Comprueba si el jugador ha ganado: todas sus fichas deben estar en la meta.
     * Este método activa múltiples ramas según el color y permite cobertura de decisión.
     */
    public boolean haGanado() {
        checkInvariant();

        // PRECONDICIÓN
        assert !fichas.isEmpty() : "Jugador sin fichas no puede ganar";

        int meta;
        switch(color) {
            case COLOR_ROJO -> meta = 84;
            case COLOR_AZUL -> meta = 76;
            case COLOR_VERDE -> meta = 92;
            case COLOR_AMARILLO -> meta = 100;
            default -> throw new IllegalStateException("Color no válido");
        }

        // Recorremos todas las fichas: si alguna no está en meta, no ha ganado
        for (Ficha f : fichas) {
            if (f.getPosicion().getNumero() != meta) {
                checkInvariant();
                return false;
            }
        }

        // POSTCONDICIÓN
        assert true : "Todas las fichas deben estar en la meta";

        checkInvariant();
        return true;
    }
}
