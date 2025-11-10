package es.uab.tqs.parchis.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FichaTest {

    private Ficha fichaAmarilla;
    private Ficha fichaRoja;

    @BeforeEach
    void setUp() {
        fichaAmarilla = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO);
        fichaRoja = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_EMPTY);
    }

    @Test
    void testConstructorInicializaCorrectamente() {
        assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, fichaAmarilla.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, fichaAmarilla.getTipo());
        assertNull(fichaAmarilla.getPosicion());
        assertNull(fichaAmarilla.getMovimiento());

        assertEquals(Ficha.ColorFicha.COLOR_ROJO, fichaRoja.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_EMPTY, fichaRoja.getTipo());
    }

    @Test
    void testSetTipoCambiaTipoFicha() {
        fichaAmarilla.setTipo(Ficha.TipoFicha.TIPO_EMPTY);
        assertEquals(Ficha.TipoFicha.TIPO_EMPTY, fichaAmarilla.getTipo());
    }

    @Test
    void testGettersRetornanValoresEsperados() {
        assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, fichaAmarilla.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, fichaAmarilla.getTipo());
        assertNull(fichaAmarilla.getPosicion());
        assertNull(fichaAmarilla.getMovimiento());
    }
}