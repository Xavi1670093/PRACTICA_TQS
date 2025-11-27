package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uab.tqs.parchis.model.Ficha.ColorFicha;

public class TableroTest {
    
    Tablero tablero;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        tablero = new Tablero();
        tablero.inicializa();
    }

    @Test
    void testInicializarTablero() {
        Ficha[][] matriz = tablero.getTablero();
        assertEquals(19, matriz.length); // Filas
        assertEquals(19, matriz[0].length); // Columnas

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                Ficha f = matriz[i][j];
                assertNotNull(f);
                assertNotNull(f.getTipo());
                assertNotNull(f.getColor());

                if ((i == 1 && j == 1) || (i == 1 && j == 5) ||(i == 5 && j == 1) || (i == 5 && j == 5)) {
                    assertEquals(Ficha.ColorFicha.COLOR_ROJO, f.getColor());
                    assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, f.getTipo());
                } 
                else if ((i == 13 && j == 1) || (i == 13 && j == 5) ||(i == 17 && j == 1) || (i == 17 && j == 5)) {
                    assertEquals(Ficha.ColorFicha.COLOR_VERDE, f.getColor());
                    assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, f.getTipo());
                }
                else if ((i == 1 && j == 13) || (i == 1 && j == 17) ||(i == 5 && j == 13) || (i == 5 && j == 17)) {
                    assertEquals(Ficha.ColorFicha.COLOR_AZUL, f.getColor());
                    assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, f.getTipo());
                }
                else if ((i == 13 && j == 13) || (i == 13 && j == 17) ||(i == 17 && j == 13) || (i == 17 && j == 17)) {
                    assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, f.getColor());
                    assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, f.getTipo());
                }
                else {
                    assertEquals(Ficha.TipoFicha.TIPO_EMPTY, f.getTipo());
                }
            }
        }
    }
    
    @Test
    void testObtenerIndice() {

        // Casos válidos
        int[] posValidoIntermedio = tablero.obtenerIndice(25);
        assertArrayEquals(new int[]{8, 11}, posValidoIntermedio);

        int[] posValidoInferior = tablero.obtenerIndice(1);
        assertArrayEquals(new int[]{18, 10}, posValidoInferior);

        int[] posValidoSuperior = tablero.obtenerIndice(68);
        assertArrayEquals(new int[]{18, 9}, posValidoSuperior);

        // Valores frontera (fuera de rango)
        assertNull(tablero.obtenerIndice(-17));
        assertNull(tablero.obtenerIndice(101 + 20));

        // Valores límite negativos y mayores
        assertNull(tablero.obtenerIndice(-18));
        assertNull(tablero.obtenerIndice(102 + 20));
    }

        
    
    
    @Test
    void testMovimentPosible() {
        Ficha ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(5, false), false);
        Ficha fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(10, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(5)); // Colocamos la ficha en el tablero
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(10)); // Colocamos la ficha del oponente en el tablero

        //CAS BASIC -> captura normal
        assertTrue(tablero.movimientPosible(ficha, 5));
        assertTrue(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 6)); //Valors frontera
        assertFalse(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 4)); //Valors frontera
        assertFalse(tablero.isCaptura());

        //CAS EXTRA: ficha del mateix color:
        Ficha fichaAliada = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(8, false), false);
        tablero.setFicha(fichaAliada, tablero.obtenerIndice(8));

        assertTrue(tablero.movimientPosible(ficha, 3));
        assertFalse(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 4)); //Valors frontera
        assertFalse(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 2)); //Valors frontera
        assertFalse(tablero.isCaptura());

        
        //CAS EXTRA -> Barrera:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(15, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(20, false), true);
        tablero.setFicha(ficha, tablero.obtenerIndice(15)); 
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(20));

        assertFalse(tablero.movimientPosible(ficha, 5));
        assertFalse(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 4)); //Valors frontera
        assertFalse(tablero.isCaptura());
        assertFalse(tablero.movimientPosible(ficha, 6)); //Valors frontera
        assertFalse(tablero.isCaptura());

        //CAS EXTRA -> Casilla segura
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(25, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(30, true), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(25)); 
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(29)); 
        
        assertFalse(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 5)); //Valors frontera
        assertFalse(tablero.isCaptura());
        assertTrue(tablero.movimientPosible(ficha, 3)); //Valors frontera
        assertFalse(tablero.isCaptura());

        //CAS FICHA VERDA DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(49, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(53, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(49)); 
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(53));

        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 86 + 20);

        //CAS FICHA VERMELLA QUE DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(32, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(36, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(32));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(36));

        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 78 + 20);

        //CAS FICHA GROGA DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(2, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(2));

        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 94 + 20);

        //CAS FICHA BLAU DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(15, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(19, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(15));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(19));
        
        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 70 + 20);

        //CAS FICHA ROJA PASA DEL 68 AL 1
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(68, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(68));
        assertTrue(tablero.movimientPosible(ficha, 1));
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 1);

        //CAS FICHA VERDE PASA DEL 66 AL 4
        ficha = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        assertTrue(tablero.movimientPosible(ficha, 6));
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 4);

        //CAS FICHA VERDE PASA DEL 68 AL 1
        ficha = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        assertTrue(tablero.movimientPosible(ficha, 4));
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 2);
    }

    @Test
    void testIniciPartidaMovimentPosible() {
        assertFalse(tablero.movimientPosible(tablero.getFicha(1,1), 4)); //FICHA ROJA
        assertTrue(tablero.movimientPosible(tablero.getFicha(1,1), 5)); //FICHA ROJA
        assertFalse(tablero.movimientPosible(tablero.getFicha(13,1), 3)); //FICHA VERDA
        assertTrue(tablero.movimientPosible(tablero.getFicha(13,1), 5)); //FICHA VERDA
        assertFalse(tablero.movimientPosible(tablero.getFicha(1,13), 2)); //FICHA BLAU
        assertTrue(tablero.movimientPosible(tablero.getFicha(1,13), 5)); //FICHA BLAU
        assertFalse(tablero.movimientPosible(tablero.getFicha(13,13), 1)); //FICHA GROCA
        assertTrue(tablero.movimientPosible(tablero.getFicha(13,13), 5)); //FICHA GROCA

    }
    @Test
    void testConvertirCasillaFinal() {
        Ficha fichaRoja = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(65, false), false);
        Ficha fichaVerde = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(65, false), false);
        Ficha fichaAzul = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(65, false), false);
        Ficha fichaAmarilla = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(65, false), false);

        // Casillas dentro del rango normal
        assertEquals(67, tablero.convertirCasillaFinal(fichaRoja, 67));
        assertEquals(68, tablero.convertirCasillaFinal(fichaVerde, 68));

        // Casillas que exceden el rango normal
        assertEquals(97, tablero.convertirCasillaFinal(fichaRoja, 35));
        assertEquals(98, tablero.convertirCasillaFinal(fichaRoja, 36));
        assertEquals(99, tablero.convertirCasillaFinal(fichaRoja, 37));
        assertEquals(100, tablero.convertirCasillaFinal(fichaRoja, 38));

        assertEquals(85 + 20, tablero.convertirCasillaFinal(fichaVerde, 52));
        assertEquals(86 + 20, tablero.convertirCasillaFinal(fichaVerde, 53));
        assertEquals(87 + 20, tablero.convertirCasillaFinal(fichaVerde, 54));
        assertEquals(88 + 20, tablero.convertirCasillaFinal(fichaVerde, 55));

        assertEquals(69 + 20, tablero.convertirCasillaFinal(fichaAzul, 18));
        assertEquals(70 + 20, tablero.convertirCasillaFinal(fichaAzul, 19));
        assertEquals(71 + 20, tablero.convertirCasillaFinal(fichaAzul, 20));
        assertEquals(72 + 20, tablero.convertirCasillaFinal(fichaAzul, 21));

        assertEquals(93 + 20, tablero.convertirCasillaFinal(fichaAmarilla, 69));
        assertEquals(94 + 20, tablero.convertirCasillaFinal(fichaAmarilla, 70));
        assertEquals(95 + 20, tablero.convertirCasillaFinal(fichaAmarilla, 71));
        assertEquals(96 + 20, tablero.convertirCasillaFinal(fichaAmarilla,   72));
    }

    @Test
    void movimentInicialTest() {
    
        assertFalse(tablero.movimentInicial(tablero.getFicha(1, 1), 4));
        assertTrue(tablero.movimentInicial(tablero.getFicha(1, 1), 5));

        assertFalse(tablero.movimentInicial(tablero.getFicha(13, 1), 3));
        assertTrue(tablero.movimentInicial(tablero.getFicha(13, 1), 5));

        assertFalse(tablero.movimentInicial(tablero.getFicha(13, 13), 2));
        assertTrue(tablero.movimentInicial(tablero.getFicha(13, 13), 5));

        assertFalse(tablero.movimentInicial(tablero.getFicha(1, 13), 1));
        assertTrue(tablero.movimentInicial(tablero.getFicha(1, 13), 5));
    }

    @Test
    void mouFichaTest() {

        // CASO BÁSICO: captura normal
        Ficha ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(5, false), false);
        Ficha fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(10, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(5));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(10));

        assertTrue(tablero.movimientPosible(ficha, 5));
        tablero.mouFicha(ficha, 5);

        assertEquals(30, ficha.getPosicion().getNumero());
        // la ficha capturada debe volver a casa: comprobamos que su posición es negativa o que se ubicó en un slot inicial
        tablero.getTablero()[1][13] = new Ficha(ColorFicha.NULL, Ficha.TipoFicha.TIPO_EMPTY, null, false);
        
        // MOVIMIENTO NORMAL SIN CAPTURA
        ficha.setPosicion(new Posicion(5, false));
        tablero.setFicha(ficha, tablero.obtenerIndice(5));

        assertTrue(tablero.movimientPosible(ficha, 6));
        tablero.mouFicha(ficha, 6);
        assertEquals(11, ficha.getPosicion().getNumero());

        // MOVIMIENTO BLOQUEADO POR ALIADA
        Ficha aliada = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(8, false), false);
        tablero.setFicha(aliada, tablero.obtenerIndice(8));

        ficha.setPosicion(new Posicion(5, false));
        tablero.setFicha(ficha, tablero.obtenerIndice(5));

        assertTrue(tablero.movimientPosible(ficha, 3));
        tablero.mouFicha(ficha, 3);
        assertEquals(8, ficha.getPosicion().getNumero());

        // BARRERA (NO DEBE MOVERSE)
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(15, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(20, false), true);
        tablero.setFicha(ficha, tablero.obtenerIndice(15));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(20));

        assertFalse(tablero.movimientPosible(ficha, 5));
        tablero.mouFicha(ficha, 5);
        assertEquals(15, ficha.getPosicion().getNumero());

        // CASILLA SEGURA (NO DEBE CAPTURAR)
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(25, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(29, true), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(25));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(29));

        assertFalse(tablero.movimientPosible(ficha, 4));
        tablero.mouFicha(ficha, 4);
        assertEquals(25, ficha.getPosicion().getNumero());

        // VERDE DA LA VUELTA
        ficha = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(49, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(53, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(49));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(53));

        assertTrue(tablero.movimientPosible(ficha, 4));
        tablero.mouFicha(ficha, 4);
        assertEquals(86 + 20, ficha.getPosicion().getNumero());

        // ROJA DA LA VUELTA
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(32, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(36, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(32));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(36));

        assertTrue(tablero.movimientPosible(ficha, 4));
        tablero.mouFicha(ficha, 4);
        assertEquals(78 + 20, ficha.getPosicion().getNumero());

        // AMARILLA DA LA VUELTA
        ficha = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(2, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(2));

        assertTrue(tablero.movimientPosible(ficha, 4));
        tablero.mouFicha(ficha, 4);
        assertEquals(94 + 20, ficha.getPosicion().getNumero());

        // AZUL DA LA VUELTA
        ficha = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(15, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(19, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(15));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(19));

        assertTrue(tablero.movimientPosible(ficha, 4));
        tablero.mouFicha(ficha, 4);
        assertEquals(70 + 20, ficha.getPosicion().getNumero());

        // ROJA 68 → 1
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(68, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(68));
        assertTrue(tablero.movimientPosible(ficha, 1));
        tablero.mouFicha(ficha, 1);
        assertEquals(1, ficha.getPosicion().getNumero());

        // VERDE 66 → 4
        ficha = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        assertTrue(tablero.movimientPosible(ficha, 6));
        tablero.mouFicha(ficha, 6);
        assertEquals(4, ficha.getPosicion().getNumero());

        // VERDE 66 → 2
        ficha = new Ficha(Ficha.ColorFicha.COLOR_VERDE, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        assertTrue(tablero.movimientPosible(ficha, 4));
        tablero.mouFicha(ficha, 4);
        assertEquals(2, ficha.getPosicion().getNumero());

        // NUEVO CASO: ficha en casa (posición negativa) saca 5 y sale a su casilla de salida
        // Usamos la entrada roja en coordenadas (1,1) con posición -1
        Ficha fichaCasa = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(-1, false), false);
        tablero.setFicha(fichaCasa, new int[]{1, 1}); // posicion física de casa para roja

        assertTrue(tablero.movimientPosible(fichaCasa, 5));
        tablero.mouFicha(fichaCasa, 5);

        // para rojo la casilla de salida indicada es 39
        assertEquals(39, fichaCasa.getPosicion().getNumero());
        int[] coordSalidaRoja = tablero.obtenerIndice(39);
        assertNotNull(coordSalidaRoja);
        assertSame(fichaCasa, tablero.getFicha(coordSalidaRoja[0], coordSalidaRoja[1]));
    }
}
