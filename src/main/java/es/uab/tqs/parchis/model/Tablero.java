package es.uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;

import es.uab.tqs.parchis.model.Ficha.ColorFicha;

public class Tablero {
    private Ficha[][] tablero;
    private int[][] numerosTablero;
    private boolean captura;
    Ficha fichaDestino;

    public void inicializa() {
        //PRECONDICION
        assert tablero == null : "El tablero no debe estar inicializado de antes";
        assert numerosTablero == null : "Los números del tablero no deben estar inicializados";

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
        //entrada azul
        numerosTablero[9][17] = 69+20;
        numerosTablero[9][16] = 70+20;
        numerosTablero[9][15] = 71+20;
        numerosTablero[9][14] = 72+20;
        numerosTablero[9][13] = 73+20;
        numerosTablero[9][12] = 74+20;
        numerosTablero[9][11] = 75+20;
        numerosTablero[9][10] = 76+20;
        //entrada roja
        numerosTablero[1][9] = 77+20;
        numerosTablero[2][9] = 78+20;
        numerosTablero[3][9] = 79+20;
        numerosTablero[4][9] = 80+20;
        numerosTablero[5][9] = 81+20;
        numerosTablero[6][9] = 82+20;
        numerosTablero[7][9] = 83+20;
        numerosTablero[8][9] = 84+20;
        //entrada verde
        numerosTablero[9][1] = 85+20;
        numerosTablero[9][2] = 86+20;
        numerosTablero[9][3] = 87+20;
        numerosTablero[9][4] = 88+20;
        numerosTablero[9][5] = 89+20;
        numerosTablero[9][6] = 90+20;
        numerosTablero[9][7] = 91+20;
        numerosTablero[9][8] = 92+20;
        //entrada amarilla
        numerosTablero[17][9] = 93+20;
        numerosTablero[16][9] = 94+20;
        numerosTablero[15][9] = 95+20;
        numerosTablero[14][9] = 96+20;
        numerosTablero[13][9] = 97+20;
        numerosTablero[12][9] = 98+20;
        numerosTablero[11][9] = 99+20;
        numerosTablero[10][9] = 100+20;
        
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

        //POSTCONDICIONES
        assert tablero.length == 19 : "El tablero no tiene tamaño 19x19";
        assert numerosTablero.length == 19 : "Los numerosTablero no tienen tamaño 19x19";
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

        if (numero < -16 || numero > 120) return null;
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
        //PRECONDICIONES
        assert ficha != null : "ficha es null";
        assert ficha.getPosicion() != null : "posición de ficha es null";

        ColorFicha color = ficha.getColor();
        int posicionActual = ficha.getPosicion().getNumero();

        int resultado = casilla; // valor por defecto para postcondiciones

        // --- BLOQUE 1: evitar conversiones en posiciones iniciales ---
        switch (color) {
            case COLOR_ROJO -> {
                if (posicionActual >= 39 && posicionActual <= 41) {
                    resultado = casilla;
                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
            }
            case COLOR_AZUL -> {
                if (posicionActual >= 22 && posicionActual <= 24) {
                    resultado = casilla;
                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
            }
            case COLOR_VERDE -> {
                if (posicionActual >= 56 && posicionActual <= 58) {
                    resultado = casilla;
                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
            }
            case COLOR_AMARILLO -> {
                if (posicionActual >= 5 && posicionActual <= 8) {
                    resultado = casilla;
                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
            }
        }

        // --- BLOQUE 2: si ya está en tramo final, NO convertir ---
        if (casilla >= 69 + 20 && casilla <= 100 + 20) {
            resultado = casilla;

            // POSTCONDICIONES
            assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
            assert !(resultado < 0 && resultado >= 69 + 20) : "rango final inconsistente";

            return resultado;
        }

        // --- BLOQUE 3: conversiones normales ---
        switch (color) {

            case COLOR_AZUL -> {
                if (casilla > 17 && casilla <= 24) {
                    resultado = 69 + 20 + (casilla - 17) - 1;

                    // POSTCONDICIONES
                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
                resultado = casilla;

                assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                return resultado;
            }

            case COLOR_ROJO -> {
                if (casilla > 34 && casilla <= 41) {
                    resultado = 77 + 20 + (casilla - 34) - 1;

                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
                resultado = casilla;

                assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                return resultado;
            }

            case COLOR_VERDE -> {
                if (casilla > 51 && casilla <= 58) {
                    resultado = 85 + 20 + (casilla - 51) - 1;

                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
                resultado = casilla;

                assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                return resultado;
            }

            case COLOR_AMARILLO -> {
                if (casilla > 68 && casilla <= 76) {
                    resultado = 93 + 20 + (casilla - 68) - 1;

                    assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                    return resultado;
                }
                resultado = casilla;

                assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                return resultado;
            }

            default -> {
                resultado = casilla;

                assert resultado >= -16 && resultado <= 120 : "casilla final fuera de rango";
                return resultado;
            }
        }
    }



    public boolean movimentInicial(Ficha f, int numDado) {
        //PRECONDICIONES
        assert f != null : "ficha es null";
        assert numDado >= 1 && numDado <= 6 : "dado fuera de rango";
        assert f.getPosicion() != null : "ficha sin posición";
        
        boolean result = (f.getPosicion().getNumero() < 0 && numDado == 5);

        //POSTCONDICION
        assert (result == true || result == false) : "resultado inválido";

        return result;
    }

    public boolean movimientPosible(Ficha ficha, int numDado) {
        captura = false;
        int posActual = ficha.getPosicion().getNumero();
        
        if (ficha.getPosicion().getNumero() < 0) {
            return movimentInicial(ficha, numDado);
        } else {
            int casillaDestino = posActual + numDado;
            if (casillaDestino > 68 && casillaDestino <= 69 + 20 && ficha.getColor() != ColorFicha.COLOR_AMARILLO) {
                casillaDestino = casillaDestino - 68;
            }
            casillaDestino = convertirCasillaFinal(ficha, casillaDestino);

            if (ficha.getColor() == ColorFicha.COLOR_AMARILLO && casillaDestino > 100 + 20) return false;
            if (ficha.getColor() == ColorFicha.COLOR_ROJO && casillaDestino > 84 + 20) return false;
            if (ficha.getColor() == ColorFicha.COLOR_AZUL && casillaDestino > 76 + 20) return false;
            if (ficha.getColor() == ColorFicha.COLOR_VERDE && casillaDestino > 92 + 20) return false;


            int[] destino = obtenerIndice(casillaDestino);
            if (destino == null) return false;  
            fichaDestino = tablero[destino[0]][destino[1]];

            if (fichaDestino.getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                fichaDestino.setPosicion(new Posicion(casillaDestino));
            }
            
            // Comprobamos que no haya barrera en el camino
            for (int i = posActual + 1; i <= casillaDestino; i++) {
                i = convertirCasillaFinal(ficha, i);
                int[] coord = obtenerIndice(i);
                if (coord != null) {
                    Ficha f = tablero[coord[0]][coord[1]];
                    if (f != null && f.isBarrera())
                        return false;
                }
            }

            if (fichaDestino.getTipo() == Ficha.TipoFicha.TIPO_EMPTY) return true;

            if (fichaDestino.getColor() == ficha.getColor()) return true;

            if (fichaDestino.getPosicion().esSeguro()) return false;

            captura = true;
            return true;
        }
    }

    private void enviarFichaACasa(Ficha ficha) {
        if (ficha.getColor() == ColorFicha.COLOR_ROJO) {
            if (tablero[1][1] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(1, 1, ColorFicha.COLOR_ROJO, new Posicion(-1, false));
            } else if (tablero[1][5] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(1, 5, ColorFicha.COLOR_ROJO, new Posicion(-2, false));
            } else if (tablero[5][1] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(5, 1, ColorFicha.COLOR_ROJO, new Posicion(-3, false));
            } else if (tablero[5][5] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(5, 5, ColorFicha.COLOR_ROJO, new Posicion(-4, false));
            }
        }
        if (ficha.getColor() == ColorFicha.COLOR_VERDE) {
            if (tablero[13][1] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(13, 1, ColorFicha.COLOR_VERDE, new Posicion(-5, false));
            } else if (tablero[13][5] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(13, 5, ColorFicha.COLOR_VERDE, new Posicion(-6, false));
            } else if (tablero[17][1] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(17, 1, ColorFicha.COLOR_VERDE, new Posicion(-7, false));
            } else if (tablero[17][5] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(17, 5, ColorFicha.COLOR_VERDE, new Posicion(-8, false));
            }
        }
        if (ficha.getColor() == ColorFicha.COLOR_AZUL) {
            if (tablero[1][13] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(1, 13, ColorFicha.COLOR_AZUL, new Posicion(-9, false));
            } else if (tablero[1][17] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(1, 17, ColorFicha.COLOR_AZUL, new Posicion(-10, false));
            } else if (tablero[5][13] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(5, 13, ColorFicha.COLOR_AZUL, new Posicion(-11, false));
            } else if (tablero[5][17] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(5, 17, ColorFicha.COLOR_AZUL, new Posicion(-12, false));
            }
        }
        if (ficha.getColor() == ColorFicha.COLOR_AMARILLO) {
            if (tablero[13][13] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(13, 13, ColorFicha.COLOR_AMARILLO, new Posicion(-13, false));
            } else if (tablero[13][17] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(13, 17, ColorFicha.COLOR_AMARILLO, new Posicion(-14, false));
            } else if (tablero[17][13] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(17, 13, ColorFicha.COLOR_AMARILLO, new Posicion(-15, false));
            } else if (tablero[17][17] .getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                setFicha(17, 17, ColorFicha.COLOR_AMARILLO, new Posicion(-16, false));
            }
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
        //PRECONDICIONES
        assert ficha != null : "ficha es null";
        assert ficha.getPosicion() != null : "posición null";
        assert numDado > 0 : "numDado debe ser positivo";

        int posAntes = ficha.getPosicion().getNumero();

        ColorFicha color = ficha.getColor();
        boolean barrera = ficha.isBarrera();
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

                // mover ficha
                if (tablero[destino[0]][destino[1]].getColor() == color) tablero[destino[0]][destino[1]].setBarrera(true);
                
                tablero[filaActual][colActual] = new Ficha(ColorFicha.NULL, Ficha.TipoFicha.TIPO_EMPTY, null, false);

                if (!tablero[destino[0]][destino[1]].isBarrera())
                {
                    ficha.setPosicion(new Posicion(salida));
                    tablero[destino[0]][destino[1]] = ficha;
                }
            }
            return;
        }

        // --- CASO 2: MOVIMIENTO NORMAL ---

        int casillaDestino = posActual + numDado;

        // Entradas finales excepto amarillo
        if (casillaDestino > 68 && casillaDestino <= 69 + 20 && ficha.getColor() != ColorFicha.COLOR_AMARILLO) {
            casillaDestino -= 68;
        }
        // Convertir tramo final según color
        casillaDestino = convertirCasillaFinal(ficha, casillaDestino);
        int[] destino = obtenerIndice(casillaDestino);
        // CAPTURA
        if (isCaptura()) {

            enviarFichaACasa(tablero[destino[0]][destino[1]]);
            captura = true;
        }

        // Mover la ficha

        if (tablero[destino[0]][destino[1]].getColor() == color) tablero[destino[0]][destino[1]].setBarrera(true);
        
        if (!barrera)
            tablero[filaActual][colActual] = new Ficha(ColorFicha.NULL, Ficha.TipoFicha.TIPO_EMPTY, null, false);
        else
            tablero[filaActual][colActual] = new Ficha(color, Ficha.TipoFicha.TIPO_OCUPADO, ficha.getPosicion(), false);

        if (!tablero[destino[0]][destino[1]].isBarrera())
        {
            ficha.setPosicion(new Posicion(casillaDestino));
            ficha.setBarrera(false);
            tablero[destino[0]][destino[1]] = ficha;
        }
        else
        {
            ficha.setPosicion(new Posicion(casillaDestino));
            tablero[destino[0]][destino[1]] = ficha;
        }
        if (isCaptura()) mouFicha(ficha, 20);

        //POSTCONDICIONES
        assert ficha.getPosicion() != null : "la ficha perdió su posición";
        assert ficha.getPosicion().getNumero() != posAntes || numDado == 0
        : "la ficha no cambió posición cuando debía";
    }

    public List<Ficha> getFichasPorColor(Ficha.ColorFicha color) {
        List<Ficha> lista = new ArrayList<>();

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                Ficha f = tablero[i][j];
                if (f.getColor() == color && f.getTipo() == Ficha.TipoFicha.TIPO_OCUPADO) {
                    lista.add(f);
                }
            }
        }
        return lista;
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
                    if (f.isBarrera()) System.out.print("(" + letraColor + ")");
                    else System.out.print(" " + letraColor + " "); // ocupado: también 3 caracteres
                }
            }
        System.out.println();
        }
    }

    public int[][] getNumerosTablero() {
    return numerosTablero;
}


    
}

