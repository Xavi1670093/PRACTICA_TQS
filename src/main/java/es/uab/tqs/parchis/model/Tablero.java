package es.uab.tqs.parchis.model;

import es.uab.tqs.parchis.model.Ficha.ColorFicha;

public class Tablero {
    private Ficha[][] tablero;
    private int[][] numerosTablero;
    private boolean captura;
    Ficha fichaDestino;

    public void inicializa() {
        tablero = new Ficha[19][19];
        numerosTablero = new int[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                tablero[i][j] = new Ficha(Ficha.ColorFicha.NULL, Ficha.TipoFicha.TIPO_EMPTY, null, false);
            }
        }

        // Rojas
        setFicha(1, 1, Ficha.ColorFicha.COLOR_ROJO, new Posicion(-1, false));
        setFicha(1, 5, Ficha.ColorFicha.COLOR_ROJO, new Posicion(-2, false));
        setFicha(5, 1, Ficha.ColorFicha.COLOR_ROJO, new Posicion(-3, false));
        setFicha(5, 5, Ficha.ColorFicha.COLOR_ROJO, new Posicion(-4, false));

        // Verdes
        setFicha(13, 1, Ficha.ColorFicha.COLOR_VERDE, new Posicion(-5, false));
        setFicha(13, 5, Ficha.ColorFicha.COLOR_VERDE, new Posicion(-6, false));
        setFicha(17, 1, Ficha.ColorFicha.COLOR_VERDE, new Posicion(-7, false));
        setFicha(17, 5, Ficha.ColorFicha.COLOR_VERDE, new Posicion(-8, false));

        // Azules
        setFicha(1, 13, Ficha.ColorFicha.COLOR_AZUL, new Posicion(-9, false));
        setFicha(1, 17, Ficha.ColorFicha.COLOR_AZUL, new Posicion(-10, false));
        setFicha(5, 13, Ficha.ColorFicha.COLOR_AZUL, new Posicion(-11, false));
        setFicha(5, 17, Ficha.ColorFicha.COLOR_AZUL, new Posicion(-12, false));

        // Amarillas
        setFicha(13, 13, Ficha.ColorFicha.COLOR_AMARILLO, new Posicion(-13, false));
        setFicha(13, 17, Ficha.ColorFicha.COLOR_AMARILLO, new Posicion(-14, false));
        setFicha(17, 13, Ficha.ColorFicha.COLOR_AMARILLO, new Posicion(-15, false));
        setFicha(17, 17, Ficha.ColorFicha.COLOR_AMARILLO, new Posicion(-16, false));

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
        numerosTablero[9][17] = 69;
        numerosTablero[9][16] = 70;
        numerosTablero[9][15] = 71;
        numerosTablero[9][14] = 72;
        numerosTablero[9][13] = 73;
        numerosTablero[9][12] = 74;
        numerosTablero[9][11] = 75;
        numerosTablero[9][10] = 76;
        numerosTablero[1][9] = 77;
        numerosTablero[2][9] = 78;
        numerosTablero[3][9] = 79;
        numerosTablero[4][9] = 80;
        numerosTablero[5][9] = 81;
        numerosTablero[6][9] = 82;
        numerosTablero[7][9] = 83;
        numerosTablero[8][9] = 84;
        numerosTablero[9][1] = 85;
        numerosTablero[9][2] = 86;
        numerosTablero[9][3] = 87;
        numerosTablero[9][4] = 88;
        numerosTablero[9][5] = 89;
        numerosTablero[9][6] = 90;
        numerosTablero[9][7] = 91;
        numerosTablero[9][8] = 92;
        numerosTablero[17][9] = 93;
        numerosTablero[16][9] = 94;
        numerosTablero[15][9] = 95;
        numerosTablero[14][9] = 96;
        numerosTablero[13][9] = 97;
        numerosTablero[12][9] = 98;
        numerosTablero[11][9] = 99;
        numerosTablero[10][9] = 100;
        numerosTablero[1][1] = -1; // Entrada Roja
        numerosTablero[1][5] = -2; // Entrada Roja
        numerosTablero[5][1] = -3; // Entrada Roja
        numerosTablero[5][5] = -4; // Entrada Roja
        numerosTablero[13][1] = -5; // Entrada Verde
        numerosTablero[13][5] = -6; // Entrada Verde
        numerosTablero[17][1] = -7; // Entrada Verde
        numerosTablero[17][5] = -8; // Entrada Verde
        numerosTablero[1][13] = -9; // Entrada Azul
        numerosTablero[1][17] = -10; // Entrada Azul
        numerosTablero[5][13] = -11; // Entrada Azul
        numerosTablero[5][17] = -12; // Entrada Azul
        numerosTablero[13][13] = -13; // Entrada Amarilla
        numerosTablero[13][17] = -14; // Entrada Amarilla
        numerosTablero[17][13] = -15; // Entrada Amarilla
        numerosTablero[17][17] = -16; // Entrada Amarilla
    }

    public Ficha[][] getTablero() {
        return tablero;
    }

    public boolean isCaptura() {
        return captura;
    }

    public Ficha getFichaDestino() {
        return fichaDestino;
    }

    public Ficha getFicha(int fila, int col) {
        return tablero[fila][col];
    }

    private void setFicha(int fila, int col, Ficha.ColorFicha color, Posicion posicion) {
        tablero[fila][col].setColor(color);
        tablero[fila][col].setTipo(Ficha.TipoFicha.TIPO_OCUPADO);
        tablero[fila][col].setPosicion(posicion);
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
        if (numero < 1 || numero > 100) return null;
        for (int i = 0; i < numerosTablero.length; i++) {
            for (int j = 0; j < numerosTablero[i].length; j++) {
                if (numerosTablero[i][j] == numero) {
                    return new int[]{i, j}; 
                }
            }
        }
        return null; 
    }   


    public int convertirCasillaFinal(Ficha ficha, int casilla) {

        // Si ya está en zona final (69–100), NO transformar nada
        if (casilla >= 69 && casilla <= 100 && ficha.getColor() != ColorFicha.COLOR_AMARILLO) {
            return casilla;
        }

        switch (ficha.getColor()) {

            case COLOR_AZUL -> {
                // Entrada en 17 → tramo 69–76
                if (casilla > 17 && casilla <= 24) { // 17 + 7 pasos
                    return 69 + (casilla - 17) - 1;
                }
                return casilla;
            }

            case COLOR_ROJO -> {
                // Entrada en 34 → tramo 77–84
                if (casilla >= 34 && casilla <= 41) {
                    return 77 + (casilla - 34) - 1;
                }
                return casilla;
            }

            case COLOR_VERDE -> {
                // Entrada en 51 → tramo 85–92
                if (casilla >= 51 && casilla <= 58) {
                    return 85 + (casilla - 51) - 1;
                }
                return casilla;
            }

            case COLOR_AMARILLO -> {
                // Entrada en 68 → tramo 93–100
                if (casilla > 68 && casilla <= 76) { 
                    return 93 + (casilla - 68) - 1;
                }
                return casilla;
            }

            default -> {
                return casilla;
            }
        }
    }

    public boolean movimentInicial(Ficha f, int numDado) {
        if (f.getPosicion().getNumero() < 0) {

            return numDado == 5;
        }

        return false;
    }

    private int obtenerCasillaFinal(Ficha.ColorFicha color) {
        switch (color) {
            case COLOR_AZUL: return 76;
            case COLOR_ROJO: return 84;
            case COLOR_VERDE: return 92;
            case COLOR_AMARILLO: return 100;
            default:return 100;
        }
    }

    public boolean movimientPosible(Ficha ficha, int numDado) {

        captura = false;

        int posActual = ficha.getPosicion().getNumero();
        if (ficha.getPosicion().getNumero() < 0) return movimentInicial(ficha, numDado);
        else
        {
            int casillaDestino = posActual + numDado;
            int casillaFinal = obtenerCasillaFinal(ficha.getColor());
            
            if (casillaDestino > casillaFinal) { return false; }
            
            if (casillaDestino > 68 && casillaDestino <= 76 && ficha.getColor() != ColorFicha.COLOR_AMARILLO) {
                casillaDestino = casillaDestino - 68;
            }
            casillaDestino = convertirCasillaFinal(ficha, casillaDestino);

            int[] destino = obtenerIndice(casillaDestino);
            fichaDestino = tablero[destino[0]][destino[1]];
            Posicion pos = new Posicion(casillaDestino);
            fichaDestino.setPosicion(pos);
    
            
            if (destino == null) return false;
            
            //Comprobem que no hi hagi cap barrera en el camí
            for (int i = posActual + 1; i <= casillaDestino; i++) {
                i = convertirCasillaFinal(ficha, i);
                int[] coord = obtenerIndice(i);
                if (coord != null) {
                    Ficha f = tablero[coord[0]][coord[1]];
                    if (f != null && f.isBarrera())
                        return false;
                }
            }

            if (fichaDestino.getTipo() == Ficha.TipoFicha.TIPO_EMPTY) return true; // Casilla lliure

            if (fichaDestino.getColor() == ficha.getColor()) return true; // Casilla ocupada per ficha del mateix color

            if (fichaDestino.getPosicion().esSeguro()) return false; // Casilla segura

            //CAPTURA
            captura = true;
            return true;
        }
        
    }

    private void enviarFichaACasa(Ficha ficha) {
        int numero = ficha.getPosicion().getNumero();
        int[] casa = null;

        // Busca una casa libre del color correspondiente
        outer:
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (numerosTablero[i][j] < 0 && tablero[i][j].getColor() == ficha.getColor()) {
                    casa = new int[]{i, j};
                    break outer;
                }
            }
        }

        if (casa != null) {
            ficha.setPosicion(new Posicion(numerosTablero[casa[0]][casa[1]]));
            tablero[casa[0]][casa[1]] = ficha;
        }
    }

    private int obtenerCasillaSalida(ColorFicha color) {
        return switch (color) {
            case COLOR_ROJO -> 39;
            case COLOR_VERDE -> 56;
            case COLOR_AZUL -> 22;
            case COLOR_AMARILLO -> 5;
            default -> 1;
        };
    }

    public void mouFicha(Ficha ficha, int numDado) {

        // 1) Comprobación general
        if (!movimientPosible(ficha, numDado)) {
            return;
        }

        int posActual = ficha.getPosicion().getNumero();
        int filaActual = -1, colActual = -1;

        // Buscar la ficha en el tablero
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (tablero[i][j] == ficha) {
                    filaActual = i;
                    colActual = j;
                    break;
                }
            }
        }

        // --- CASO 1: SALIDA DESDE CASA ---
        if (posActual < 0) {
            if (numDado == 5) {
                int salida = obtenerCasillaSalida(ficha.getColor());
                int[] destino = obtenerIndice(salida);

                // CAPTURA si hay enemigo
                if (tablero[destino[0]][destino[1]].getTipo() == Ficha.TipoFicha.TIPO_OCUPADO &&
                    tablero[destino[0]][destino[1]].getColor() != ficha.getColor() &&
                    !tablero[destino[0]][destino[1]].getPosicion().esSeguro()) {

                    // Enemigo vuelve a su casa
                    enviarFichaACasa(tablero[destino[0]][destino[1]]);
                }

                // mover ficha
                tablero[filaActual][colActual] = new Ficha(ColorFicha.NULL, Ficha.TipoFicha.TIPO_EMPTY, null, false);
                ficha.setPosicion(new Posicion(salida));
                tablero[destino[0]][destino[1]] = ficha;
            }
            return;
        }

        // --- CASO 2: MOVIMIENTO NORMAL ---

        int casillaDestino = posActual + numDado;

        // Entradas finales excepto amarillo
        if (casillaDestino > 68 && casillaDestino <= 76 && ficha.getColor() != ColorFicha.COLOR_AMARILLO) {
            casillaDestino -= 68;
        }

        // Convertir tramo final según color
        casillaDestino = convertirCasillaFinal(ficha, casillaDestino);

        int[] destino = obtenerIndice(casillaDestino);

        // CAPTURA
        if (tablero[destino[0]][destino[1]].getTipo() == Ficha.TipoFicha.TIPO_OCUPADO &&
            tablero[destino[0]][destino[1]].getColor() != ficha.getColor() &&
            !tablero[destino[0]][destino[1]].getPosicion().esSeguro()) {

            enviarFichaACasa(tablero[destino[0]][destino[1]]);
            captura = true;
        }

        // Mover la ficha
        tablero[filaActual][colActual] = new Ficha(ColorFicha.NULL, Ficha.TipoFicha.TIPO_EMPTY, null, false);

        ficha.setPosicion(new Posicion(casillaDestino));
        tablero[destino[0]][destino[1]] = ficha;
    }





































    public void mostrar() {

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                Ficha f = tablero[i][j];
                if (f.getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                    if (numerosTablero[i][j] != 0 && numerosTablero[i][j] <= 68) {
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

