package es.uab.tqs.parchis.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FichaTest {

    private Ficha fichaAmarilla;
    private Ficha fichaRoja;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        fichaAmarilla = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO, null, null);
        fichaRoja = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_EMPTY, null, null);
    }

    @Test
    void testConstructorInicializaCorrectamente() {
        assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, fichaAmarilla.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, fichaAmarilla.getTipo());
        assertNull(fichaAmarilla.getPosicion());
        assertNull(fichaAmarilla.getMovimiento());

        assertEquals(Ficha.ColorFicha.COLOR_ROJO, fichaRoja.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_EMPTY, fichaRoja.getTipo());
        assertNull(fichaRoja.getPosicion());
        assertNull(fichaRoja.getMovimiento());
    }

    @Test
    void testSetTipoCambiaTipoFicha() {
        fichaAmarilla.setTipo(Ficha.TipoFicha.TIPO_EMPTY);
        assertEquals(Ficha.TipoFicha.TIPO_EMPTY, fichaAmarilla.getTipo());
    }

    @Test
    void testSetColorCambiaColorFicha() {
        fichaRoja.setColor(Ficha.ColorFicha.COLOR_VERDE);
        assertEquals(Ficha.ColorFicha.COLOR_VERDE, fichaRoja.getColor());
    }

    @Test
    void testGettersRetornanValoresEsperados() {
        assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, fichaAmarilla.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, fichaAmarilla.getTipo());
        assertNull(fichaAmarilla.getPosicion());
        assertNull(fichaAmarilla.getMovimiento());
    }

    @Test
    void testConstructorVacioNoLanzaErrores() {
        Ficha fichaVacia = new Ficha();
        assertNull(fichaVacia.getColor());
        assertNull(fichaVacia.getTipo());
        assertNull(fichaVacia.getPosicion());
        assertNull(fichaVacia.getMovimiento());
    }
}
