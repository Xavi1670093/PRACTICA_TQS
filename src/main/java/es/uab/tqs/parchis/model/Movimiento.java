package es.uab.tqs.parchis.model;

public class Movimiento {

    private Posicion inicial;
    private Posicion destino;
    private boolean captura;

    public Movimiento(Posicion inicial, Posicion destino, boolean captura) {
        this.inicial = inicial;
        this.destino = destino;
        this.captura = captura;
    }

    public Posicion getInicial() {
        return inicial;
    }

    public Posicion getDestino() {
        return destino;
    }

    public boolean isCaptura() {
        return captura;
    }

    public void setInicial(Posicion inicial) {
        this.inicial = inicial;
    }
    public void setDestino(Posicion destino) {
        this.destino = destino;
    }
    public void setCaptura(boolean captura) {
        this.captura = captura;
    }

}