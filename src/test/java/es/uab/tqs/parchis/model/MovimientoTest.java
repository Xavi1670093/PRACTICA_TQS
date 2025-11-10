package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovimientoTest {
    
    Movimiento mov;
    @BeforeEach
    void setUp() {
        Posicion inicio = new Posicion(0, false);
        Posicion destino = new Posicion(4, false);
        mov = new Movimiento(inicio, destino, false);
    }

    @Test
    void testConstructor() {
        assertEquals(mov.getInicial().getNumero(), 0);
        assertEquals(mov.getDestino().getNumero(), 4);
        assertFalse(mov.isCaptura());
    }

    @Test
    void testSetters() {
        mov.setInicial(new Posicion(2, false));
        mov.setDestino(new Posicion(6, true));
        mov.setCaptura(true);
        assertEquals(mov.getInicial().getNumero(), 2);
        assertEquals(mov.getDestino().getNumero(), 6);
        assertTrue(mov.isCaptura());
    }
}
