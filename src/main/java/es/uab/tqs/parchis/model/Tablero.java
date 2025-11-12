package es.uab.tqs.parchis.model;

public class Tablero {
    private Ficha[][] tablero;
    private int[][] numerosTablero;

    public void inicializa() {
        tablero = new Ficha[19][19];
        numerosTablero = new int[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                tablero[i][j] = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_EMPTY, null);
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

        numerosTablero[18][10] = 1;
        numerosTablero[17][10] = 2;
        numerosTablero[16][10] = 3;
        numerosTablero[15][10] = 4;
        numerosTablero[14][10] = 5;
        numerosTablero[13][10] = 6;
        numerosTablero[12][10] = 7;
        numerosTablero[11][10] = 8;
        numerosTablero[10][11] = 9;
        numerosTablero[10][12] = 10;
        numerosTablero[10][13] = 11;
        numerosTablero[10][14] = 12;
        numerosTablero[10][15] = 13;
        numerosTablero[10][16] = 14;
        numerosTablero[10][17] = 15;
        numerosTablero[10][18] = 16;
        numerosTablero[9][18] = 17;
        numerosTablero[8][18] = 18;
        numerosTablero[8][17] = 19;
        numerosTablero[8][16] = 20;
        numerosTablero[8][15] = 21;
        numerosTablero[8][14] = 22;
        numerosTablero[8][13] = 23;
        numerosTablero[8][12] = 24;
        numerosTablero[8][11] = 25;
        numerosTablero[7][10] = 26;
        numerosTablero[6][10] = 27;
        numerosTablero[5][10] = 28;
        numerosTablero[4][10] = 29;
        numerosTablero[3][10] = 30;
        numerosTablero[2][10] = 31;
        numerosTablero[1][10] = 32;
        numerosTablero[0][10] = 33;
        numerosTablero[0][9] = 34;
        numerosTablero[0][8] = 35;
        numerosTablero[1][8] = 36;
        numerosTablero[2][8] = 37;
        numerosTablero[3][8] = 38;
        numerosTablero[4][8] = 39;
        numerosTablero[5][8] = 40;
        numerosTablero[6][8] = 41;
        numerosTablero[7][8] = 42;
        numerosTablero[8][7] = 43;
        numerosTablero[8][6] = 44;
        numerosTablero[8][5] = 45;
        numerosTablero[8][4] = 46;
        numerosTablero[8][3] = 47;
        numerosTablero[8][2] = 48;
        numerosTablero[8][1] = 49;
        numerosTablero[8][0] = 50;
        numerosTablero[9][0] = 51;
        numerosTablero[10][0] = 52;
        numerosTablero[10][1] = 53;
        numerosTablero[10][2] = 54;
        numerosTablero[10][3] = 55;
        numerosTablero[10][4] = 56;
        numerosTablero[10][5] = 57;
        numerosTablero[10][6] = 58;
        numerosTablero[10][7] = 59;
        numerosTablero[11][8] = 60;
        numerosTablero[12][8] = 61;
        numerosTablero[13][8] = 62;
        numerosTablero[14][8] = 63;
        numerosTablero[15][8] = 64;
        numerosTablero[16][8] = 65;
        numerosTablero[17][8] = 66;
        numerosTablero[18][8] = 67;
        numerosTablero[18][9] = 68;
    }

    private void setFicha(int fila, int col, Ficha.ColorFicha color) {
        tablero[fila][col].setColor(color);
        tablero[fila][col].setTipo(Ficha.TipoFicha.TIPO_OCUPADO);
    }

    public Ficha[][] getTablero() {
        return tablero;
    }

    public boolean capturaPosible(Ficha ficha, int numDado) {
        int pos = ficha.getPosicion().getNumero();
        return false; 
    }

    public void setFicha(Ficha ficha, int[] posicion) {
    if (posicion == null || posicion.length != 2) {
        throw new IllegalArgumentException("Posición inválida");
    }

    int fila = posicion[0];
    int col = posicion[1];

    tablero[fila][col] = ficha;
}


    public int[] obtenerIndice(int numero) {
    if (numero < 1 || numero > 68) return null;
    for (int i = 0; i < numerosTablero.length; i++) {
        for (int j = 0; j < numerosTablero[i].length; j++) {
            if (numerosTablero[i][j] == numero) {
                return new int[]{i, j}; 
            }
        }
    }
    return null; 
}
































    public void mostrar() {

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                Ficha f = tablero[i][j];
                if (f.getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                    if (numerosTablero[i][j] != 0) {
                        System.out.printf("%2d ", numerosTablero[i][j]); // número con 2 dígitos de ancho
                    } else {
                        System.out.print("   "); // alineamos con espacio extra
                    }
                } else {
                    char letraColor = ' '; 
                    switch (f.getColor()) {
                        case COLOR_ROJO -> letraColor = 'R';
                        case COLOR_VERDE -> letraColor = 'V';
                        case COLOR_AZUL -> letraColor = 'A';
                        case COLOR_AMARILLO -> letraColor = 'Y';
                    }
                    System.out.print(" " + letraColor + " "); // ocupado: también 3 caracteres
                }
            }
        System.out.println();
        }
    }

    
}

