package es.uab.tqs.parchis.model;

/**
 * Representa la posición de una ficha en el tablero.
 * Contiene el número de casilla y un indicador de seguridad.
 * 
 * Esta clase es simple, pero encapsula reglas importantes:
 * - Determina automáticamente si una casilla es segura según su número.
 * - Permite modificar número y estado seguro de forma controlada.
 */
public class Posicion {
    private int nCasilla;   // Número de casilla en el tablero (puede ser negativo si está en casa)
    private boolean seguro; // Indica si la casilla es segura (no se puede capturar allí)

    // --- CONSTRUCTORES ---

    /** Constructor por defecto: casilla 0 y no segura */
    public Posicion() {
        this.nCasilla = 0;
        this.seguro = false;
    }
    
    /** Constructor con número de casilla: calcula automáticamente si es segura */
    public Posicion(int nCasilla) {
        this.nCasilla = nCasilla;
        this.seguro = esCasillaSegura(nCasilla);
    }

    /** Constructor completo: permite definir número y seguridad manualmente */
    public Posicion(int nCasilla, boolean seguro) {
        this.nCasilla = nCasilla;
        this.seguro = seguro;
    }

    /**
     * Determina si una casilla es segura según reglas del tablero.
     * Esta función está basada en los números de casilla definidos en Parchís.
     * 
     * IMPORTANTE para testing: 
     * - Permite testear comportamiento de captura y movimiento seguro.
     * - Cubre múltiples ramas según el número de casilla.
     */
    private boolean esCasillaSegura(int n) {
        return n == 5  || n == 12 || n == 17 ||
               n == 22 || n == 29 || n == 34 ||
               n == 39 || n == 46 || n == 51 ||
               n == 56 || n == 63 || n == 68;
    }

    // --- GETTERS ---

    /** Devuelve el número de casilla de la posición */
    public int getNumero() {
        return nCasilla;
    }

    /** Devuelve si la posición es segura */
    public boolean esSeguro() {
        return seguro;
    }

    // --- SETTERS ---

    /** Modifica el número de casilla */
    public void setNumero(int nCasilla) {
        this.nCasilla = nCasilla;
    }

    /** Modifica el estado de seguro */
    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }
}
