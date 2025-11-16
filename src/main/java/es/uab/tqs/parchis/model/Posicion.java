package es.uab.tqs.parchis.model;

public class Posicion {
    private int nCasilla;
    private boolean seguro;

    //CONSTRUCTOR
    public Posicion() {
        this.nCasilla = 0;
        this.seguro = false;
    }
    
    public Posicion(int nCasilla, boolean seguro) {
        this.nCasilla = nCasilla;
        this.seguro = seguro;
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
