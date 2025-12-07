package es.uab.tqs.parchis.model;

import java.util.Objects;

public class Ficha {

    public Ficha() {
    }

    public enum ColorFicha {
        COLOR_AMARILLO,
        COLOR_AZUL,
        COLOR_ROJO,
        COLOR_VERDE,
        NULL
    }

    public enum TipoFicha {
        TIPO_OCUPADO,
        TIPO_EMPTY
    }

    private ColorFicha color;
    private TipoFicha tipo;
    private Posicion pos;
    private boolean barrera;

    public Ficha(ColorFicha color, TipoFicha tipo, Posicion pos, boolean barrera) {
        Objects.requireNonNull(color, "El color de la ficha no puede ser null");
        Objects.requireNonNull(tipo, "El tipo de la ficha no puede ser null");

        this.color = color;
        this.tipo = tipo;
        this.pos = pos;
        this.barrera = barrera;

        assert this.color != null : "La ficha debe tener un color válido";
        assert this.tipo != null : "La ficha debe tener un tipo válido";
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

    public boolean isBarrera() {
        return barrera;
    }

    public void setColor(ColorFicha color) {
        Objects.requireNonNull(color, "El color no puede ser null");
        this.color = color;
    }

    public void setTipo(TipoFicha tipo) {
        Objects.requireNonNull(tipo, "El tipo no puede ser null");
        this.tipo = tipo;
    }

    public void setBarrera(boolean barrera) {
        this.barrera = barrera;
    }

    public void setPosicion(Posicion pos) {
        this.pos = pos;
    }

    public int getNumCasilla() {
        int num = (pos != null) ? pos.getNumero() : 0;
        assert num >= 0 : "El número de casilla debe ser no negativo";
        return num;
    }
}
