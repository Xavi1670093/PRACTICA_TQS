package es.uab.tqs.parchis.model;

public interface IJugador {
    /**
     * Intenta jugar una tirada.
     *
     * @param numDado el número sacado en el dado
     * @param tablero el tablero
     * @param color   el color del jugador
     * @return true si pudo mover (o se realizó alguna acción válida), false si no se pudo mover
     */
    boolean jugar(int numDado, Tablero tablero, Ficha.ColorFicha color);
}
