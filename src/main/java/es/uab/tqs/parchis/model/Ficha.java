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
    private Movimiento mov;

    public Ficha(ColorFicha color, TipoFicha tipo) {
        this.color = color;
        this.tipo = tipo;
        this.pos = new Posicion();
        this.mov = new Movimiento();
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
    public Movimiento getMovimiento() {
        return mov;
    }

    public void setColor(ColorFicha color) {
        this.color = color;
    }
    public void setTipo(TipoFicha tipo) {
        this.tipo = tipo;
    }

}