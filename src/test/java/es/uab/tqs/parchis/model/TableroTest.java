package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertNull(tablero.obtenerIndice(0));
        assertNull(tablero.obtenerIndice(101));

        // Valores límite negativos y mayores
        assertNull(tablero.obtenerIndice(-1));
        assertNull(tablero.obtenerIndice(102));
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
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 86);

        //CAS FICHA VERMELLA QUE DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(32, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(36, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(32));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(36));

        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 78);

        //CAS FICHA GROGA DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(66, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(2, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(66));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(2));

        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 94);

        //CAS FICHA BLAU DONA TOTA LA VOLTA:
        ficha = new Ficha(Ficha.ColorFicha.COLOR_AZUL, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(15, false), false);
        fichaOponente = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_OCUPADO, new Posicion(19, false), false);
        tablero.setFicha(ficha, tablero.obtenerIndice(15));
        tablero.setFicha(fichaOponente, tablero.obtenerIndice(19));
        
        assertTrue(tablero.movimientPosible(ficha, 4));
        assertFalse(tablero.isCaptura());
        assertEquals(tablero.getFichaDestino().getPosicion().getNumero(), 70);
    }
}
