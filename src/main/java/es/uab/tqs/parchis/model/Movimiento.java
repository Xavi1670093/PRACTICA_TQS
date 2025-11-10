package es.uab.tqs.parchis.model;

public class Movimiento {

    private Posicion inicial;
    private Posicion destino;
    private boolean captura;
    private Posicion comida;

    public Movimiento(Posicion inicial, Posicion destino, boolean captura) {
        this.inicial = inicial;
        this.destino = destino;
        this.captura = captura;
        this.comida = null;
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

    public Posicion getComida() {
        return comida;
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
    public void setComida(Posicion comida) {
        this.comida = comida;
    }

}