package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TableroTest {
    
    Tablero tablero;

    @BeforeEach
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
}
