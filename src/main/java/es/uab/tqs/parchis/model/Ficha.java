package es.uab.tqs.parchis.model;

public class Ficha {

    public Ficha() {
    }
    public enum ColorFicha {
        COLOR_AMARILLO,
        COLOR_AZUL,
        COLOR_ROJO,
        COLOR_VERDE
    }

    public enum TipoFicha {
        TIPO_OCUPADO,
        TIPO_EMPTY
    }

    private ColorFicha color;
    private TipoFicha tipo;
    private Posicion pos;

    public Ficha(ColorFicha color, TipoFicha tipo, Posicion pos) {
        this.color = color;
        this.tipo = tipo;
        this.pos = pos;
    }

    
    public ColorFicha getColor() {
        return color;
    }
    public TipoFicha getTipo() {
        return tipo;
    }
    public Posicion getPosicion() {
        return pos;
    }

    public void setColor(ColorFicha color) {
        this.color = color;
    }
    public void setTipo(TipoFicha tipo) {
        this.tipo = tipo;
    }

}