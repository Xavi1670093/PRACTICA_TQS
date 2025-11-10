package es.uab.tqs.parchis.model;

public class ficha {
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
    private int pos;

    public ficha(ColorFicha color, TipoFicha tipo) {
        this.color = color;
        this.tipo = tipo;
    }

    public void enterBoard(int pos_inicial){
        this.pos = pos_inicial;
    }

    // getters y setters
}
