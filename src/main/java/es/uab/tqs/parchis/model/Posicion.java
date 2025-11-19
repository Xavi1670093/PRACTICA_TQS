package es.uab.tqs.parchis.model;

public class Posicion {
    private int nCasilla;
    private boolean seguro;

    //CONSTRUCTOR
    public Posicion() {
        this.nCasilla = 0;
        this.seguro = false;
    }
    
    public Posicion(int nCasilla) {
        this.nCasilla = nCasilla;
        this.seguro = esCasillaSegura(nCasilla);
    }

    public Posicion(int nCasilla, boolean seguro) {
        this.nCasilla = nCasilla;
        this.seguro = seguro;
    }

    private boolean esCasillaSegura(int n) {
        return n == 5  || n == 12 || n == 17 ||
            n == 22 || n == 29 || n == 34 ||
            n == 39 || n == 46 || n == 51 ||
            n == 56 || n == 63 || n == 68;
    }

    //GETTERS
    public int getNumero() {
        return nCasilla;
    }
    public boolean esSeguro() {
        return seguro;
    }

    //SETTERS
    public void setNumero(int nCasilla) {
        this.nCasilla = nCasilla;
    }
    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

}
