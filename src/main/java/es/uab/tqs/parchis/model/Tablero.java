package es.uab.tqs.parchis.model;

public class Tablero {
    private Ficha[][] tablero;

    public void inicializa() {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                tablero[i][j] = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_EMPTY);
            }
        }

        // Rojas
        setFicha(1, 1, Ficha.ColorFicha.COLOR_ROJO);
        setFicha(1, 5, Ficha.ColorFicha.COLOR_ROJO);
        setFicha(5, 1, Ficha.ColorFicha.COLOR_ROJO);
        setFicha(5, 5, Ficha.ColorFicha.COLOR_ROJO);

        // Verdes
        setFicha(13, 1, Ficha.ColorFicha.COLOR_VERDE);
        setFicha(13, 5, Ficha.ColorFicha.COLOR_VERDE);
        setFicha(17, 1, Ficha.ColorFicha.COLOR_VERDE);
        setFicha(17, 5, Ficha.ColorFicha.COLOR_VERDE);

        // Azules
        setFicha(1, 13, Ficha.ColorFicha.COLOR_AZUL);
        setFicha(1, 17, Ficha.ColorFicha.COLOR_AZUL);
        setFicha(5, 13, Ficha.ColorFicha.COLOR_AZUL);
        setFicha(5, 17, Ficha.ColorFicha.COLOR_AZUL);

        // Amarillas
        setFicha(13, 13, Ficha.ColorFicha.COLOR_AMARILLO);
        setFicha(13, 17, Ficha.ColorFicha.COLOR_AMARILLO);
        setFicha(17, 13, Ficha.ColorFicha.COLOR_AMARILLO);
        setFicha(17, 17, Ficha.ColorFicha.COLOR_AMARILLO);
    }

    private void setFicha(int fila, int col, Ficha.ColorFicha color) {
        tablero[fila][col].setColor(color);
        tablero[fila][col].setTipo(Ficha.TipoFicha.TIPO_OCUPADO);
    }

    public Ficha[][] getTablero() {
        return tablero;
    }




}